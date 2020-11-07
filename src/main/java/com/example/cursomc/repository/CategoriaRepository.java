package com.example.cursomc.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;


import com.example.cursomc.domain.Categoria;


public interface CategoriaRepository extends JpaRepositoryImplementation<Categoria, Integer>{
	
}
