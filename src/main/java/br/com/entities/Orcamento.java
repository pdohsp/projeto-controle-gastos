package br.com.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Orcamento implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Date data;
	private Double valorTotal;
	private Usuario autor;
	private List<Item> items = new ArrayList<>();
	private List<ItemComposto> itemsCompostos = new ArrayList<>();

	public Orcamento() {
	}

	public Orcamento(Integer id, Date data, Usuario altor) {
		this.id = id;
		this.data = data;
		this.autor = altor;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(nullable = false)
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Column
	public double getValorTotal() {
		this.valorTotal = 0.0;

		for (Item item : items) {
			valorTotal += item.getValor();
		}

		for (ItemComposto itemComposto : itemsCompostos) {
			valorTotal += itemComposto.getValor();
		}

		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	@ManyToOne
	@JoinColumn(name = "id_autor", nullable = false)
	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario altor) {
		this.autor = altor;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "orcamento_item")
	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "Orcamento_Item_composto")
	public List<ItemComposto> getItemsCompostos() {
		return itemsCompostos;
	}

	public void setItemsCompostos(List<ItemComposto> itemsCompostos) {
		this.itemsCompostos = itemsCompostos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orcamento other = (Orcamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}