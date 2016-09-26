package com.github.wagnerfonseca.buscacep.service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.github.wagnerfonseca.buscacep.exception.CEPException;

public class BuscaCepServiceImpl implements BuscaCepService {

	@Override
	public JsonObject buscaCep(String url) throws CEPException {		
		JsonObject result = null;
        try {          

        	CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = httpclient.execute(httpGet);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException(response.getStatusLine().getReasonPhrase());
            }

            HttpEntity entity = response.getEntity();

            result = Json.createReader(entity.getContent()).readObject();

        } catch (Exception e) {
            throw new CEPException("Houve um erro no momento obter os dados do cep.\n" + e.getMessage());
        }

        return result;
		
	}
	
	public JsonObject buscaCepByHttp(String urlToRead) throws CEPException {
		StringBuilder result = new StringBuilder();
		JsonObject obj = null;
		InputStream in = null;
        try {
            URL url = new URL(urlToRead);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            
            if (!result.toString().isEmpty()) {
            	in = new ByteArrayInputStream(result.toString().getBytes());
            	obj = Json.createReader(in).readObject(); 
            }
            
        } catch (MalformedURLException | ProtocolException ex) {
            throw new CEPException(ex.getMessage());
        } catch (IOException ex) {
            throw new CEPException(ex.getMessage());
        }
     
        return obj;
	}

}
