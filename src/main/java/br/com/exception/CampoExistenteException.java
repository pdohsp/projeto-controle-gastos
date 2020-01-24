package br.com.exception;

public class CampoExistenteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CampoExistenteException(String mensagem) {
		super(mensagem);
	}
}
