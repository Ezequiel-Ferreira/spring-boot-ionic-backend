package com.example.cursomc.repository;

import java.util.List;


import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.cursomc.domain.Categoria;
import com.example.cursomc.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepositoryImplementation<Produto, Integer>{
	
	@Query("SELECT DISTINCT obj FROM Produto obj WHERE INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias;")
	Page<Produto> search(@Param("nome")String nome, @Param("categorias")List<Categoria> categorias, Pageable  pageRequest);  
}
