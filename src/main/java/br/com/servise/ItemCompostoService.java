package br.com.servise;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.entities.ItemComposto;
import br.com.exception.CampoExistenteException;
import br.com.exception.CampoNuloException;
import br.com.exception.ObjetoNaoEncontradoException;
import br.com.repository.ItemCompostoRepository;

@Service
public class ItemCompostoService {

	@Autowired
	private ItemCompostoRepository repository;

	@Autowired
	private ItemService itemService;

	public ItemComposto cadastrar(ItemComposto itemComposto) {
		return repository.save(itemComposto);
	}

	public ItemComposto atualizarNome(ItemComposto objeto) {
		ItemComposto itemComposto = repository.findById(objeto.getId()).get();

		if (objeto.getNome() != null && objeto.getNome() != "") {
			itemComposto.setNome(objeto.getNome());
		}
		return repository.save(itemComposto);
	}

	public ItemComposto addItem(ItemComposto objeto) {
		if (objeto.getItems().size() == 0) {
			throw new CampoNuloException("Item Não informado");
		}
		if (!repository.findById(objeto.getId()).isPresent()) {
			throw new ObjetoNaoEncontradoException("Id do orçamento não encontrado");
		}

		ItemComposto itemComposto = repository.findById(objeto.getId()).get();

		if (itemService.buscarPorNome(objeto.getItems().get(0).getNome()) == null) {
			itemService.cadastrar(objeto.getItems().get(0));
		} else if (itemComposto.getItems().contains(objeto.getItems().get(0))) {
			throw new CampoExistenteException("O item: " + objeto.getItems().get(0).getNome() + ", já foi cadastrado");
		}

		itemComposto.getItems().add(objeto.getItems().get(0));
		return repository.save(itemComposto);
	}

	public ItemComposto removerItem(ItemComposto objeto) {
		if (objeto.getItems().size() == 0) {
			throw new CampoNuloException("Item Não informado");
		}
		if (!repository.findById(objeto.getId()).isPresent()) {
			throw new ObjetoNaoEncontradoException("Id do orçamento não encontrado");
		}

		ItemComposto itemComposto = repository.findById(objeto.getId()).get();

		if (itemComposto == null) {
			throw new ObjetoNaoEncontradoException("Este item não está cadastrado nesta lista de items");
		} else if (!itemComposto.getItems().contains(objeto.getItems().get(0))) {
			throw new ObjetoNaoEncontradoException("Este item não está cadastrado nesta lista de items");
		}

		itemComposto.getItems().remove(objeto.getItems().get(0));
		return repository.save(itemComposto);
	}

	public Optional<ItemComposto> buscarPorId(Integer id) {
		return repository.findById(id);
	}

	public ItemComposto buscarPorNome(String nome) {
		return repository.findByNome(nome);
	}

	public void excluir(Integer id) {
		repository.deleteById(id);
	}

	public List<ItemComposto> listar() {
		return repository.findAll();
	}

}