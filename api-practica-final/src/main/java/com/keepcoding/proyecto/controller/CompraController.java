package com.keepcoding.proyecto.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keepcoding.proyecto.entity.Compra;
import com.keepcoding.proyecto.service.CompraService;


@RestController
@RequestMapping("/api/compras")
public class CompraController {
	
    @Autowired
    private CompraService service;

    @GetMapping
    public ResponseEntity<?> listarCompras() {
        List<Compra> compras = service.allCompras();
		
        Map<String,String> response = new HashMap<>();
		
        if(!compras.isEmpty()) {
            return ResponseEntity.ok(compras);
        } else {
            response.put("mensaje", "No existen compras registradas");
            return ResponseEntity.ok(response);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerCompra(@PathVariable Long id) {
        Compra compra = service.compraById(id);
        Map<String,String> response = new HashMap<>();
		
        if(compra != null) {
            return ResponseEntity.ok(compra);
        } else {
            response.put("mensaje", "Compra no encontrada con id: " + id);
            return ResponseEntity.ok(response);
        }		
    }
    
    @PostMapping
    public ResponseEntity<?> crearCompra(@RequestBody Compra compra) {
        Compra nuevaCompra = null;
        Map<String,Object> response = new HashMap<>();
		
        try {
            nuevaCompra = service.compraSave(compra);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al crear la compra en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
		
        response.put("mensaje", "Compra creada exitosamente");
        response.put("compra", nuevaCompra);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
	
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> actualizarCompra(@PathVariable Long id, @RequestBody Compra compra) {
        Compra compraActualizada = null;
        Map<String,Object> response = new HashMap<>();
        Compra compraExistente = service.compraById(id);
		
        if(compraExistente == null) {
            response.put("mensaje", "No existe compra con id: " + id);
            return ResponseEntity.ok(response);
        }
		
        try {
            compraExistente.setArticulo(compra.getArticulo());
            compraExistente.setCantidad(compra.getCantidad());
            compraExistente.setCliente(compra.getCliente());
            compraExistente.setFecha(compra.getFecha());
            compraExistente.setIva(compra.getIva());
            compraExistente.setTotal(compra.getTotal());
            compraExistente.setTotal_iva(compra.getTotal_iva());
			
            compraActualizada = service.compraSave(compraExistente);
			
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar la compra");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
		
        response.put("mensaje", "Compra actualizada exitosamente");
        response.put("compra", compraActualizada);
        return ResponseEntity.ok(response);
    }
	
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarCompra(@PathVariable Long id) {
        Compra compra = null;
        Map<String,Object> response = new HashMap<>();
		
        compra = service.compraById(id);
		
        if(compra == null) {
            response.put("mensaje", "No existe compra con id: " + id);
            return ResponseEntity.ok(response);
        }
		
        try {
            service.compraDelete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar la compra");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
		
        response.put("mensaje", "Compra eliminada exitosamente");
        response.put("compra", compra);
        return ResponseEntity.ok(response);
    }
}