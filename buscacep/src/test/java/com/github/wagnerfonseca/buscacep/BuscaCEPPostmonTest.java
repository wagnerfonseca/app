package com.github.wagnerfonseca.buscacep;

import static org.junit.Assert.*;

import org.junit.*;

import com.github.wagnerfonseca.buscacep.factory.BuscaCep;
import com.github.wagnerfonseca.buscacep.factory.BuscaCepPostmon;
import com.github.wagnerfonseca.buscacep.model.Endereco;

public class BuscaCEPPostmonTest {
	
	private BuscaCep cep;	
	
	@Before
	public void criaObject() {
		cep = new BuscaCepPostmon();		
	}
	
	@Test
	public void testBuscaCep() {
		String result = cep.buscaCep("70002900");
		assertNotNull(result);	
	}	
	
	@Test 
	public void testEnderecoBairro() {
		Endereco endereco = cep.getEnderecoByCep("14405445");		
		assertTrue(endereco.getBairro().toLowerCase().contains("maria rosa"));
		
	}
	
	@Test 
	public void testEnderecoRua() {
		Endereco endereco = cep.getEnderecoByCep("70002-900");	
		assertTrue(endereco.getEndereco().toLowerCase().contains("sbn quadra 1 bloco a"));		
	}
	
	@Test 
	public void testEnderecoNomeCidade() {
		Endereco endereco = cep.getEnderecoByCep("70002900");	
		assertTrue(endereco.getCidade().toLowerCase().contains("brasília"));		
	}
	
	@Test 
	public void testEnderecoCidade() {
		Endereco endereco = cep.getEnderecoByCep("14405445");	
		assertTrue(endereco.getCidade().toLowerCase().contains("franca"));		
	}
	
	@Test 
	public void testEnderecoUF() {
		Endereco endereco = cep.getEnderecoByCep("14405445");	
		assertTrue(endereco.getEstado().toLowerCase().contains("sp"));		
	}

}
