package br.com.zupacademy.antonio.dtos;

import java.math.BigDecimal;

public class PropostaDto {

	private String cpfCnpj;
	
	private String nome;
	
	private String email;
	
	private String endereco;
	
	private BigDecimal salario;

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getEndereco() {
		return endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}
	
}

