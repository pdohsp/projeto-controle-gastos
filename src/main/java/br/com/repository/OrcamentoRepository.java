package br.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.entities.Orcamento;

public interface OrcamentoRepository extends JpaRepository<Orcamento, Integer> {

}
