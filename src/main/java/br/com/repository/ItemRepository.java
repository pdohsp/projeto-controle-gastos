package br.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.entities.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

	Item findByNome(String nome);
}
