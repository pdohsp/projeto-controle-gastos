package br.com.servise;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.entities.Orcamento;
import br.com.exception.CampoExistenteException;
import br.com.exception.CampoNuloException;
import br.com.repository.OrcamentoRepository;

@Service
public class OrcamentoService {

	@Autowired
	private OrcamentoRepository repository;

	@Autowired
	private ItemService itemService;

	public Orcamento cadastrar(Orcamento orcamento) {
		orcamento.setData(new Date());
		return repository.save(orcamento);
	}

	public Orcamento addItem(Orcamento objeto) {
		if (objeto.getItems().size() == 0) {
			throw new CampoNuloException("Item Não informado");
		}

		Orcamento orcamento = repository.findById(objeto.getId()).get();

		if (itemService.buscarPorNome(objeto.getItems().get(0).getNome()).size() == 0) {
			itemService.cadastrar(objeto.getItems().get(0));
		} else {
			for (int i = 0; i < orcamento.getItems().size(); i++) {
				
				if (orcamento.getItems().get(i).getNome().equals(objeto.getItems().get(0).getNome()))
					throw new CampoExistenteException(
							"O item  " + objeto.getItems().get(0).getNome() + ", já foi cadastrado neste orçamento");
			}
		}
		
		orcamento.getItems().add(objeto.getItems().get(0));
		return repository.save(orcamento);
	}

	public Orcamento addItemComposto(Orcamento orcamento) {

		orcamento.setData(new Date());

		return repository.save(orcamento);
	}

	public void excluir(Integer id) {
		repository.deleteById(id);
	}

	public List<Orcamento> listar() {
		return repository.findAll();
	}
}