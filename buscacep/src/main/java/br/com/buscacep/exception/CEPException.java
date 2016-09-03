package br.com.buscacep.exception;

public class CEPException extends Exception {
	private static final long serialVersionUID = -2052790468514598646L;
	
	private String cep;
	private String clazz;
	
	public CEPException() {
		super();
	}
	
	public CEPException(String message) {
		super(message);		
	}
	
	public CEPException(String message, String cep) {
		super(message);
		this.cep = cep;
	}
	
	public CEPException(String message, String cep, String clazz) {
		super(message);
		this.cep = cep;
		this.clazz = clazz;
	}

	public String getCep() {
		return cep;
	}
	
	public Boolean hasCEp() {
		return !this.cep.isEmpty();
	}

	public String getClazz() {
		return clazz;
	}	
	
}
