package com.example.cursomc.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.example.cursomc.domain.Cidade;

@Repository
public interface CidadeRepository extends JpaRepositoryImplementation<Cidade, Integer>{
	
}

