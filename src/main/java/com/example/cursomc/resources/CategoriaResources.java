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

import com.example.cursomc.domain.Categoria;
import com.example.cursomc.dto.CategoriaDTO;
import com.example.cursomc.repository.CategoriaRepository;
import com.example.cursomc.service.CategoriaService;

@RestController
@RequestMapping("categoria")
public class CategoriaResources {
	@Autowired
	private CategoriaService cateService;
	@Autowired
	CategoriaRepository categoriaRepository;

	@PostMapping("/create")
	public ResponseEntity<Void> create(@Valid @RequestBody CategoriaDTO categoriadto) {
		Categoria categoria = this.cateService.fromDTO(categoriadto);
		categoria = categoriaRepository.save(categoria);
		URI uri = ServletUriComponentsBuilder.fromPath("http://localhost:8070/categoria/getbyid/{id}").buildAndExpand(categoria.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping("/categorias")
	ResponseEntity<List<CategoriaDTO>> listarCategorias() {
		List<Categoria> lista = this.cateService.findAll();
		List<CategoriaDTO> listaDto = lista.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return new ResponseEntity<>(listaDto, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/categorias/{page}")
	ResponseEntity<Page<CategoriaDTO>> listCategoriasPages(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24")	Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction	
			) {
		
		Page<Categoria> lista = this.cateService.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoriaDTO> listaDto = lista.map(obj -> new CategoriaDTO(obj));
		return new ResponseEntity<>(listaDto, HttpStatus.ACCEPTED);
	}
	

	@GetMapping("/getbyid/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
		Categoria categoria = this.cateService.categoriaPorId(id);
		return new ResponseEntity<>(categoria, HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateById(@PathVariable("id") Integer id, @Valid @RequestBody CategoriaDTO categoriaDto) {
		Categoria categoria = cateService.fromDTO(categoriaDto);
		Categoria cate = cateService.updateCategoria(id, categoria);
		return new ResponseEntity<>(cate, HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> deleteCategoria(@PathVariable("id") Integer id) {
		ResponseEntity<?> cate = cateService.deleteCategoria(id);
		return new ResponseEntity<>(cate.getBody(), cate.getStatusCode());
	}

}
