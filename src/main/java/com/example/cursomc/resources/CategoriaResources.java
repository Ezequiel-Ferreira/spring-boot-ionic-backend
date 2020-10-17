package com.example.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cursomc.domain.Categoria;
import com.example.cursomc.repository.CategoriaRepository;
import com.sun.el.stream.Optional;

@RestController
@RequestMapping("categoria")
public class CategoriaResources {
	@Autowired
	CategoriaRepository categoriaRepository;
	
	
	@PostMapping("/create")
	ResponseEntity<?> create(@RequestBody Categoria categoria){
		categoriaRepository.save(categoria);
		return new ResponseEntity<>("Criado com sucesso", HttpStatus.OK);
	}
	
	@GetMapping("/categorias")
	ResponseEntity<List<Categoria>> listarCategorias() {
		List<Categoria> lista = categoriaRepository.findAll();
	
		return new ResponseEntity(lista, HttpStatus.ACCEPTED);
	}
	
}
