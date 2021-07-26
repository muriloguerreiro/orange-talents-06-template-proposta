package br.com.zupacademy.murilo.proposta.solicitacao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "analise", url = "${api.analise.url}")
public interface AnaliseSolicitacao {
	
	@PostMapping("/api/solicitacao")
	SolicitacaoDto enviar(SolicitacaoForm solicitacaoForm);
	
}
