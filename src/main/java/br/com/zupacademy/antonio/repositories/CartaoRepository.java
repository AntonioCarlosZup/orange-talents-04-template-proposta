package br.com.zupacademy.antonio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupacademy.antonio.entities.Cartao;

public interface CartaoRepository extends JpaRepository<Cartao, String>{

}
