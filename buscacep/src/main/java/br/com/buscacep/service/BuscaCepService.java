package br.com.buscacep.service;

import javax.json.JsonObject;

import br.com.buscacep.exception.CEPException;

public interface BuscaCepService {
	
	JsonObject buscaCep(String url) throws CEPException;	
	
	JsonObject buscaCepByHttp(String urlToRead) throws CEPException;

}
