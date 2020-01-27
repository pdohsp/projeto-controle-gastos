package br.com.servise;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.entities.Usuario;
import br.com.exception.CampoExistenteException;
import br.com.exception.CampoNuloException;
import br.com.exception.LoginException;
import br.com.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	public Usuario cadastrar(Usuario usuario) {
		if (usuario.getNome() == null || usuario.getNome().equals("")) {
			throw new CampoNuloException("O campo nome não pode ser nulo");
		}
		if (usuario.getEmail() == null || usuario.getEmail().equals("")) {
			throw new CampoNuloException("O campo email não pode ser nulo");
		}
		if (repository.findByEmail(usuario.getEmail()).size() > 0) {
			throw new CampoExistenteException("Email já cadastrado");
		}
		if (usuario.getSenha() == null || usuario.getSenha().equals("")) {
			throw new CampoNuloException("O campo senha não pode ser nulo");
		}

		return repository.save(usuario);
	}

	public Usuario atualizar(Usuario objeto) {
		Usuario usuario = repository.findById(objeto.getId()).get();

		if (objeto.getNome() != null && !objeto.getNome().equals("")) {
			usuario.setNome(objeto.getNome());
		}
		if (objeto.getEmail() != null && !objeto.getEmail().equals("")) {

			if (repository.findByEmail(objeto.getEmail()).size() > 0) {
				throw new CampoExistenteException("Email já cadastrado");
			}

			usuario.setEmail(objeto.getEmail());
		}
		if (objeto.getSenha() != null && !objeto.getSenha().equals("")) {
			usuario.setSenha(objeto.getSenha());
		}
		return repository.save(usuario);
	}

	public void excluir(Integer id) {
		repository.deleteById(id);
	}

	public List<Usuario> listar() {
		return repository.findAll();
	}

	public List<Usuario> buscarPorNome(String nome) {
		return repository.findByNomeContaining(nome);
	}

	public List<Usuario> buscarPorEmail(String email) {
		return repository.findByEmailContaining(email);
	}

	public void login(Usuario objeto) {
		if (objeto.getEmail() == null || objeto.getEmail().equals("")) {
			throw new CampoNuloException("O campo email não pode ser nulo");
		}

		List<Usuario> usuario = repository.findByEmail(objeto.getEmail());

		if (usuario.isEmpty()) {
			throw new LoginException("Email incorreto");
		}
		if (!usuario.get(0).getSenha().equals(objeto.getSenha())) {
			throw new LoginException("Senha incorreta");
		}
	}

}