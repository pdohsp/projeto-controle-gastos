package br.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.entities.ItemComposto;

public interface ItemCompostoRepository extends JpaRepository<ItemComposto, Integer> {

	List<ItemComposto> findByNome(String nome);
}
