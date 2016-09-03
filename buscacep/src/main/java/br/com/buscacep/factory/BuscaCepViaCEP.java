package br.com.buscacep.factory;

import java.util.Map;

import javax.json.JsonObject;

import br.com.buscacep.domain.Endereco;
import br.com.buscacep.exception.CEPException;
import br.com.buscacep.service.BuscaCepService;
import br.com.buscacep.service.BuscaCepServiceImpl;
import br.com.buscacep.util.Util;

public class BuscaCepViaCEP extends BuscaCep {
	
	private BuscaCepService service;
	
	public BuscaCepViaCEP() {
		setUrl("http://viacep.com.br/ws/");
	}

	@Override
	public String buscaCep(String cep) {
		service = new BuscaCepServiceImpl();
		JsonObject jsonObject = null;
		try {
			if (!cep.isEmpty())
				jsonObject = service.buscaCep(getUrl() + Util.getCEPFormat(cep) + "/json");

		} catch (CEPException e) {
			e.printStackTrace();
		}
		
		return jsonObject.toString();
	}

	@Override
	public Map<String, Object> getCeps(String cep) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Endereco getEnderecoByCep(String cep) {
		service = new BuscaCepServiceImpl();
		JsonObject jsonObject = null;
		Endereco endereco = new Endereco();
		try {
			if (!cep.isEmpty()) {
				jsonObject = service.buscaCep(getUrl() + Util.getCEPFormat(cep) + "/json");				
				
				endereco.setBairro(getString(jsonObject, "bairro"));
				endereco.setCep(getString(jsonObject, "cep"));
				endereco.setCidade(getString(jsonObject, "localidade"));				
				endereco.setEndereco(getString(jsonObject, "logradouro"));
				endereco.setEstado(getString(jsonObject, "uf"));
			}
		} catch (CEPException e) {
			e.printStackTrace();
		}

		return endereco;
	}

}
