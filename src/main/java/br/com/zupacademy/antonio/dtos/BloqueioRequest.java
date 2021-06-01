package br.com.zupacademy.antonio.dtos;

import javax.validation.constraints.NotBlank;

public class BloqueioRequest {

	@NotBlank
	private String sistemaResponsavel;

	public String getSistemaResponsavel() {
		return sistemaResponsavel;
	}

}
