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
@Table(name = "usuario")
public class Usuario extends AbstractEntity {
	
	private static final long serialVersionUID = 4909550865888713780L;

	@NotNull
	private String nome;
	
	@NotNull
	private String email;
	
	@NotNull
	private String senha;
	
	@ManyToMany
	@JoinTable(
		name = "usuario_grupo",
		joinColumns = @JoinColumn(name = "id_usuario"),
		inverseJoinColumns = @JoinColumn(name = "id_grupo")
	)
	private List<Grupo> grupos;
}
