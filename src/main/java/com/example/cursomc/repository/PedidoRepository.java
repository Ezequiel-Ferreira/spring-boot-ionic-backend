package com.example.cursomc.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.example.cursomc.domain.Pedido;

public interface PedidoRepository extends JpaRepositoryImplementation<Pedido, Integer> {

}
