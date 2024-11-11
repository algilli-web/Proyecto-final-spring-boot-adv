package com.keepcoding.proyecto.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.keepcoding.proyecto.entity.Cliente;
import com.keepcoding.proyecto.repository.ClienteRepository;
import com.keepcoding.proyecto.service.ClienteService;


@Service
public class ClienteServiceImpl implements ClienteService {

	
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> allClientes() {
		return (List<Cliente>) clienteRepository.findAll();
	}

	
	@Override
	@Transactional(readOnly = true)
	public Cliente clienteById(Long id) {
		return clienteRepository.findById(id).orElse(null);
	}

	
	@Override
	@Transactional
	public Cliente clienteSave(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	
	@Override
	@Transactional
	public void clienteDelete(Long id) {
		clienteRepository.deleteById(id);
		
	}

}