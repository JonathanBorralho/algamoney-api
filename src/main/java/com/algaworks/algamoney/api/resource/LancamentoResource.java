package com.algaworks.algamoney.api.resource;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algamoney.api.model.Lancamento;
import com.algaworks.algamoney.api.repository.LancamentoRepo;
import com.algaworks.algamoney.api.repository.filter.LancamentoFilter;
import com.algaworks.algamoney.api.security.AppRoles;
import com.algaworks.algamoney.api.service.LancamentoService;
import com.algaworks.algamoney.api.service.PessoaInativaExpection;
import com.algaworks.algamoney.api.validation.model.ValidationErrorResponse;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {
	
	@Autowired
	private LancamentoRepo lancamentoRepo;
	
	@Autowired
	private LancamentoService lancamentoService;
	
	@GetMapping
	@PreAuthorize(AppRoles.PESQUISAR_LANCAMENTO)
	public Page<Lancamento> listar(LancamentoFilter filter, Pageable pageable) {
		return lancamentoService.findAll(filter, pageable);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize(AppRoles.CADASTRAR_LANCAMENTO)
	public Lancamento salvar(@RequestBody @Valid Lancamento lancamento) {
		return lancamentoService.salvar(lancamento);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize(AppRoles.PESQUISAR_LANCAMENTO)
	public ResponseEntity<Lancamento> buscarPor(@PathVariable("id") Optional<Lancamento> lancamento) {
		return ResponseEntity.of(lancamento);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize(AppRoles.CADASTRAR_LANCAMENTO)
	public ResponseEntity<?> excluir(@PathVariable("id") Optional<Lancamento> lancamento) {
		if (lancamento.isPresent()) {
			lancamentoRepo.delete(lancamento.get());
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(PessoaInativaExpection.class)
	public ResponseEntity<ValidationErrorResponse> handlePessoaInativaException() {
		return ResponseEntity.badRequest().body(ValidationErrorResponse.of("Pessoa informada está inativa"));
	}
}
