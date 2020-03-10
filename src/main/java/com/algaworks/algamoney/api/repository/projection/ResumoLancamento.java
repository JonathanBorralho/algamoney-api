package com.algaworks.algamoney.api.repository.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.algaworks.algamoney.api.model.Lancamento;
import com.algaworks.algamoney.api.model.TipoLancamento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResumoLancamento {
	private Long id;
	private String descricao;
	private LocalDate dataVencimento;
	private LocalDate dataPagamento;
	private BigDecimal valor;
	private TipoLancamento tipo;
	private String categoria;
	private String pessoa;
	
	public static ResumoLancamento fromEntity(Lancamento l) {
		return new ResumoLancamento(
					l.getId(),
					l.getDescricao(),
					l.getDataVencimento(),
					l.getDataPagamento(),
					l.getValor(),
					l.getTipo(),
					l.getCategoria().getNome(),
					l.getPessoa().getNome()
				);
	}
}
