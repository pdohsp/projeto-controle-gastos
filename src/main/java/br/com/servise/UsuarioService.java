package br.com.servise;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dto.UsuarioDTO;
import br.com.entities.Usuario;
import br.com.exception.CampoExistenteException;
import br.com.exception.CampoNuloException;
import br.com.exception.LoginException;
import br.com.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	public UsuarioDTO cadastrar(Usuario usuario) {
		if (usuario.getNome() == null || usuario.getNome().equals("")) {
			throw new CampoNuloException("O campo nome não pode ser nulo");
		}
		if (usuario.getEmail() == null || usuario.getEmail().equals("")) {
			throw new CampoNuloException("O campo email não pode ser nulo");
		}
		if (repository.findByEmail(usuario.getEmail()) != null) {
			throw new CampoExistenteException("Email já cadastrado");
		}
		if (usuario.getSenha() == null || usuario.getSenha().equals("")) {
			throw new CampoNuloException("O campo senha não pode ser nulo");
		}
		
		UsuarioDTO usuarioDTO = new UsuarioDTO(repository.save(usuario));
		return  usuarioDTO;
	}

	public UsuarioDTO atualizar(Usuario objeto) {
		Usuario usuario = repository.findById(objeto.getId()).get();

		if (objeto.getNome() != null && !objeto.getNome().equals("")) {
			usuario.setNome(objeto.getNome());
		}
		if (objeto.getEmail() != null && !objeto.getEmail().equals("")) {

			if (repository.findByEmail(objeto.getEmail()) != null) {
				throw new CampoExistenteException("Email já cadastrado");
			}

			usuario.setEmail(objeto.getEmail());
		}
		if (objeto.getSenha() != null && !objeto.getSenha().equals("")) {
			usuario.setSenha(objeto.getSenha());
		}
		
		UsuarioDTO usuarioDTO = new UsuarioDTO(repository.save(usuario));
		return  usuarioDTO;
	}

	public void excluir(Integer id) {
		repository.deleteById(id);
	}

	public List<UsuarioDTO> listar() {
		return repository.findAll().stream().map(x -> new UsuarioDTO(x)).collect(Collectors.toList());
	}

	public List<UsuarioDTO> buscarPorNome(String nome) {
		return repository.findByNomeContaining(nome).stream().map(x -> new UsuarioDTO(x)).collect(Collectors.toList());
	}

	public List<UsuarioDTO> buscarPorEmail(String email) {
		return repository.findByEmailContaining(email).stream().map(x -> new UsuarioDTO(x)).collect(Collectors.toList());
	}

	public void login(Usuario objeto) {
		if (objeto.getEmail() == null || objeto.getEmail().equals("")) {
			throw new CampoNuloException("O campo email não pode ser nulo");
		}

		Usuario usuario = repository.findByEmail(objeto.getEmail());

		if (usuario == null) {
			throw new LoginException("Email incorreto");
		}
		if (!usuario.getSenha().equals(objeto.getSenha())) {
			throw new LoginException("Senha incorreta");
		}
	}

}