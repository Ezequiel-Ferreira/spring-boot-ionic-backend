package com.example.cursomc.resources;

import java.net.URI;

import java.util.List;
import java.util.stream.Collectors;

import com.example.cursomc.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.cursomc.domain.Categoria;
import com.example.cursomc.dto.CategoriaDTO;
import com.example.cursomc.service.CategoriaService;

@RestController
@RequestMapping("categoria")
public class CategoriaResources {
	@Autowired
	private CategoriaService cateService;
	@Autowired
	CategoriaRepository categoriaRepository;

	@PostMapping("/create")
	public ResponseEntity<Void> create(@RequestBody Categoria categoria) {
		categoria = this.cateService.criarCategoria(categoria);
		categoria = categoriaRepository.save(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping("/categorias")
	ResponseEntity<List<CategoriaDTO>> listarCategorias() {
		List<Categoria> lista = this.cateService.buscarTodos();
		List<CategoriaDTO> listaDto = lista.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return new ResponseEntity<>(listaDto, HttpStatus.ACCEPTED);
	}

	@GetMapping("/getbyid/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
		Categoria categoria = this.cateService.categoriaPorId(id);
		return new ResponseEntity<>(categoria, HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateById(@PathVariable("id") Integer id, @RequestBody Categoria categoria) {
		Categoria cate = cateService.updateCategoria(id, categoria);
		return new ResponseEntity<>(cate, HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> deleteCategoria(@PathVariable("id") Integer id) {
		ResponseEntity<?> cate = cateService.deleteCategoria(id);
		return new ResponseEntity<>(cate.getBody(), cate.getStatusCode());
	}

}
