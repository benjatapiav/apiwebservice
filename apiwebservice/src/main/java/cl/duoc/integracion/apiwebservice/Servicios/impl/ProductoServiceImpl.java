package cl.duoc.integracion.apiwebservice.Servicios.impl;


import cl.duoc.integracion.apiwebservice.DTO.ProductoDTO;
import cl.duoc.integracion.apiwebservice.Entidades.HistorialDePrecio;
import cl.duoc.integracion.apiwebservice.Entidades.Producto;
import cl.duoc.integracion.apiwebservice.Repositorios.HistorialDePrecioRepository;
import cl.duoc.integracion.apiwebservice.Repositorios.ProductoRepository;
import cl.duoc.integracion.apiwebservice.Servicios.ProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService{

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private HistorialDePrecioRepository historialDePrecioRepository;


    // === PRODUCTO ===

    @Override
    public List<Producto> listarProducto(){
        return productoRepository.findAll();
    }

    @Override
    public List<Producto> listarProductoPorNombreContainingIgnoreCase(String nombreProducto){
        return productoRepository.findByNombreProductoContainingIgnoreCase(nombreProducto);
    }

    @Override
    public List<Producto> listarProductoPorMarcaContainingIgnoreCase(String marcaProducto){
        return productoRepository.findByMarcaProductoContainingIgnoreCase(marcaProducto);
    }

    @Override
    public List<Producto> listarProductoPorCategoriaContainingIgnoreCase(String categoriaProducto){
        return productoRepository.findByCategoriaProductoContainingIgnoreCase(categoriaProducto);
    }

    @Override
    public List<Producto> listarProductoPorSucursalContainingIgnoreCase(String sucursal){
        return productoRepository.findBySucursalContainingIgnoreCase(sucursal);
    }

    @Override
    public Optional<Producto> obtenerProductoPorId(Long idProducto){
        return productoRepository.findByIdProducto(idProducto);
    }

    @Override
    public Optional<Producto> obtenerProductoPorCodigo(String codigoProducto){
        return productoRepository.findByCodigoProducto(codigoProducto);
    }

    @Override
    public Producto crearProducto(ProductoDTO productoDTO){
        Optional<Producto> productoExistente = productoRepository.findByCodigoProducto(productoDTO.getCodigoProducto());
        if(productoExistente.isPresent()){
            throw new IllegalArgumentException("Ya existe un producto con el codigo: "+productoDTO.getCodigoProducto());
        }else{

            Producto producto = new Producto();
            producto.setCodigoProducto(productoDTO.getCodigoProducto());
            producto.setNombreProducto(productoDTO.getNombreProducto());
            producto.setCantidadProducto(productoDTO.getCantidadProducto());
            producto.setCategoriaProducto(productoDTO.getCategoriaProducto());
            producto.setMarcaProducto(productoDTO.getMarcaProducto());
            producto.setPrecioProducto(productoDTO.getPrecioProducto());
            producto.setSucursal(productoDTO.getSucursal());
    
            return productoRepository.save(producto);
        }

    }

    @Override
    public Producto actualizarProducto(Long idProducto, Producto producto) {
        Producto productoExistente = productoRepository.findByIdProducto(idProducto)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado con Id: " + idProducto));

    
        productoExistente.setCodigoProducto(producto.getCodigoProducto());
        productoExistente.setNombreProducto(producto.getNombreProducto());
        productoExistente.setMarcaProducto(producto.getMarcaProducto());
        productoExistente.setCategoriaProducto(producto.getCategoriaProducto());
        productoExistente.setCantidadProducto(producto.getCantidadProducto());
        productoExistente.setSucursal(producto.getSucursal());
        productoExistente.setPrecioProducto(producto.getPrecioProducto());

        return productoRepository.save(productoExistente);
    }

    @Override
    public Producto actualizarParteDeProducto(Long idProducto, Map<String, Object> campos){
        Optional<Producto> productoOptional = productoRepository.findByIdProducto(idProducto);
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
                    case "sucursal":
                        producto.setSucursal(value.toString());
                        break;
                    case "precioProducto":
                        try{
                            Double nuevoPrecio = Double.valueOf(value.toString());
                            if(!producto.getPrecioProducto().equals(nuevoPrecio)){
                                HistorialDePrecio historial = new HistorialDePrecio();
                                historial.setProducto(producto);
                                historial.setPrecio(producto.getPrecioProducto());
                                historial.setFechaInicio(LocalDate.now());
                                historial.setFechaFin(LocalDate.now());

                                historialDePrecioRepository.save(historial);
                                
                                producto.setPrecioProducto(nuevoPrecio);
                            }
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
        if(!productoRepository.existsById(idProducto)){
            throw new RuntimeException("Producto no encontrado con Id: " + idProducto);
        }
            productoRepository.deleteById(idProducto);
        }

    
}
