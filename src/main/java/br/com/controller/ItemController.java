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

import br.com.entities.Item;
import br.com.servise.ItemService;

@RestController
@RequestMapping(value = "/item")
public class ItemController {

	@Autowired
	ItemService service;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Item cadastrar(@RequestBody Item item) {
		return service.cadastrar(item);
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping
	public Item atualizar(@RequestBody Item item) {
		return service.atualizar(item);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Integer id) {
		service.excluir(id);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public List<Item> listar() {
		return service.listar();
	}
}