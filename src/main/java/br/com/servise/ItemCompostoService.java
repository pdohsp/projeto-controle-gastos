package br.com.servise;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.entities.ItemComposto;
import br.com.exception.CampoExistenteException;
import br.com.exception.CampoNuloException;
import br.com.repository.ItemCompostoRepository;

@Service
public class ItemCompostoService {

	@Autowired
	private ItemCompostoRepository repository;

	@Autowired
	private ItemService itemService;

	public ItemComposto cadastrar(ItemComposto itemComposto) {
		if (repository.findByNome(itemComposto.getNome()).size() > 0)
			throw new CampoExistenteException("Já existe um item composto cadastrado com este nome");

		return repository.save(itemComposto);
	}

	public ItemComposto atualizarNome(ItemComposto objeto) {
		ItemComposto itemComposto = repository.findById(objeto.getId()).get();

		if (objeto.getNome() != null) {
			itemComposto.setNome(objeto.getNome());
		}
		return repository.save(itemComposto);
	}

	public ItemComposto addItem(ItemComposto objeto) {
		if (objeto.getItems().size() == 0) {
			throw new CampoNuloException("Item Não informado");
		}
		ItemComposto itemComposto = repository.findById(objeto.getId()).get();

		if (itemService.buscarPorNome(objeto.getItems().get(0).getNome()).size() == 0) {
			itemService.cadastrar(objeto.getItems().get(0));
		} else {
			for (int i = 0; i < itemComposto.getItems().size(); i++) {
				if (itemComposto.getItems().get(i).getNome().equals(objeto.getItems().get(0).getNome()))
					throw new CampoExistenteException(
							"O item  " + objeto.getItems().get(0).getNome() + ", já foi cadastrado neste ItemComposto");
			}
		}

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