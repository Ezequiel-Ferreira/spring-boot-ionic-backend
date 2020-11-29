package com.example.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cursomc.domain.Categoria;
import com.example.cursomc.domain.Produto;
import com.example.cursomc.dto.ProdutoDTO;
import com.example.cursomc.resources.util.URL;
import com.example.cursomc.service.ProdutoService;

@CrossOrigin("http://localhost:8100")
@RequestMapping("produto")
@RestController
public class ProdutoResourse {
	
	@Autowired
	private ProdutoService proService;
	
	@PostMapping("/create")
	ResponseEntity<?> create(@RequestBody Produto produto){
		proService.criarProduto(produto);
		return new ResponseEntity<>("Criado com sucesso", HttpStatus.OK);
	}
	
	@GetMapping("/produtos/{page}")
	ResponseEntity<Page<ProdutoDTO>> listProdutosPages(@RequestParam(value ="nome", defaultValue ="") String nome,
			@RequestParam(value ="categorias", defaultValue ="") String categorias,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24")	Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction	
			) {
		String nomeDecode = URL.decodeParan(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
		Page<Produto> lista = this.proService.search(nomeDecode, ids, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listaDto = lista.map(obj -> new ProdutoDTO(obj));
		return new ResponseEntity<>(listaDto, HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/produtos")
	public ResponseEntity<?> getProdutos(){
		List<Produto> produtos = proService.buacarTodos();
		return new ResponseEntity<List<Produto>>(produtos, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/addProduto/{id}")
	public void addProduto(@RequestBody Categoria categoria,  @PathVariable("id") Integer id) {
		proService.addCategoria(id, categoria);
	}
}
