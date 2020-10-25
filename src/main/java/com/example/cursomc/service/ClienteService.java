package com.example.cursomc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cursomc.domain.Cliente;
import com.example.cursomc.repository.ClienteRepository;
import com.example.cursomc.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepo;
	
	public void cadastrar(Cliente cliente) {
		clienteRepo.save(cliente);
	}
	
	public Cliente clientePorId(Integer id) {
		Optional<Cliente> cliente = clienteRepo.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado! id: " +
				id + ", Tipo " + Cliente.class.getName()));
	}
}
