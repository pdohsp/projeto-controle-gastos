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

import br.com.entities.ItemComposto;
import br.com.servise.ItemCompostoService;

@RestController
@RequestMapping(value = "/itemComposto")
public class ItemCompostoController {

	@Autowired
	ItemCompostoService service;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public ItemComposto cadastrar(@RequestBody ItemComposto itemComposto) {
		return service.cadastrar(itemComposto);
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/atualizarNome")
	public ItemComposto atualizarNome(@RequestBody ItemComposto itemComposto) {
		return service.atualizarNome(itemComposto);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/addItem")
	public ItemComposto addItem(@RequestBody ItemComposto itemComposto) {
		return service.addItem(itemComposto);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/removerItem")
	public ItemComposto removerItem(@RequestBody ItemComposto itemComposto) {
		return service.removerItem(itemComposto);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Integer id) {
		service.excluir(id);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public List<ItemComposto> listar() {
		return service.listar();
	}
	
}