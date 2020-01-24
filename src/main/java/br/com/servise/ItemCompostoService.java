package br.com.servise;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.entities.Item;
import br.com.entities.ItemComposto;
import br.com.exception.CampoExistenteException;
import br.com.exception.CampoNuloException;
import br.com.repository.ItemCompostoRepository;

@Service
public class ItemCompostoService {

	@Autowired
	private ItemCompostoRepository repository;

	public ItemComposto cadastrar(ItemComposto itemComposto) {
		if (repository.findByNome(itemComposto.getNome()).size() > 0)
			throw new CampoExistenteException("Já existe um item composto cadastrado com este nome");

		return repository.save(itemComposto);
	}

	public ItemComposto atualizarNome(ItemComposto objeto) {
		ItemComposto itemComposto = repository.findById(objeto.getId()).get();

		if (objeto.getNome() != null)
			itemComposto.setNome(objeto.getNome());

		return repository.save(itemComposto);
	}

	public ItemComposto addItem(ItemComposto objeto) {
		ItemComposto itemComposto = repository.findById(objeto.getId()).get();
		
		if (objeto.getItems().size() < 1)
			throw new CampoNuloException("Item Não informado");
		if ( repository.findByNome(objeto.getItems().get(0).getNome()).get(0).getItems() != null)
			throw new CampoExistenteException("Um item com este nome já foi cadastrado");
			
		itemComposto.getItems().add(objeto.getItems().get(0));

		return repository.save(itemComposto);
	}

	public void excluir(Integer id) {
		repository.deleteById(id);
	}

	public List<ItemComposto> listar() {
		return repository.findAll();
	}

}