package br.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.entities.Usuario;
import br.com.servise.UsuarioService;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

	@Autowired
	UsuarioService service;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Usuario cadastrar(@RequestBody Usuario usuario) {
		return service.cadastrar(usuario);
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping
	public Usuario atualizar(@RequestBody Usuario usuario) {
		return service.atualizar(usuario);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(value = "/{id}")
	public void excluir(@PathVariable Integer id) {
		service.excluir(id);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public List<Usuario> listar() {
		return service.listar();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/buscarPorNome/{nome}")
	public List<Usuario> buscarPorNome(@PathVariable String nome) {
		return service.buscarPorNome(nome);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/buscarPorEmail/{email}")
	public List<Usuario> buscarPorEmail(@PathVariable String email) {
		return service.buscarPorEmail(email);
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "login")
	public void login(@RequestBody Usuario usuario) {
		service.login(usuario);
	}

}