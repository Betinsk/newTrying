package com.example.demo.domain.enums;

public enum EstadoPagamento {

	PENDENTE(1, "Pedente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private int cod;
	private String descrição;
	
	private EstadoPagamento(Integer cod, String descrição) {
		this.cod = cod;
		this.descrição = descrição;
	}

	public int getCod() {
		return cod;
	}

	
	public String getDescrição() {
		return descrição;
	}

	public static EstadoPagamento toEnum(Integer cod)  {
		if (cod == null) {
			return null;
		}
		for(EstadoPagamento x : EstadoPagamento.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("id inválido: "+ cod);
 	}
	
	
	
}
