package br.com.exception;

public class CampoNuloException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public CampoNuloException(String mensagem) {
		super(mensagem);
	}
}
