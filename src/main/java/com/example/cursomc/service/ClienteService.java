package com.example.cursomc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cursomc.domain.Cidade;
import com.example.cursomc.domain.Cliente;
import com.example.cursomc.domain.Endereco;
import com.example.cursomc.dto.ClienteDTO;
import com.example.cursomc.dto.ClienteDTONew;
import com.example.cursomc.repository.CidadeRepository;
import com.example.cursomc.repository.ClienteRepository;
import com.example.cursomc.service.exception.DataIntegrityException;
import com.example.cursomc.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepo;
	@Autowired
	private CidadeRepository cidRepo;
	
	
	@Transactional
	public Cliente createCliente(Cliente cliente) {
		try {

			if (cliente.getId() != null) {
				cliente.setId(null);
			}
			return clienteRepo.save(cliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Cliente fromDTO(ClienteDTO clienteDto) {
		return new Cliente(clienteDto.getId(), clienteDto.getNome(), clienteDto.getEmail(), clienteDto.getTipo());
	}

	public Cliente fromDTO(ClienteDTONew clienteDto) {
		Cliente cliente = new Cliente(null, clienteDto.getNome(), clienteDto.getEmail(), clienteDto.getTipo());
		
		cliente = clienteRepo.save(cliente);

		Cidade cidade = cidRepo.findById(clienteDto.getCidadeId()).get();

		Endereco endereco = new Endereco(null, clienteDto.getLagradouro(), clienteDto.getNumero(),
				clienteDto.getComplemento(), clienteDto.getBairro(), clienteDto.getCep(), cliente, cidade);
		
		
		cliente.addEndereco(endereco);
		cliente.getTelefones().add(clienteDto.getTelefone1());
		if (clienteDto.getTelefone2() != null) {
			cliente.getTelefones().add(clienteDto.getTelefone2());
		}
		if (clienteDto.getTelefone3() != null) {
			cliente.getTelefones().add(clienteDto.getTelefone3());
		}
		
		return cliente;
	}

	public List<Cliente> findAll() {
		List<Cliente> Clientes = clienteRepo.findAll();
		return Clientes;
	}

	public Page<Cliente> findPage(Integer page, Integer linePerPage, String orderBy, String direction) {

		PageRequest pageRequest = PageRequest.of(page, linePerPage, Sort.Direction.valueOf(direction), orderBy);
		return clienteRepo.findAll(pageRequest);

	}

	public Cliente clientePorId(Integer id) {
		Optional<Cliente> cliente = clienteRepo.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
				"Cliente não encontrada! id: " + id + ", Tipo " + Cliente.class.getName()));
	}

	public Cliente clientePorIdTradicional(Integer id) {
		Optional<Cliente> cliente = clienteRepo.findById(id);
		return cliente.get();
	}

	public Cliente updateCliente(Integer id, Cliente cliente) {
		Cliente _cliente = clientePorId(id);
		if (cliente != null) {
			if (cliente.getNome() != null) {
				_cliente.setNome(cliente.getNome());
			}
			if (cliente.getEmail() != null) {
				_cliente.setEmail(cliente.getNome());
			}
			if (cliente.getTipo() != null) {
				_cliente.setTipo(cliente.getTipo());
			}

			return clienteRepo.save(cliente);
		}

		return null;
	}

	public ResponseEntity<?> deleteCliente(Integer id) {
		Cliente cliente = clientePorId(id);
		try {
			clienteRepo.deleteById(cliente.getId());
			return new ResponseEntity<>("Deletado com sucesso!", HttpStatus.ACCEPTED);

		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Impossivel excluir um Cliente com produtos cadastrado!");

		}

	}

}
