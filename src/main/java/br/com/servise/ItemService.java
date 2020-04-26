package br.com.servise;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dto.ItemDTO;
import br.com.entities.Item;
import br.com.exception.CampoExistenteException;
import br.com.exception.CampoNuloException;
import br.com.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository repository;

	public ItemDTO cadastrar(Item item) {
		if (item.getNome() == null || item.getNome().equals("")) {
			throw new CampoNuloException("O campo nome não pode ser nulo");
		}
		if (repository.findByNome(item.getNome()) != null) {
			throw new CampoExistenteException("Já existe um item cadastrado com este nome");
		}
		
		ItemDTO itemDTO = new ItemDTO(repository.save(item));
		return itemDTO;
	}

	public ItemDTO atualizar(Item objeto) {
		if (objeto.getNome() == null || objeto.getNome().equals("")) {
			throw new CampoNuloException("O campo nome não pode ser nulo");
		}
		if (repository.findByNome(objeto.getNome()) != null) {
			throw new CampoExistenteException("Já existe um item cadastrado com este nome");
		}
		
		Item item = repository.findById(objeto.getId()).get();
		item.setNome(objeto.getNome());
		item.setValor(objeto.getValor());
		
		ItemDTO itemDTO = new ItemDTO(repository.save(item));
		return itemDTO;
	}

	public void excluir(Integer id) {
		repository.deleteById(id);
	}

	public List<ItemDTO> listar() {
		return repository.findAll().stream().map(x -> new ItemDTO(x)).collect(Collectors.toList());
	}

	public List<ItemDTO> buscarPorNome(String nome) {
		return repository.findByNomeContaining(nome).stream().map(x -> new ItemDTO(x)).collect(Collectors.toList());
	}

}