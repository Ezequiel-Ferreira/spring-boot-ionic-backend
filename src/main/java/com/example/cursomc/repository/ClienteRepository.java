package com.example.cursomc.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.example.cursomc.domain.Cliente;

public interface ClienteRepository extends JpaRepositoryImplementation<Cliente, Integer>{

}
