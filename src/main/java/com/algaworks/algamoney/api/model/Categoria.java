package com.algaworks.algamoney.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "categoria")
public class Categoria extends AbstractEntity {
	
	private static final long serialVersionUID = 8245639713461273060L;
	
	@NotBlank
	@Column(name = "nome")
	private String nome;

}
