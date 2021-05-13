package br.com.zupacademy.antonio.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zupacademy.antonio.dtos.PropostaDto;
import br.com.zupacademy.antonio.entities.Proposta;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long>{

	Optional<Proposta> findByCpfCnpj(String cpfOrCnpj);

}
