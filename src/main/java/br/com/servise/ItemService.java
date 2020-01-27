package br.com.servise;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.entities.Item;
import br.com.exception.CampoExistenteException;
import br.com.exception.CampoNuloException;
import br.com.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository repository;

	public Item cadastrar(Item item) {
		if (item.getNome() == null || item.getNome().equals("")) {
			throw new CampoNuloException("O campo nome não pode ser nulo");
		}
		if (repository.findByNome(item.getNome()).size() > 0) {
			throw new CampoExistenteException("Já existe um item cadastrado com este nome");
		}
		return repository.save(item);
	}

	public Item atualizar(Item objeto) {
		if (objeto.getNome() == null || objeto.getNome().equals("")) {
			throw new CampoNuloException("O campo nome não pode ser nulo");
		}
		if (repository.findByNome(objeto.getNome()).size() > 0) {
			throw new CampoExistenteException("Já existe um item cadastrado com este nome");
		}
		
		Item item = repository.findById(objeto.getId()).get();
		item.setNome(objeto.getNome());
		
		return repository.save(item);
	}

	public void excluir(Integer id) {
		repository.deleteById(id);
	}

	public List<Item> listar() {
		return repository.findAll();
	}

	public List<Item> buscarPorNome(String nome) {
		return repository.findByNome(nome);
	}

}