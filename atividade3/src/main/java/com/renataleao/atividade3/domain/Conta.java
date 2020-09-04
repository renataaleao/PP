package com.renataleao.atividade3.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Conta {
	
	private AgenciaBancaria agencia;
	@Id
	private int conta;
	@Column(length = 50)
	private String titular;
	private double saldo;
	
	public Conta(AgenciaBancaria agencia, int conta, String titular) {
		this.agencia = agencia;
		this.conta = conta;
		this.titular = titular;
	}
	
	
	
}
