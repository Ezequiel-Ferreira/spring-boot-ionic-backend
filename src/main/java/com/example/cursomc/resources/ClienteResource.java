package com.example.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.cursomc.domain.Cliente;
import com.example.cursomc.dto.ClienteDTO;
import com.example.cursomc.repository.ClienteRepository;
import com.example.cursomc.service.ClienteService;

@RestController
@RequestMapping("cliente")
public class ClienteResource {
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private ClienteRepository clienteRepository;

	@PostMapping("/create")
	public ResponseEntity<Void> create(@Valid @RequestBody ClienteDTO clienteDto) {
		Cliente Cliente = this.clienteService.fromDTO(clienteDto);
		Cliente = clienteRepository.save(Cliente);
		URI uri = ServletUriComponentsBuilder.fromPath("http://localhost:8070/cliente/getbyid/{id}").buildAndExpand(Cliente.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping("/clientes")
	public ResponseEntity<List<ClienteDTO>> listarClientes() {
		List<Cliente> lista = this.clienteService.findAll();
		List<ClienteDTO> listaDto = lista.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return new ResponseEntity<>(listaDto, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/pageclientes/{page}")
	 public ResponseEntity<Page<ClienteDTO>> listClientesPages(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24")	Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction	
			) {
		
		Page<Cliente> lista = this.clienteService.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> listaDto = lista.map(obj -> new ClienteDTO(obj));
		return new ResponseEntity<>(listaDto, HttpStatus.ACCEPTED);
	}
	

	@GetMapping("/getbyid/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
		Cliente cliente = this.clienteService.clientePorIdTradicional(id);
		return new ResponseEntity<>(cliente, HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateById(@PathVariable("id") Integer id, @Valid @RequestBody ClienteDTO ClienteDto) {
		Cliente Cliente = clienteService.fromDTO(ClienteDto);
		Cliente cliente = clienteService.updateCliente(id, Cliente);
		return new ResponseEntity<>(cliente, HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> deleteCliente(@PathVariable("id") Integer id) {
		ResponseEntity<?> cliente = clienteService.deleteCliente(id);
		return new ResponseEntity<>(cliente.getBody(), cliente.getStatusCode());
	}

}
