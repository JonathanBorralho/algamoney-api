package com.algaworks.algamoney.api.config.security;

public abstract class AppRoles {
	public static final String CADASTRAR_LANCAMENTO = "hasRole('CADASTRAR_LANCAMENTO')";
	public static final String PESQUISAR_LANCAMENTO = "hasRole('PESQUISAR_LANCAMENTO')";
	
	public static final String CADASTRAR_CATEGORIA = "hasRole('CADASTRAR_CATEGORIA')";
	public static final String PESQUISAR_CATEGORIA = "hasRole('PESQUISAR_CATEGORIA')";
	
	public static final String CADASTRAR_PESSOA = "hasRole('CADASTRAR_PESSOA')";
	public static final String PESQUISAR_PESSOA = "hasRole('PESQUISAR_PESSOA')";
}

