package com.algaworks.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.algaworks.algamoney.api.model.Pessoa;

public interface PessoaRepo extends JpaRepository<Pessoa, Long>, QuerydslPredicateExecutor<Pessoa> {

}
