package cl.duoc.integracion.apiwebservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cl.duoc.integracion.apiwebservice.Entidades.Producto;
import cl.duoc.integracion.apiwebservice.Repositorios.ProductoRepository;
import cl.duoc.integracion.apiwebservice.Servicios.ProductoService;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/productos")
public class ProductoController{

    @Autowired
    private ProductoService productoService;

    
    @GetMapping
    public List<Producto> listarTodo() {
        return productoService.listarProducto();
    }
    
    @PostMapping
    public Producto creaProducto(@RequestBody Producto producto) {
        return productoService.crearProducto(producto);
    }
    
    
}
