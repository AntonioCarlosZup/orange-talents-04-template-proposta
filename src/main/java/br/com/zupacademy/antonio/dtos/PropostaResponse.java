package br.com.zupacademy.antonio.dtos;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import br.com.zupacademy.antonio.entities.Proposta;
import br.com.zupacademy.antonio.enuns.Status;

public class PropostaResponse {
  
	private Long id;
	private String cpfCnpj;
	private String email;
	private String nome;
	private String endereco;
	private BigDecimal salario;
	private Status status;
	
	public PropostaResponse(Proposta proposta) {
		
		this.id = proposta.getId();
		this.cpfCnpj = proposta.getCpfCnpj();
		this.email = proposta.getEmail();
		this.nome = proposta.getNome();
		this.endereco = proposta.getEndereco();
		this.salario = proposta.getSalario();
		this.status = proposta.getStatus();
	}
	
	@Deprecated
	public PropostaResponse() {}
	
	public Long getId() {
		return id;
	}
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	public String getEmail() {
		return email;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}
	public BigDecimal getSalario() {
		return salario;
	}
	
	public Status getStatus() {
		return status;
	}

	public List<PropostaResponse> coverterLista(List<Proposta> propostas){
		
		return propostas
				.stream()
				.map(PropostaResponse::new)
				.collect(Collectors.toList());
	}
	
}
