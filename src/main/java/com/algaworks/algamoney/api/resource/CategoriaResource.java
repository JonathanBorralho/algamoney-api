package com.algaworks.algamoney.api.resource;


import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algamoney.api.model.Categoria;
import com.algaworks.algamoney.api.repository.CategoriaRepo;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaRepo categoriaRepo;
	
	@GetMapping
	public Page<Categoria> listar(Pageable pageable) {
		return categoriaRepo.findAll(pageable);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('ROLE_PERQUISAR_CATEGORIA')")
	public Categoria salvar(@Valid @RequestBody Categoria categoria) {
		return categoriaRepo.save(categoria);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> buscarPor(@PathVariable("id") Optional<Categoria> categoria) {
		return ResponseEntity.of(categoria);
	}
}
