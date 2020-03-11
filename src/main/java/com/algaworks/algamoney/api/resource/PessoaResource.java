package com.algaworks.algamoney.api.resource;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algamoney.api.model.Pessoa;
import com.algaworks.algamoney.api.repository.PessoaRepo;
import com.algaworks.algamoney.api.repository.filter.PessoaFilter;
import com.algaworks.algamoney.api.security.AppRoles;
import com.algaworks.algamoney.api.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private PessoaRepo pessoaRepo;
	
	@GetMapping
	@PreAuthorize(AppRoles.PESQUISAR_PESSOA)
	public Page<Pessoa> listar(PessoaFilter filter, Pageable pageable) {
		return pessoaService.findAll(filter, pageable);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize(AppRoles.CADASTRAR_PESSOA)
	public Pessoa salvar(@Valid @RequestBody Pessoa pessoa) {
		return pessoaRepo.save(pessoa);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize(AppRoles.CADASTRAR_PESSOA)
	public ResponseEntity<Pessoa> atualizar(@PathVariable("id") Pessoa pessoaSalva, @Valid @RequestBody Pessoa pessoa) {
		if (pessoaSalva == null) return ResponseEntity.notFound().build();
		
		BeanUtils.copyProperties(pessoa, pessoaSalva, "id");
		pessoaRepo.save(pessoaSalva);
		
		return ResponseEntity.ok(pessoaSalva);
	}
	
	@PutMapping("/{id}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize(AppRoles.CADASTRAR_PESSOA)
	public void atualizarStatusAtivo(@PathVariable("id") Optional<Pessoa> pessoa, @RequestBody Boolean isAtivo) {
		pessoa.ifPresent(p -> {
			p.setAtivo(isAtivo);
			pessoaRepo.save(p);
		});
	}
	
	@GetMapping("/{id}")
	@PreAuthorize(AppRoles.PESQUISAR_PESSOA)
	public ResponseEntity<Pessoa> buscarPor(@PathVariable("id") Optional<Pessoa> pessoa) {
		return ResponseEntity.of(pessoa);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize(AppRoles.CADASTRAR_PESSOA)
	public ResponseEntity<?> excluir(@PathVariable("id") Optional<Pessoa> pessoa) {
		if (pessoa.isPresent()) {
			pessoaRepo.delete(pessoa.get());
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}
