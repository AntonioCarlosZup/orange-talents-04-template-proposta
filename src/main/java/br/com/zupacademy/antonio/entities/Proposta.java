package br.com.zupacademy.antonio.entities;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import br.com.zupacademy.antonio.enuns.Status;

@Entity
public class Proposta {

	public Proposta(String cpfCnpj, String email, String nome, String endereco, BigDecimal salario) {
		this.cpfCnpj = cpfCnpj;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}
	
	 public Proposta(String cpfCnpj, String email, String nome, 
  		   String endereco, BigDecimal salario, Cartao cartao) {
  	this.cpfCnpj = cpfCnpj;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
		this.cartao = cartao;
	}

	@Deprecated
	public Proposta() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String cpfCnpj;
	private String nome;
	private String email;
	private String endereco;
	private BigDecimal salario;

	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private Cartao cartao;

	@Enumerated(EnumType.STRING)
	private Status status;

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

	public String getEndereco() {
		return endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setStatus(Status status) {
		this.status = status;

	}

	public Cartao getCartao() {
		return cartao;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}

}
