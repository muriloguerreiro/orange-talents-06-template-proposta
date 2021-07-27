package br.com.zupacademy.murilo.proposta.associacao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "associacao", url = "${api.associacao.url}")
public interface AssociacaoCartao {
	
	@PostMapping("/api/cartoes")
	AssociacaoDto solicitar(AssociacaoForm associacaoForm);

}
