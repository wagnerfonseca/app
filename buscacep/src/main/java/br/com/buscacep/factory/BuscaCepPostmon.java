package br.com.buscacep.factory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.json.JsonObject;
import javax.json.JsonValue;

import br.com.buscacep.domain.Endereco;
import br.com.buscacep.exception.CEPException;
import br.com.buscacep.service.BuscaCepService;
import br.com.buscacep.service.BuscaCepServiceImpl;
import br.com.buscacep.util.Util;

public class BuscaCepPostmon extends BuscaCep {

	private BuscaCepService service;

	/**
	 * Classe para recuperar informações de CEP do WS do postmon.com.br
	 */
	public BuscaCepPostmon() {
		setUrl("http://api.postmon.com.br/v1/cep/");
	}

	@Override
	public String buscaCep(String cep) {
		service = new BuscaCepServiceImpl();
		JsonObject jsonObject = null;
		try {
			if (!cep.isEmpty())
				jsonObject = service.buscaCep(getUrl() + Util.getCEPFormat(cep));

		} catch (CEPException e) {
			e.printStackTrace();
		}
		
		return jsonObject.toString();
	}

	@Override
	public Map<String, Object> getCeps(String cep) {

		service = new BuscaCepServiceImpl();
		JsonObject jsonObject = null;
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			if (!cep.isEmpty())
				jsonObject = service.buscaCep(getUrl() + Util.getCEPFormat(cep));

			for (Iterator<Map.Entry<String, JsonValue>> it = jsonObject.entrySet().iterator(); it.hasNext();) {
				Map.Entry<String, JsonValue> entry = it.next();

				if (entry.getValue().getValueType().equals(JsonValue.ValueType.OBJECT)) {
					JsonObject subObject = jsonObject.getJsonObject(entry.getKey());

					Map<String, String> mapaSo = new HashMap<String, String>();
					for (Iterator<Map.Entry<String, JsonValue>> itso = subObject.entrySet().iterator(); itso.hasNext();) {
						Map.Entry<String, JsonValue> entrySo = itso.next();

						mapaSo.put(entrySo.getKey(), entrySo.getValue().toString());
					}

					map.put(entry.getKey(), mapaSo);
				} else {
					map.put(entry.getKey(), entry.getValue().toString());
				}
			}

		} catch (CEPException e) {
			e.printStackTrace();
		}

		return map;
	}

	@Override
	public Endereco getEnderecoByCep(String cep) {
		service = new BuscaCepServiceImpl();
		JsonObject jsonObject = null;
		Endereco endereco = new Endereco();
		try {
			if (!cep.isEmpty()) {
				jsonObject = service.buscaCep(getUrl() + Util.getCEPFormat(cep));				
				
				endereco.setBairro(getString(jsonObject, "bairro"));
				endereco.setCep(getString(jsonObject, "cep"));
				endereco.setCidade(getString(jsonObject, "cidade"));				
				endereco.setEndereco(getString(jsonObject, "logradouro") + getString(jsonObject, "endereco"));
				endereco.setEstado(getString(jsonObject, "estado"));
			}
		} catch (CEPException e) {
			e.printStackTrace();
		}

		return endereco;
	}
	
}
