package br.com.exception;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(CampoExistenteException.class)
	public ResponseEntity<StandardError> campoExistente(CampoExistenteException emailExistenteException,
			HttpServletRequest httpServletRequest) {

		StandardError standardError = new StandardError(Instant.now(), HttpStatus.UPGRADE_REQUIRED.value(), "Campo já cadastrado",
				emailExistenteException.getMessage(), httpServletRequest.getRequestURI());

		return ResponseEntity.status(HttpStatus.UPGRADE_REQUIRED).body(standardError);
	}
	
	
	@ExceptionHandler(CampoNuloException.class)
	public ResponseEntity<StandardError> campoExistente(CampoNuloException campoNuloException,
			HttpServletRequest httpServletRequest) {

		StandardError standardError = new StandardError(Instant.now(), HttpStatus.UPGRADE_REQUIRED.value(), "Campo não pode ser nulo",
				campoNuloException.getMessage(), httpServletRequest.getRequestURI());

		return ResponseEntity.status(HttpStatus.UPGRADE_REQUIRED).body(standardError);
	}
	
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<StandardError> campoExistente(LoginException loginexception,
			HttpServletRequest httpServletRequest) {

		StandardError standardError = new StandardError(Instant.now(), HttpStatus.UNAUTHORIZED.value(), "Não foi possível efetuar o login",
				loginexception.getMessage(), httpServletRequest.getRequestURI());

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(standardError);
	}
	
	@ExceptionHandler(ObjetoNaoEncontradoException.class)
	public ResponseEntity<StandardError> ObjetoNaoEncontrado(ObjetoNaoEncontradoException objetoNaoEncontradoException,
			HttpServletRequest httpServletRequest) {

		StandardError standardError = new StandardError(Instant.now(), HttpStatus.NOT_FOUND.value(), "Objeto nao encontrado",
				objetoNaoEncontradoException.getMessage(), httpServletRequest.getRequestURI());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
	}
	
}