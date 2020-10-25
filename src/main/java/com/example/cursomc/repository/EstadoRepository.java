package com.example.cursomc.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.example.cursomc.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepositoryImplementation<Estado, Integer>{
	
}
