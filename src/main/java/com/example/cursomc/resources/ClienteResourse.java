package com.example.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cursomc.domain.Cliente;
import com.example.cursomc.service.ClienteService;

@RestController
@RequestMapping("cliente")
public class ClienteResourse {

	@Autowired
	private ClienteService clienteService;
	
	
	@PostMapping("/create")
	public ResponseEntity<?> criarCliente(@RequestBody Cliente cliente){
		clienteService.cadastrar(cliente);
		return new ResponseEntity<>("Cadstrado com sucesso!", HttpStatus.OK);
	}
	
	@GetMapping("/getbyid/{id}")
	public ResponseEntity<Cliente> getVById(@PathVariable("id") Integer id){
		Cliente cliente = clienteService.clientePorId(id);
		return new ResponseEntity<>(cliente, HttpStatus.OK);
	}
}
