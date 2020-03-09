package com.algaworks.algamoney.api.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "grupo")
public class Grupo extends AbstractEntity {
	
	private static final long serialVersionUID = -8362264155444088236L;

	@NotNull
	private String nome;
	
	@ManyToMany
	@JoinTable(
		name = "grupo_permissao",
		joinColumns = @JoinColumn(name = "id_grupo"),
		inverseJoinColumns = @JoinColumn(name = "id_permissao")
	)
	private List<Permissao> permissoes;
}
