package com.algaworks.algamoney.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algamoney.api.model.Lancamento;
import com.algaworks.algamoney.api.model.Pessoa;
import com.algaworks.algamoney.api.repository.LancamentoRepo;
import com.algaworks.algamoney.api.repository.PessoaRepo;

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
}
