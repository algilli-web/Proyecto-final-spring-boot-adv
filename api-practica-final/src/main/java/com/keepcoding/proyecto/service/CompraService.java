package com.keepcoding.proyecto.service;

import java.util.List;

import com.keepcoding.proyecto.entity.Articulo;
import com.keepcoding.proyecto.entity.Cliente;
import com.keepcoding.proyecto.entity.Compra;



public interface CompraService {
	
	public List<Compra> allCompras();
	
	public Compra compraById(Long id);

	public Compra compraSave(Compra compra);
	
	public void compraDelete(Long id);
	
	public List<Cliente> allClientes();
	
	public List<Articulo> allArticulos();
	

}