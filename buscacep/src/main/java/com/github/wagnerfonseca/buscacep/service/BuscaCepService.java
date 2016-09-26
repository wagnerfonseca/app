package com.github.wagnerfonseca.buscacep.service;

import javax.json.JsonObject;

import com.github.wagnerfonseca.buscacep.exception.CEPException;

public interface BuscaCepService {
	
	JsonObject buscaCep(String url) throws CEPException;	
	
	JsonObject buscaCepByHttp(String urlToRead) throws CEPException;

}
