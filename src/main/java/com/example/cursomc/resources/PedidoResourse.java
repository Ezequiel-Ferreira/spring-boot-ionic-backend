package com.example.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.cursomc.domain.Pedido;
import com.example.cursomc.service.PedidoService;

public class PedidoResourse {
	@Autowired
	private PedidoService pedidoService;
	
	@PostMapping("/create")
	ResponseEntity<?> create(@RequestBody Pedido Pedido){
		pedidoService.criarPedido(Pedido);
		return new ResponseEntity<>("Criado com sucesso", HttpStatus.OK);
	}
	
	@GetMapping("/Pedidos")
	ResponseEntity<List<Pedido>> listarPedidos() {
		List<Pedido> lista = new ArrayList<Pedido>();
		lista = pedidoService.buacarTodos();
	
		return new ResponseEntity<List<Pedido>>(lista, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getbyid/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Integer id){
		Pedido Pedido = pedidoService.pedidoPorId(id);
		return new ResponseEntity<>(Pedido, HttpStatus.OK);
	}
	
}
