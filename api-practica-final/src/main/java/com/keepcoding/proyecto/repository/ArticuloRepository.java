package com.keepcoding.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.keepcoding.proyecto.entity.Articulo;


@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, Long> {

	
}