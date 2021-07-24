package br.com.zupacademy.murilo.proposta.config.validacao.anotacoes;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.zupacademy.murilo.proposta.config.validacao.validadores.ValidaValorUnico;

@Documented
@Constraint(validatedBy = {ValidaValorUnico.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValorUnico {
	
	String message() default "O campo deve ser único";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	String fieldName();
	Class<?> domainClass();
	
}
