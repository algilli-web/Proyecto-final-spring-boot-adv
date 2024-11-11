package com.keepcoding.proyecto.service;

import java.util.List;

import com.keepcoding.proyecto.entity.Cliente;


public interface ClienteService {
	
	public List<Cliente> allClientes();
	
	public Cliente clienteById(Long id);

	public Cliente clienteSave(Cliente cliente);
	
	public void clienteDelete(Long id);

}