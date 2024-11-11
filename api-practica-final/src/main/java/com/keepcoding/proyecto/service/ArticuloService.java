package com.keepcoding.proyecto.service;

import java.util.List;

import com.keepcoding.proyecto.entity.Articulo;


public interface ArticuloService {
	
	public List<Articulo> allArticulos();
	
	public Articulo articuloById(Long id);

	public Articulo articuloSave(Articulo articulo);
	
	public void articuloDelete(Long id);
	
}