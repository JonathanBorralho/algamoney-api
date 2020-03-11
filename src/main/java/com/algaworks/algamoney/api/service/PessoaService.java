package com.algaworks.algamoney.api.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.algaworks.algamoney.api.model.Pessoa;
import com.algaworks.algamoney.api.model.QPessoa;
import com.algaworks.algamoney.api.repository.PessoaRepo;
import com.algaworks.algamoney.api.repository.filter.PessoaFilter;
import com.querydsl.core.BooleanBuilder;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepo pessoaRepo;
	
	public Page<Pessoa> findAll(PessoaFilter filter, Pageable pageable) {
		QPessoa pessoa = QPessoa.pessoa;
		BooleanBuilder builder = new BooleanBuilder();
		
		if (StringUtils.isNotBlank(filter.getNome())) {
			builder.and(pessoa.nome.containsIgnoreCase(filter.getNome()));
		}
		
		return pessoaRepo.findAll(builder, pageable);
	}
}
