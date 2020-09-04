package com.renataleao.atividade3.domain;

import lombok.Data;

@Data
public class AgenciaBancaria {
	
	private int numeroAgencia;

	public int getNumeroAgencia() {
		return numeroAgencia;
	}

	public void setNumeroAgencia(int numeroAgencia) {
		this.numeroAgencia = numeroAgencia;
	}
	
	
}
