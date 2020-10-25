package com.example.cursomc.enuns;


public enum TipoCliente {

	PESSOAJURIDICA(1, "Pessoa Jurídica"),
	PESSOAFISICA(2, "Pessoa Fisica");
	
	
	private Integer cod;
	private String descricao;
	
	private TipoCliente(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoCliente toEnun(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(TipoCliente tc : TipoCliente.values()) {
			if(cod.equals(tc.getCod())) {
				return tc;
			}
		}
		throw new IllegalArgumentException("O código " + cod + " é inválido!");
	}
	
}
