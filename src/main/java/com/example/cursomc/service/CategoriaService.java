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

import com.example.cursomc.domain.Categoria;
import com.example.cursomc.repository.CategoriaRepository;
import com.example.cursomc.service.exception.DataIntegrityException;
import com.example.cursomc.service.exception.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository cateRepo;

	public Categoria createCategoria(Categoria categoria) {
		try {

			if (categoria.getId() != null) {
				categoria.setId(null);
			}
			return cateRepo.save(categoria);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Categoria> findAll() {
		List<Categoria> categorias = cateRepo.findAll();
		return categorias;
	}
	
	public Page<Categoria> findPage(Integer page, Integer linePerPage, String orderBy, String direction){
		
		PageRequest pageRequest = PageRequest.of(page, linePerPage, Sort.Direction.valueOf(direction), orderBy);
		return cateRepo.findAll(pageRequest);
		
	}

	public Categoria categoriaPorId(Integer id) {
		Optional<Categoria> cate = cateRepo.findById(id);
		return cate.orElseThrow(() -> new ObjectNotFoundException(
				"Categoria não encontrada! id: " + id + ", Tipo " + Categoria.class.getName()));

	}

	public Categoria updateCategoria(Integer id, Categoria categoria) {
		Categoria cate = categoriaPorId(id);
		if (cate != null) {
			if (categoria.getNome() != null) {
				cate.setNome(categoria.getNome());
			}
			return cateRepo.save(cate);
		}

		return null;
	}

	public ResponseEntity<?> deleteCategoria(Integer id) {
		Categoria cate = categoriaPorId(id);
		try {
			cateRepo.deleteById(cate.getId());
			return new ResponseEntity<>("Deletado com sucesso!", HttpStatus.ACCEPTED);

		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Impossivel excluir uma categoria com produtos cadstrado!");

		}

	}

}
