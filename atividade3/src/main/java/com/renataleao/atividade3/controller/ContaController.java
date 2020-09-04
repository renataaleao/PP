package com.renataleao.atividade3.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.renataleao.atividade3.domain.AgenciaBancaria;
import com.renataleao.atividade3.domain.Conta;
import com.renataleao.atividade3.exceptions.ContaAlreadyExistsException;
import com.renataleao.atividade3.service.ContaService;


@RestController
public class ContaController {
	
	@Autowired
	private ContaService contaService;

	
	@RequestMapping(value = "/conta/{id}", method = RequestMethod.GET)
	public ResponseEntity<Conta> obterTitular(@PathVariable int conta) {

		Conta titular = contaService.buscarTitularPorConta(conta);

		if (titular == null) {
			return new ResponseEntity<Conta>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Conta>(titular, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/Novaconta", method = RequestMethod.POST)
	public ResponseEntity<Conta> criarNovaConta(@RequestBody Conta conta) {

		try {
			Conta novaContaInserida = contaService.inserirNovaConta(conta);
			
			return new ResponseEntity<Conta>(novaContaInserida, HttpStatus.CREATED);

		} catch(ContaAlreadyExistsException e) {
			e.printStackTrace();
			return new ResponseEntity<Conta>(HttpStatus.CONFLICT);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Conta>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/atualizarConta", method = RequestMethod.PUT)
	public ResponseEntity<Conta> atualiza(int conta, AgenciaBancaria agencia, String titular){
	     return new ResponseEntity<Conta>(contaService.atualizarConta(conta, agencia, titular), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deletarConta", method = RequestMethod.DELETE)
	public ResponseEntity<Conta> deleta(int numConta) {
		contaService.deletar(numConta);
		return new ResponseEntity<Conta>(HttpStatus.OK);
		
	}
	
}
