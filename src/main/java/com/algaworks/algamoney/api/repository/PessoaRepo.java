package com.algaworks.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.algamoney.api.model.Pessoa;

public interface PessoaRepo extends JpaRepository<Pessoa, Long> {

}
