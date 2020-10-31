package com.example.cursomc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.cursomc.domain.Pedido;
import com.example.cursomc.repository.PedidoRepository;
import com.example.cursomc.service.exception.ObjectNotFoundException;

public class PedidoService {
	@Autowired
	private PedidoRepository pedidoRepo;

	public void criarPedido(Pedido pedido) {
		pedidoRepo.save(pedido);
	}

	public List<Pedido> buacarTodos() {
		List<Pedido> Pedidos = pedidoRepo.findAll();
		return Pedidos;
	}

	public Pedido pedidoPorId(Integer id) {
		Optional<Pedido> pedido = pedidoRepo.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException(
				"Pedido não encontrada! id: " + id + ", Tipo " + Pedido.class.getName()));

	}
}
