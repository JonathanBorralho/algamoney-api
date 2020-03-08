package com.algaworks.algamoney.api.resource;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algamoney.api.model.Lancamento;
import com.algaworks.algamoney.api.repository.filter.LancamentoFilter;
import com.algaworks.algamoney.api.service.LancamentoService;
import com.algaworks.algamoney.api.service.PessoaInativaExpection;
import com.algaworks.algamoney.api.validation.model.ValidationErrorResponse;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {
	
	@Autowired
	private LancamentoService lancamentoService;
	
	@GetMapping
	public Page<Lancamento> listar(LancamentoFilter filter, Pageable pageable) {
		return lancamentoService.findAll(filter, pageable);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Lancamento salvar(@RequestBody @Valid Lancamento lancamento) {
		return lancamentoService.salvar(lancamento);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Lancamento> buscarPor(@PathVariable("id") Optional<Lancamento> lancamento) {
		return ResponseEntity.of(lancamento);
	}
	
	@ExceptionHandler(PessoaInativaExpection.class)
	public ResponseEntity<ValidationErrorResponse> handlePessoaInativaException() {
		return ResponseEntity.badRequest().body(ValidationErrorResponse.of("Pessoa informada está inativa"));
	}
}
