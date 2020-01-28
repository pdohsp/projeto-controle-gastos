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

import br.com.entities.Orcamento;
import br.com.servise.OrcamentoService;

@RestController
@RequestMapping(value = "/orcamento")
public class OrcamentoController {

	@Autowired
	OrcamentoService service;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Orcamento cadastrar(@RequestBody Orcamento orcamento) {
		return service.cadastrar(orcamento);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Integer id) {
		service.excluir(id);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public List<Orcamento> listar() {
		return service.listar();
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/addItem")
	public Orcamento addItem(@RequestBody Orcamento orcamento) {
		return service.addItem(orcamento);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/removerItem")
	public Orcamento removerItem(@RequestBody Orcamento orcamento) {
		return service.removerItem(orcamento);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/addItemComposto")
	public Orcamento addItemComposto(@RequestBody Orcamento orcamento) {
		return service.addItemComposto(orcamento);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/removerItemComposto")
	public Orcamento removerItemComposto(@RequestBody Orcamento orcamento) {
		return service.removerItemComposto(orcamento);
	}
	
}