package com.example.cursomc.repository;



import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.transaction.annotation.Transactional;

import com.example.cursomc.domain.Cliente;

public interface ClienteRepository extends JpaRepositoryImplementation<Cliente, Integer>{
	@Transactional(readOnly = true)
	Cliente findByEmail(String email);

}
