package br.com.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class ItemComposto extends Item {

	private List<Item> items = new ArrayList<>();

	public ItemComposto() {
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "item_composto_item")
	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public Double getValor() {
		double retorno = 0;

		for (Item item : items) {
			retorno += item.getValor();
		}

		return retorno;
	}

}