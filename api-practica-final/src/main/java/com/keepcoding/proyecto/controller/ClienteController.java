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

import com.keepcoding.proyecto.entity.Cliente;
import com.keepcoding.proyecto.service.ClienteService;


@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	
    @Autowired
    private ClienteService service;
	
    @GetMapping
    public ResponseEntity<?> listarClientes() {
        List<Cliente> clientes = service.allClientes();
		
        Map<String,String> response = new HashMap<>();
		
        if(!clientes.isEmpty()) {
            return ResponseEntity.ok(clientes);
        } else {
            response.put("mensaje", "No existen clientes registrados");
            return ResponseEntity.ok(response);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerCliente(@PathVariable Long id) {
        Cliente cliente = service.clienteById(id);
        Map<String,String> response = new HashMap<>();
		
        if(cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            response.put("mensaje", "Cliente no encontrado con id: " + id);
            return ResponseEntity.ok(response);
        }		
    }

    @PostMapping
    public ResponseEntity<?> crearCliente(@RequestBody Cliente cliente) {
        Cliente nuevoCliente = null;
        Map<String,Object> response = new HashMap<>();
		
        try {
            nuevoCliente = service.clienteSave(cliente);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al crear el cliente en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
		
        response.put("mensaje", "Cliente creado exitosamente");
        response.put("cliente", nuevoCliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> actualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente clienteActualizado = null;
        Map<String,Object> response = new HashMap<>();
        Cliente clienteExistente = service.clienteById(id);
		
        if(clienteExistente == null) {
            response.put("mensaje", "No existe cliente con id: " + id);
            return ResponseEntity.ok(response);
        }
		
        try {
            clienteExistente.setApellido(cliente.getApellido());
            clienteExistente.setCodigoPostal(cliente.getCodigoPostal());
            clienteExistente.setCompras(cliente.getCompras());
            clienteExistente.setDireccion(cliente.getDireccion());
            clienteExistente.setEmpresa(cliente.getEmpresa());
            clienteExistente.setFechaNacimiento(cliente.getFechaNacimiento());
            clienteExistente.setNombre(cliente.getNombre());
            clienteExistente.setProvincia(cliente.getProvincia());
            clienteExistente.setPuesto(cliente.getPuesto());
            clienteExistente.setTelefono(cliente.getTelefono());
			
            clienteActualizado = service.clienteSave(clienteExistente);
			
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el cliente");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
		
        response.put("mensaje", "Cliente actualizado exitosamente");
        response.put("cliente", clienteActualizado);
        return ResponseEntity.ok(response);
    }
	
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarCliente(@PathVariable Long id) {
        Cliente cliente = null;
        Map<String,Object> response = new HashMap<>();
		
        cliente = service.clienteById(id);
		
        if(cliente == null) {
            response.put("mensaje", "No existe cliente con id: " + id);
            return ResponseEntity.ok(response);
        }
		
        try {
            service.clienteDelete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar el cliente");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
		
        response.put("mensaje", "Cliente eliminado exitosamente");
        response.put("cliente", cliente);
        return ResponseEntity.ok(response);
    }
}