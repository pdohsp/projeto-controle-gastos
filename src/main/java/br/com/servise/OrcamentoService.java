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
		if (objeto.getId() == null) {
			throw new CampoNuloException("Informe o id do orçamento");
		}
		if (!repository.findById(objeto.getId()).isPresent()) {
			throw new ObjetoNaoEncontradoException("Id do orçamento não encontrado");
		}

		if (objeto.getItems().get(0).getId() == null) {

			if (objeto.getItems().get(0).getNome() == null) {
				throw new CampoNuloException("Informe o nome do item");
			}
			if (objeto.getItems().get(0).getValor() == null) {
				throw new CampoNuloException("Informe o valor do item");
			}

			itemService.cadastrar(objeto.getItems().get(0));

		} else if (itemCompostoService.buscarPorId(objeto.getItemsCompostos().get(0).getId()) == null) {
			throw new ObjetoNaoEncontradoException("Id não cadastrado");
		}

		Orcamento orcamento = repository.findById(objeto.getId()).get();

		if (orcamento.getItems().contains(objeto.getItems().get(0))) {
			throw new CampoExistenteException("O item  " + objeto.getItems().get(0).getNome() + ", já foi cadastrado");
		}

		orcamento.getItems().add(objeto.getItems().get(0));
		return repository.save(orcamento);
	}

	public Orcamento removerItem(Orcamento objeto) {
		if (objeto.getId() == null) {
			throw new CampoNuloException("Informe o id do orçamento");
		}
		if (objeto.getItems().get(0).getId() == null) {
			throw new CampoNuloException("Informe o id do item");
		}
		if (!repository.findById(objeto.getId()).isPresent()) {
			throw new ObjetoNaoEncontradoException("Id do orçamento não encontrado");
		}

		Orcamento orcamento = repository.findById(objeto.getId()).get();

		if (orcamento == null) {
			throw new ObjetoNaoEncontradoException("Id do orçamento não encontrado");
		} else if (!orcamento.getItems().contains(objeto.getItems().get(0))) {
			throw new ObjetoNaoEncontradoException("Item não está cadastrado neste orcamento");
		}

		orcamento.getItems().remove(objeto.getItems().get(0));
		return repository.save(orcamento);
	}

	public Orcamento addItemComposto(Orcamento objeto) {
		if (objeto.getId() == null) {
			throw new CampoNuloException("Informe o id do orçamento");
		}
		if (!repository.findById(objeto.getId()).isPresent()) {
			throw new ObjetoNaoEncontradoException("Id do orçamento não encontrado");
		}

		if (objeto.getItemsCompostos().get(0).getId() == null) {

			if (objeto.getItemsCompostos().get(0).getNome() == null) {
				throw new CampoNuloException("Informe o nome do itemComposto");
			}

			itemService.cadastrar(objeto.getItems().get(0));

		} else if (!itemCompostoService.buscarPorId(objeto.getItemsCompostos().get(0).getId()).isPresent()) {
			throw new ObjetoNaoEncontradoException("Id do itemComposto não cadastrado");
		}

		Orcamento orcamento = repository.findById(objeto.getId()).get();

		if (orcamento.getItemsCompostos().contains(objeto.getItemsCompostos().get(0))) {
			throw new CampoExistenteException(
					"O item " + objeto.getItemsCompostos().get(0).getNome() + ", já foi cadastrado");
		}

		orcamento.getItemsCompostos().add(objeto.getItemsCompostos().get(0));
		return repository.save(orcamento);
	}

	public Orcamento removerItemComposto(Orcamento objeto) {
		if (objeto.getId() == null) {
			throw new CampoNuloException("Informe o id do orçamento");
		}
		if (objeto.getItemsCompostos().get(0).getId() == null) {
			throw new CampoNuloException("Informe o id do itemComposto");
		}
		if (!repository.findById(objeto.getId()).isPresent()) {
			throw new ObjetoNaoEncontradoException("Id do itemComposto não cadastrado");
		}

		Orcamento orcamento = repository.findById(objeto.getId()).get();

		if (!orcamento.getItemsCompostos().contains(objeto.getItemsCompostos().get(0))) {
			throw new ObjetoNaoEncontradoException("ItemComposto não está cadastrado neste orcamento");
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