package br.com.zupacademy.murilo.proposta.solicitacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zupacademy.murilo.proposta.proposta.Proposta;
import feign.FeignException;

@Service
public class AnaliseSolicitacaoService {
	
	@Autowired
	private AnaliseSolicitacao analiseSolicitacao;
	
	public StatusAnalise analisar(Proposta proposta) {
		try {
			SolicitacaoForm solicitacaoForm = new SolicitacaoForm(proposta);
			SolicitacaoDto solicitacaoDto = analiseSolicitacao.enviar(solicitacaoForm);
			return solicitacaoDto.getResultadoSolicitacao();
		} catch (FeignException.UnprocessableEntity e) {
			return StatusAnalise.COM_RESTRICAO;
		}
	}
}
