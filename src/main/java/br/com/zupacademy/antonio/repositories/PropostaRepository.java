package br.com.zupacademy.antonio.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zupacademy.antonio.entities.Proposta;
import br.com.zupacademy.antonio.enuns.Status;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long>{

	Optional<Proposta> findByCpfCnpj(String cpfOrCnpj);
	
	List<Proposta> findByStatusAndCartaoId(Status status, String id);
	
}
