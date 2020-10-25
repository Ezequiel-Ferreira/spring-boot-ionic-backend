package com.example.cursomc.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.example.cursomc.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepositoryImplementation<Produto, Integer>{

}
