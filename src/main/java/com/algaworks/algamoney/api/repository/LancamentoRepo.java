package com.algaworks.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.algamoney.api.model.Lancamento;

public interface LancamentoRepo extends JpaRepository<Lancamento, Long> {

}
