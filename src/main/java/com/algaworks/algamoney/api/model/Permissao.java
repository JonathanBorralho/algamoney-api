package com.algaworks.algamoney.api.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "permissao")
public class Permissao extends AbstractEntity {
	
	private static final long serialVersionUID = 8156450969990665356L;

	@NotNull
	private String nome;
}
