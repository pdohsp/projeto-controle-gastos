package br.com.dto;

import br.com.entities.Item;

public class ItemDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private Double valor;
	
	public ItemDTO() {
	}
	
	public ItemDTO(Item item) {
		this.setId(item.getId());
		this.setNome(item.getNome());
		this.setValor(item.getValor());
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

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
}