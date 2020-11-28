package com.example.cursomc.resources;

import java.net.URI;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.cursomc.domain.Cidade;

import com.example.cursomc.repository.CidadeRepository;
@RestController
@RequestMapping("cidade")
public class CidadeResource {
	
	@Autowired
	private CidadeRepository CidadeRepository;

	@PostMapping("/create")
	public ResponseEntity<Void> create(@RequestBody Cidade cidade) {
		Cidade _cidade = CidadeRepository.save(cidade);
		URI uri = ServletUriComponentsBuilder.fromPath("http://localhost:8070/Cidade/getbyid/{id}").buildAndExpand(_cidade.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}
