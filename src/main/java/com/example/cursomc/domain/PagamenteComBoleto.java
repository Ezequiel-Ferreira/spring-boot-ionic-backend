package com.example.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.example.cursomc.enuns.EstadoPagamento;
import com.example.cursomc.util.DataUtil;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class PagamenteComBoleto extends Pagamento {

	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern = "dd/MM/yyyy HH:ss")
	private Date dataVencimento;
 
	@JsonFormat(pattern = "dd/MM/yyyy HH:ss")
	private Date dataPagamento;

	public PagamenteComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, String dataVencimento,
			String dataPagamento) {
		super(id, estado, pedido);
		this.dataVencimento = DataUtil.convertToDate(dataVencimento);
		this.dataPagamento = DataUtil.convertToDate(dataPagamento);
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

}
