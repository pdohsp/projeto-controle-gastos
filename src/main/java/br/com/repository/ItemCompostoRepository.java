package br.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.entities.ItemComposto;

public interface ItemCompostoRepository extends JpaRepository<ItemComposto, Integer> {

	ItemComposto findByNome(String nome);
}
