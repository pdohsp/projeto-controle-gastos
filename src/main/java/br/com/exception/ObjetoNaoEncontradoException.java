package br.com.exception;

public class ObjetoNaoEncontradoException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ObjetoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

}
