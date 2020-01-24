package br.com.servise;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.entities.Item;
import br.com.exception.CampoExistenteException;
import br.com.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository repository;

	public Item cadastrar(Item item) {
		if (repository.findByNome(item.getNome()).size() > 0)
			throw new CampoExistenteException("Já existe um item cadastrado com este nome");

		return repository.save(item);
	}

	public Item atualizar(Item objeto) {
		Item item = repository.findById(objeto.getId()).get();

		if (objeto.getNome() != null)
			item.setNome(objeto.getNome());

		return repository.save(item);
	}

	public void excluir(Integer id) {
		repository.deleteById(id);
	}

	public List<Item> listar() {
		return repository.findAll();
	}

}