package br.com.zupacademy.murilo.proposta.config.validacao.validadores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.murilo.proposta.config.validacao.anotacoes.ValorUnico;
import br.com.zupacademy.murilo.proposta.config.validacao.exceptions.ApiErrorException;

public class ValidaValorUnico implements ConstraintValidator<ValorUnico, Object> {
	
	private String domainAttribute;
	private Class<?> klass;
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public void initialize(ValorUnico params) {
		domainAttribute = params.fieldName();
		klass = params.domainClass();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		Query query = manager.createQuery("select l from " + klass.getName() + " l where " + domainAttribute + " = :pValue");
		query.setParameter("pValue", value);
		List<?> list = query.getResultList();
		Assert.state(list.size() <= 1, "Foi encontrado mais de um(a) " + klass	+ " com o atributo" + domainAttribute + " = " + value);
		

		if (list.size() == 1) {
			throw new ApiErrorException(HttpStatus.UNPROCESSABLE_ENTITY, domainAttribute + " informado(a) ja cadastrado(a)");
		}
		
		return list.isEmpty();
	}

}
