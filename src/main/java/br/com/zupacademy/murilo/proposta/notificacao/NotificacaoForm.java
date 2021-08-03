package br.com.zupacademy.murilo.proposta.notificacao;

import javax.validation.constraints.NotBlank;

public class NotificacaoForm {
	
	@NotBlank
	private String sistemaResponsavel;

	public NotificacaoForm(@NotBlank String sistemaResponsavel) {
		this.sistemaResponsavel = sistemaResponsavel;
	}

	public String getSistemaResponsavel() {
		return sistemaResponsavel;
	}
	
}
