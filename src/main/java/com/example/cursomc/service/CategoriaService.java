package com.example.cursomc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cursomc.domain.Categoria;
import com.example.cursomc.repository.CategoriaRepository;
import com.example.cursomc.service.exception.ObjectNotFoundException;



@Service
public class CategoriaService {
 
	@Autowired
	private CategoriaRepository cateRepo;
	
	
	public void criarCategoria(Categoria categoria) {
		cateRepo.save(categoria);
	}
	
	
	public List<Categoria> buacarTodos(){
		List<Categoria> categorias = cateRepo.findAll();
		return categorias;
	}
	
	
	public Categoria categoriaPorId(Integer id){
		Optional<Categoria> cate = cateRepo.findById(id);
			return cate.orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada! id: " +
		id + ", Tipo " + Categoria.class.getName()));
		
	}
}
