package com.renataleao.atividade3.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.renataleao.atividade3.domain.AgenciaBancaria;
import com.renataleao.atividade3.domain.Conta;
import com.renataleao.atividade3.exceptions.ContaAlreadyExistsException;
import com.renataleao.atividade3.repository.ContaRepository;

@Service
public class ContaService {
	
	private ContaRepository repository;
	
	public Conta buscarTitularPorConta(int conta) {
		return repository.findByConta(conta);
	}
	
	@Transactional
	public Conta inserirNovaConta(Conta titular) throws ContaAlreadyExistsException{

		Conta existing = repository.findByTitular(titular.getTitular());

		if (existing != null) {
			throw new ContaAlreadyExistsException("Titular já existente com o nome" + titular.getTitular());
		}

		return repository.save(titular);
	}
	
	public Conta atualizarConta(int numConta, AgenciaBancaria agencia, String titular){
		Conta conta = repository.findByConta(numConta);
		if(!conta.equals(null)){
			Conta contaAux = new Conta(agencia, numConta, titular);
			contaAux.setConta(numConta);
			contaAux.setAgencia(agencia);
			contaAux.setTitular(titular);
			
			conta = contaAux;
		}
		return repository.save(conta);
	}
	
	public void deletar(int numConta){
		Conta conta = repository.findByConta(numConta);
		if(!conta.equals(null)) {
		repository.deleteById(numConta);
		}
	}
}
