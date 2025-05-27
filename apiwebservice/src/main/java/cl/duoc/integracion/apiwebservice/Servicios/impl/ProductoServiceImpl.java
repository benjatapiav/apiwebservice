package cl.duoc.integracion.apiwebservice.Servicios.impl;


import cl.duoc.integracion.apiwebservice.Entidades.Producto;
import cl.duoc.integracion.apiwebservice.Repositorios.ClienteRepository;
import cl.duoc.integracion.apiwebservice.Repositorios.EmpleadoRepository;
import cl.duoc.integracion.apiwebservice.Repositorios.HistorialDePrecioRepository;
import cl.duoc.integracion.apiwebservice.Repositorios.ProductoRepository;

import cl.duoc.integracion.apiwebservice.Servicios.ProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;

@Service
public class ProductoServiceImpl implements ProductoService{

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private ClienteRepository clienteRepository;


    @Autowired
    private HistorialDePrecioRepository historialDePrecioRepository;

    // PRODUCTO

    @Override
    public List<Producto> listarProducto(){
        return productoRepository.findAll();
    }

    @Override
    public List<Producto> listarProductoPorNombre(String nombreProducto){
        return productoRepository.findByNombreProducto(nombreProducto);
    }

    @Override
    public List<Producto> listarProductoPorMarca(String marcaProducto){
        return productoRepository.findByMarcaProducto(marcaProducto);
    }

    @Override
    public List<Producto> listarProductoPorCategoria(String categoriaProducto){
        return productoRepository.findByCategoriaProducto(categoriaProducto);
    }

    @Override
    public Optional<Producto> obtenerProductoPorId(Long idProducto){
        return productoRepository.findById(idProducto);
    }

    @Override
    public Optional<Producto> obtenerProductoPorCodigo(String codigoProducto){
        return productoRepository.findByCodigoProducto(codigoProducto);
    }

    @Override
    public Producto crearProducto(Producto producto){
        return productoRepository.save(producto);
    }

    @Override
    public Producto actualizarProducto(Long idProducto, Producto producto){
        Optional<Producto> productoExistente = productoRepository.findById(idProducto);
        if(productoExistente.isPresent()){
            producto.setIdProducto(idProducto);
            return productoRepository.save(producto);
            
        }
        return null;
    }

    @Override
    public Producto actualizarParteDeProducto(Long idProducto, Map<String, Object> campos){
        Optional<Producto> productoOptional = productoRepository.findById(idProducto);
        if(productoOptional.isPresent()){
            Producto producto = productoOptional.get();

            campos.forEach((key,value) ->{
                switch (key){
                    case "nombreProducto":
                        producto.setNombreProducto(value.toString());
                        break;
                    case "codigoProducto":
                        producto.setCodigoProducto(value.toString());
                        break;
                    case "marcaProducto":
                        producto.setMarcaProducto(value.toString());
                        break;
                    case "categoriaProducto":
                        producto.setCategoriaProducto(value.toString());
                        break;
                    case "sucursalProducto":
                        producto.setSucursal(value.toString());
                        break;
                    case "precioProducto":
                        try{
                            BigDecimal nuevoPrecio = new BigDecimal(value.toString());
                            producto.setPrecioProducto(nuevoPrecio);
                        }catch(NumberFormatException e){
                            throw new IllegalArgumentException("Precio Invalido "+value);
                        }
                        break;
                }
            });
            return productoRepository.save(producto);
        }else{
            throw new RuntimeException("Producto no encontrado con Id: "+ idProducto);

        }
    }

    @Override
    public void eliminarProducto(Long idProducto){
        productoRepository.deleteById(idProducto);
    }

    // EMPLEADO



    
}
