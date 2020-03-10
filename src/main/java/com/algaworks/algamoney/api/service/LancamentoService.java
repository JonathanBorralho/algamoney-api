package com.algaworks.algamoney.api.service;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.algaworks.algamoney.api.model.Lancamento;
import com.algaworks.algamoney.api.model.Pessoa;
import com.algaworks.algamoney.api.model.QLancamento;
import com.algaworks.algamoney.api.repository.LancamentoRepo;
import com.algaworks.algamoney.api.repository.PessoaRepo;
import com.algaworks.algamoney.api.repository.filter.LancamentoFilter;
import com.algaworks.algamoney.api.repository.projection.ResumoLancamento;
import com.querydsl.core.BooleanBuilder;

@Service
public class LancamentoService {
	
	@Autowired
	private LancamentoRepo lancamentoRepo;
	
	@Autowired
	private PessoaRepo pessoaRepo;
	
	public Lancamento salvar(Lancamento lancamento) {
		Optional<Pessoa> pessoa = pessoaRepo.findById(lancamento.getPessoa().getId());
		pessoa.ifPresent(p -> {
			if (p.isInativo())
			throw new PessoaInativaExpection();
		});
		
		return lancamentoRepo.save(lancamento);
	}
	
	public Page<Lancamento> findAll(LancamentoFilter filter, Pageable pageable) {
		QLancamento lancamento = QLancamento.lancamento;
		BooleanBuilder builder = new BooleanBuilder();
		
		if (StringUtils.isNotBlank(filter.getDescricao())) {
			builder.and(lancamento.descricao.containsIgnoreCase(filter.getDescricao()));
		}
		
		if (filter.getDataVencimentoDe() != null) {
			builder.and(lancamento.dataVencimento.goe(filter.getDataVencimentoDe()));
		}
		
		if (filter.getDataVencimentoAte() != null) {
			builder.and(lancamento.dataVencimento.loe(filter.getDataVencimentoAte()));
		}
		
		return lancamentoRepo.findAll(builder, pageable);
	}
	
	public Page<ResumoLancamento> resumir(LancamentoFilter filter, Pageable pageable) {
		return findAll(filter, pageable)
				.map(ResumoLancamento::fromEntity);
	}
}
