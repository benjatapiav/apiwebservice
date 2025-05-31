package cl.duoc.integracion.apiwebservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cl.duoc.integracion.apiwebservice.DTO.ProductoDTO;
import cl.duoc.integracion.apiwebservice.Entidades.Producto;
import cl.duoc.integracion.apiwebservice.Servicios.ProductoService;

import java.util.Optional;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/productos")
public class ProductoController{

    @Autowired
    private ProductoService productoService;

    //Listar todos los productos
    @GetMapping
    public ResponseEntity<List<Producto>> listarProducto() {
        List<Producto> productoData = productoService.listarProducto();
        if(!productoData.isEmpty()){
            return new ResponseEntity<>(productoData,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Obtener Producto por Id
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id) {
        Optional<Producto> productoData = productoService.obtenerProductoPorId(id);
        if(productoData.isPresent()){
            return new ResponseEntity<>(productoData.get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Obtener Producto por Codigo
    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<Producto> obtenerProductoPorCodigo(@PathVariable String codigo) {
        Optional<Producto> productoData = productoService.obtenerProductoPorCodigo(codigo);
        if(productoData.isPresent()){
            return new ResponseEntity<>(productoData.get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Listar Producto por Marca
    @GetMapping("/marca/{marca}")
    public ResponseEntity<List<Producto>> listarProductoPorMarca(@PathVariable String marca) {
        List<Producto> productoData = productoService.listarProductoPorMarcaContainingIgnoreCase(marca);
        if(!productoData.isEmpty()){
            return new ResponseEntity<>(productoData,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(productoData,HttpStatus.NOT_FOUND);
        }

    }

    //Listar Producto por Categoria
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Producto>> listarProductoPorCategoria(@PathVariable String categoria) {
        List<Producto> productoData = productoService.listarProductoPorCategoriaContainingIgnoreCase(categoria);
        if(!productoData.isEmpty()){
            return new ResponseEntity<>(productoData,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(productoData,HttpStatus.NOT_FOUND);
        }

    }

    ////Listar Producto por Nombre
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<Producto>> listarProductoPorNombreContainingIgnoreCase(@PathVariable String nombre) {
        List<Producto> productoData = productoService.listarProductoPorNombreContainingIgnoreCase(nombre);
        if(!productoData.isEmpty()){
            return new ResponseEntity<>(productoData,HttpStatus.OK);
            
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    //Listar Producto por Sucursal
    @GetMapping("/sucursal/{sucursal}")
    public ResponseEntity<List<Producto>> listarProductoPorSucursalContainingIgnoreCase(@PathVariable String sucursal) {
        List<Producto> productoData = productoService.listarProductoPorSucursalContainingIgnoreCase(sucursal);
        if(!productoData.isEmpty()){
            return new ResponseEntity<>(productoData,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Crear un nuevo producto POST
    @PostMapping
    public ResponseEntity<String> crearProducto(@Valid @RequestBody ProductoDTO productoDTO) {
            productoService.crearProducto(productoDTO);
            return new ResponseEntity<>("Producto de codigo: " + productoDTO.getCodigoProducto() + " creado con exito", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @Valid @RequestBody Producto producto) {
                Producto productoActualizado = productoService.actualizarProducto(id, producto);
                
                return new ResponseEntity<>(productoActualizado, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Producto> actualizarParteProducto(@PathVariable Long id,@RequestBody Map<String, Object> campos) {
            Producto productoActualizado = productoService.actualizarParteDeProducto(id, campos);
            return new ResponseEntity<>(productoActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
            productoService.eliminarProducto(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}