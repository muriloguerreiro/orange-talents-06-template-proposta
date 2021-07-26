package br.com.zupacademy.murilo.proposta.solicitacao;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class SolicitacaoDto {
	
	@Enumerated(EnumType.STRING)
	private StatusAnalise resultadoSolicitacao;
	
	@Deprecated
	public SolicitacaoDto() {
	}

	public SolicitacaoDto(StatusAnalise status) {
		this.resultadoSolicitacao = status;
	}

	public StatusAnalise getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}
	
}
