package br.com.zupacademy.antonio.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupacademy.antonio.entities.Bloqueio;
import br.com.zupacademy.antonio.enuns.Resultado;

public interface BloqueioRepository extends JpaRepository<Bloqueio, Long>{

	Optional<Bloqueio> findByResultadoAndCartaoId(Resultado resultado, String cartaoId);
}
