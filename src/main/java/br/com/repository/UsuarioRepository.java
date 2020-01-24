package br.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	
	List<Usuario> findByNomeContaining(String nome);
	
	List<Usuario> findByEmailContaining(String email);

	List<Usuario> findByEmail(String email);
	
	List<Usuario> findBySenha(String senha);
}
