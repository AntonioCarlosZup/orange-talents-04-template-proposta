package br.com.zupacademy.antonio.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Biometria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String fingerprint;
	
	@ManyToOne
	private Cartao cartao; 

	public Biometria(String fingerprint, Cartao cartao) {
		super();
		this.fingerprint = fingerprint;
		this.cartao = cartao;
	}

	@Deprecated
	public Biometria() {}

	public Long getId() {
		return id;
	}

	public String getFingerprint() {
		return fingerprint;
	}

	public Cartao getCartao() {
		return cartao;
	}
	
}
