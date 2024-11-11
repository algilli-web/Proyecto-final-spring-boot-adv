package com.keepcoding.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.keepcoding.proyecto.entity.Cliente;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}