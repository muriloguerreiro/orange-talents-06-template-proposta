package br.com.zupacademy.murilo.proposta.config.validacao.exceptions;

import org.springframework.http.HttpStatus;

public class ApiErrorException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpStatus httpStatus;

    private String reason;

	public ApiErrorException(HttpStatus httpStatus, String reason) {
		super(reason);
		this.httpStatus = httpStatus;
		this.reason = reason;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public String getReason() {
		return reason;
	}
	
}
