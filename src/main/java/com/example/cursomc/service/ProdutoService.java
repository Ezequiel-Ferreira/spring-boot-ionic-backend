package com.example.cursomc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.cursomc.domain.Categoria;
import com.example.cursomc.domain.Produto;
import com.example.cursomc.repository.CategoriaRepository;
import com.example.cursomc.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository proRepo;
	@Autowired
	private CategoriaRepository cateRepo;

	public void criarProduto(Produto produto) {
		proRepo.save(produto);
	}

	public List<Produto> buacarTodos() {
		List<Produto> produtos = proRepo.findAll();
		return produtos;
	}
	
	public void addCategoria(Integer id, Categoria categoria) {
		Optional<Produto> produto = proRepo.findById(id);
		produto.get().addCategoria(categoria);
		categoria.getProdutos().add(produto.get());
		proRepo.save(produto.get());
		cateRepo.save(categoria);
	}
	
public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linePerPage, String orderBy, String direction){
		
		PageRequest pageRequest = PageRequest.of(page, linePerPage, Sort.Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = cateRepo.findAllById(ids);
		
		return proRepo.search(nome, categorias, pageRequest);
		
	}
}
