package com.keepcoding.proyecto.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.keepcoding.proyecto.entity.Articulo;
import com.keepcoding.proyecto.repository.ArticuloRepository;
import com.keepcoding.proyecto.service.ArticuloService;



@Service
public class ArticuloServiceImpl implements ArticuloService {

	
	@Autowired
	private ArticuloRepository articuloRepository;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Articulo> allArticulos() {
		return (List<Articulo>) articuloRepository.findAll();
	}

	
	@Override
	@Transactional(readOnly = true)
	public Articulo articuloById(Long id) {
		return articuloRepository.findById(id).orElse(null);
	}

	
	@Override
	@Transactional
	public Articulo articuloSave(Articulo articulo) {
		return articuloRepository.save(articulo);
	}

	
	@Override
	@Transactional
	public void articuloDelete(Long id) {
		articuloRepository.deleteById(id);
		
	}

}