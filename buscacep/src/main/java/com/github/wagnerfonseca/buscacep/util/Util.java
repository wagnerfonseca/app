package com.github.wagnerfonseca.buscacep.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class Util {
	
	public static String getCEPFormat(String cep) {		
		if (!cep.isEmpty()){
			return cep.replaceAll("(\\d{5})-?(\\d{3})", "$1$2");
		}
		return null;		
	}
	
	public static String readInputStream(InputStream input) {		
		String result = "";
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(input))) {
            result = buffer.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
			e.printStackTrace();
		}
        return result;
    }

}
