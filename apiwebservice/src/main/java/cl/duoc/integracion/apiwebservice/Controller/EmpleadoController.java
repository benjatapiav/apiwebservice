package cl.duoc.integracion.apiwebservice.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cl.duoc.integracion.apiwebservice.DTO.ProductoDTO;
import cl.duoc.integracion.apiwebservice.Entidades.Producto;
import cl.duoc.integracion.apiwebservice.Servicios.EmpleadoService;
import cl.duoc.integracion.apiwebservice.Servicios.ProductoService;

import java.util.Optional;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("api/empleados")
public class EmpleadoController {
    
    @Autowired
    private EmpleadoService empleadoService;
}
