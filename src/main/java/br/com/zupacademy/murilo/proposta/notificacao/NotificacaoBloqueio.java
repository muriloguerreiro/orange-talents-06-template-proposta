package br.com.zupacademy.murilo.proposta.notificacao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "notificacao", url = "${api.notificacao.url}")
public interface NotificacaoBloqueio {

	@PostMapping("/{id}/bloqueios")
	void enviar(@PathVariable("id") String id, NotificacaoForm notificacaoForm);
}
