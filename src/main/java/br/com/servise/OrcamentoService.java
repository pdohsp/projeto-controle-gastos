package br.com.servise;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.entities.Orcamento;
import br.com.exception.CampoExistenteException;
import br.com.exception.CampoNuloException;
import br.com.exception.ObjetoNaoEncontradoException;
import br.com.repository.OrcamentoRepository;

@Service
public class OrcamentoService {

	@Autowired
	private OrcamentoRepository repository;

	@Autowired
	private ItemService itemService;

	@Autowired
	ItemCompostoService itemCompostoService;
	
	public Orcamento cadastrar(Orcamento orcamento) {
		orcamento.setData(new Date());
		return repository.save(orcamento);
	}

	public Orcamento addItem(Orcamento objeto) {
		if (objeto.getItems().size() == 0) {
			throw new CampoNuloException("Item Não informado");
		}

		Orcamento orcamento = repository.findById(objeto.getId()).get();

		if (itemService.buscarPorNome(objeto.getItems().get(0).getNome()) == null) {
			itemService.cadastrar(objeto.getItems().get(0));
		} else if (orcamento.getItems().contains(objeto.getItems().get(0))) {
			throw new CampoExistenteException("O item  " + objeto.getItems().get(0).getNome() + ", já foi cadastrado");
		}

		orcamento.getItems().add(objeto.getItems().get(0));
		return repository.save(orcamento);
	}

	public Orcamento removerItem(Orcamento objeto) {
		if (objeto.getItems().size() == 0) {
			throw new CampoNuloException("Item Não informado");
		}

		Orcamento orcamento = repository.findById(objeto.getId()).get();

		if (orcamento == null) {
			throw new ObjetoNaoEncontradoException("Item não está cadastrado nesta lista de items");
		} else if (!orcamento.getItems().contains(objeto.getItems().get(0))) {
			throw new ObjetoNaoEncontradoException("Item não está cadastrado nesta lista de items");
		}

		orcamento.getItems().remove(objeto.getItems().get(0));
		return repository.save(orcamento);
	}

	public Orcamento addItemComposto(Orcamento objeto) {
		if (objeto.getItemsCompostos().size() == 0) {
			throw new CampoNuloException("Item Não informado");
		}

		Orcamento orcamento = repository.findById(objeto.getId()).get();

		if (itemCompostoService.buscarPorNome(objeto.getItemsCompostos().get(0).getNome()) == null) {
			itemCompostoService.cadastrar(objeto.getItemsCompostos().get(0));
		} else if (orcamento.getItemsCompostos().contains(objeto.getItemsCompostos().get(0))) {
			throw new CampoExistenteException("O item  " + objeto.getItemsCompostos().get(0).getNome() + ", já foi cadastrado");
		}
		
		orcamento.getItemsCompostos().add(objeto.getItemsCompostos().get(0));
		return repository.save(orcamento);
	}

	public Orcamento removerItemComposto(Orcamento objeto) {
		if (objeto.getItemsCompostos().size() == 0) {
			throw new CampoNuloException("Item Não informado");
		}

		Orcamento orcamento = repository.findById(objeto.getId()).get();

		if (orcamento == null) {
			throw new ObjetoNaoEncontradoException("Item não está cadastrado neste orcamento");
		} else if (!orcamento.getItemsCompostos().contains(objeto.getItemsCompostos().get(0))) {
			throw new ObjetoNaoEncontradoException("Item não está cadastrado neste orcamento");
		}

		orcamento.getItemsCompostos().remove(objeto.getItemsCompostos().get(0));
		return repository.save(orcamento);
	}
	
	public void excluir(Integer id) {
		repository.deleteById(id);
	}

	public List<Orcamento> listar() {
		return repository.findAll();
	}

}