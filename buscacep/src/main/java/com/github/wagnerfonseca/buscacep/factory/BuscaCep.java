package com.github.wagnerfonseca.buscacep.factory;

import java.util.Map;

import javax.json.JsonObject;

import com.github.wagnerfonseca.buscacep.model.Endereco;

public abstract class BuscaCep {
	
	private String url;
	
	public abstract String buscaCep(String cep);
	
	public abstract Map<String, Object> getCeps(String cep);
	
	public abstract Endereco getEnderecoByCep(String cep);

	protected String getUrl() {
		return url;
	}

	protected void setUrl(String url) {
		this.url = url;
	}
	
	protected String getString(JsonObject obj, String value) {
		String result = "";
		try {			
			result = obj.getString(value);
		} catch(NullPointerException e) {
			return result = "";
		}
		
		return result;
	}
}
