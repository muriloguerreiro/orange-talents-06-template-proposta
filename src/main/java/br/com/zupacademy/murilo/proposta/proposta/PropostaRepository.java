package br.com.zupacademy.murilo.proposta.proposta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupacademy.murilo.proposta.associacao.Cartao;
import br.com.zupacademy.murilo.proposta.solicitacao.StatusProposta;

public interface PropostaRepository extends JpaRepository<Proposta,Long>{
	
	List<Proposta> findByStatusAndCartao(StatusProposta status, Cartao cartao);
	
}
