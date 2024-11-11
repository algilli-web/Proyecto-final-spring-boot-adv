package com.keepcoding.proyecto.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.keepcoding.proyecto.entity.Articulo;
import com.keepcoding.proyecto.entity.Cliente;
import com.keepcoding.proyecto.entity.Compra;
import com.keepcoding.proyecto.repository.CompraRepository;
import com.keepcoding.proyecto.service.CompraService;



@Service
public class CompraServiceImpl implements CompraService{

	
	@Autowired
	private CompraRepository compraRepository;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Compra> allCompras() {
		return (List<Compra>) compraRepository.findAll();
	}

	
	@Override
	@Transactional(readOnly = true)
	public Compra compraById(Long id) {
		return compraRepository.findById(id).orElse(null);
	}

	
	@Override
	@Transactional
	public Compra compraSave(Compra compra) {
		return compraRepository.save(compra);
	}

	
	@Override
	@Transactional
	public void compraDelete(Long id) {
		compraRepository.deleteById(id);
		
	}

	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> allClientes() {
		return compraRepository.findAllClientes();
	}

	
	@Override
	@Transactional(readOnly = true)
	public List<Articulo> allArticulos() {
		return compraRepository.findAllArticulos();
	}
	

}