package com.keepcoding.proyecto.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keepcoding.proyecto.entity.Articulo;
import com.keepcoding.proyecto.service.ArticuloService;


@RestController
@RequestMapping("/api/articulos")
@CrossOrigin(origins = "*")
public class ArticuloController {

    @Autowired
    private ArticuloService service;

    @GetMapping
    public ResponseEntity<?> listarArticulos() {
        List<Articulo> lista = service.allArticulos();
        Map<String, String> response = new HashMap<>();
        
        if(lista.size() > 0) {
            return ResponseEntity.ok(lista);
        } else {
            response.put("mensaje", "No hay artículos registrados");
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerArticulo(@PathVariable Long id) {
        Articulo articulo = service.articuloById(id);
        Map<String, String> response = new HashMap<>();
        
        if(articulo != null) {
            return ResponseEntity.ok(articulo);
        } else {
            response.put("mensaje", "No hay artículo con este identificador id=" + id);
            return ResponseEntity.ok(response);
        }
    }

    @PostMapping
    public ResponseEntity<?> crearArticulo(@RequestBody Articulo articulo) {
        Articulo nuevoArticulo = null;
        Map<String, Object> response = new HashMap<>();
        
        try {
            nuevoArticulo = service.articuloSave(articulo);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al crear el artículo en base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        
        response.put("mensaje", "Artículo creado con éxito");
        response.put("articulo", nuevoArticulo);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> actualizarArticulo(@PathVariable Long id, @RequestBody Articulo articulo) {
        Articulo articuloActualizado = null;
        Map<String, Object> response = new HashMap<>();
        Articulo articuloExistente = service.articuloById(id);
        
        if(articuloExistente == null) {
            response.put("mensaje", "No existe artículo con id: " + id);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        
        try {
            articuloExistente.setNombre(articulo.getNombre());
            articuloExistente.setDescripcion(articulo.getDescripcion());
            articuloExistente.setUnidadPrecio(articulo.getUnidadPrecio());
            articuloExistente.setUnidadStock(articulo.getUnidadStock());
            articuloExistente.setTipo(articulo.getTipo());
            articuloExistente.setProveedor(articulo.getProveedor());
            articuloExistente.setFecha(articulo.getFecha());
            
            articuloActualizado = service.articuloSave(articuloExistente);
            
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el artículo en base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        
        response.put("mensaje", "Artículo actualizado con éxito");
        response.put("articulo", articuloActualizado);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarArticulo(@PathVariable Long id) {
        Articulo articulo = null;
        Map<String, Object> response = new HashMap<>();
        
        articulo = service.articuloById(id);
        
        if(articulo == null) {
            response.put("mensaje", "No existe artículo con id: " + id);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        
        try {
            service.articuloDelete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar el artículo en base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        
        response.put("mensaje", "Artículo eliminado con éxito");
        response.put("articulo", articulo);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}