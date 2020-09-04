package com.renataleao.atividade3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.renataleao.atividade3.domain.Conta;

@RepositoryRestResource(collectionResourceRel =  "conta", path = "conta")
public interface ContaRepository extends JpaRepository<Conta, Integer>{
	
	public Conta findByConta(int conta);

	public Conta findByTitular(String titular);
}
