package br.com.zupacademy.antonio.dtos;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import br.com.zupacademy.antonio.anotacoes.CPFouCNPJ;
import br.com.zupacademy.antonio.entities.Proposta;

public class PropostaForm {
	
	@Deprecated
	public PropostaForm() {}
	
	@NotBlank//NotBlank ja tem notNull e notEmpty
	@CPFouCNPJ
	private String cpfCnpj;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String endereco;
	
	@Positive
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



	public Proposta converter() {
		
		return new Proposta(cpfCnpj, email, nome, endereco, salario);
	}
}

