package br.com.exception;

public class LoginException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public LoginException(String mensagem) {
		super(mensagem);
	}

}
