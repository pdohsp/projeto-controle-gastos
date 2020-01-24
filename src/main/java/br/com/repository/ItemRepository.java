package br.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.entities.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

	List<Item> findByNome(String nome);
}
