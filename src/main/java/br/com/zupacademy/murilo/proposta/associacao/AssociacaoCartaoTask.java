package br.com.zupacademy.murilo.proposta.associacao;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.zupacademy.murilo.proposta.proposta.Proposta;
import br.com.zupacademy.murilo.proposta.proposta.PropostaRepository;
import br.com.zupacademy.murilo.proposta.solicitacao.StatusProposta;
import feign.FeignException;

@Component
public class AssociacaoCartaoTask {

	private Logger log = LoggerFactory.getLogger(AssociacaoCartaoTask.class);

	@Autowired
	private AssociacaoCartao associacaoCartao;

	@Autowired
	private PropostaRepository propostaRepository;

	@Scheduled(fixedDelay = 30000)
	@Transactional
	public void executar() {
		List<Proposta> propostas = propostaRepository.findByStatusAndCartao(StatusProposta.ELEGIVEL,null);

		for (Proposta proposta : propostas) {
			try {
				AssociacaoForm associacaoForm = new AssociacaoForm(proposta.getDocumento(), proposta.getNome(), proposta.getId());
				AssociacaoDto associacaoDto = associacaoCartao.solicitar(associacaoForm);

				proposta.associaCartao(associacaoDto);		
				propostaRepository.save(proposta);

				log.info("Cartão criado com sucesso para a proposta {}", proposta.getId());

			} catch (FeignException e) {
				 log.error("Não foi possível criar um cartão para a proposta {}", proposta.getId());
			}
		}
	}

}
