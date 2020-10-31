package com.example.cursomc.enuns;

public enum EstadoPagamento {
	
	PENTENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private Integer cod;
	private String descricao;
	
	private EstadoPagamento(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static EstadoPagamento toEnun(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(EstadoPagamento estado : EstadoPagamento.values()) {
			if(cod.equals(estado.getCod())) {
				return estado;
			}
		}
		throw new IllegalArgumentException("O código " + cod + " é inválido!");
	}

}
