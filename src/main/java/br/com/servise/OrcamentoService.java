package br.com.servise;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.entities.Orcamento;
import br.com.repository.OrcamentoRepository;

@Service
public class OrcamentoService {

	@Autowired
	private OrcamentoRepository repository;

	public Orcamento cadastrar(Orcamento orcamento) {
		orcamento.setData(new Date());
		return repository.save(orcamento);
	}
	
	public Orcamento atualizar(Orcamento orcamento) {
//		Orcamento orcamento = repository.findById(objeto.getId()).get();
		orcamento.setData(new Date());
		
//		if (objeto.getNome() != null)
//			usuario.setNome(objeto.getNome());
//		
//		if (objeto.getSenha() != null)
//			usuario.setSenha(objeto.getSenha());
		return repository.save(orcamento);
	}

	public void excluir(Integer id) {
		repository.deleteById(id);
	}

	public List<Orcamento> listar() {
		return repository.findAll();
	}
}