package br.com.zupacademy.murilo.proposta.biometria;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupacademy.murilo.proposta.associacao.Cartao;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {
	
}
