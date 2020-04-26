package br.com.dto;

import br.com.entities.Usuario;

public class UsuarioDTO implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private String email;
	
	public UsuarioDTO() {
	}

	public UsuarioDTO(Usuario usuario) {
		this.setId(usuario.getId());
		this.setNome(usuario.getNome());
		this.setEmail(usuario.getEmail());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}