
package br.com.zupacademy.antonio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupacademy.antonio.entities.Biometria;

public interface BiometriaRepository extends JpaRepository<Biometria, String>{
	
}
