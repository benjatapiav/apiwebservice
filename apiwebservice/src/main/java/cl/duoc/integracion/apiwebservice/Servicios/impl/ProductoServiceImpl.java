package cl.duoc.integracion.apiwebservice.Servicios.impl;


import cl.duoc.integracion.apiwebservice.DTO.ProductoDTO;
import cl.duoc.integracion.apiwebservice.DTO.ProductoPatchDTO;
import cl.duoc.integracion.apiwebservice.Entidades.Producto;
import cl.duoc.integracion.apiwebservice.Servicios.HistorialDePrecioService;
import cl.duoc.integracion.apiwebservice.Repositorios.ProductoRepository;
import cl.duoc.integracion.apiwebservice.Servicios.ProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Objects;

@Service
public class ProductoServiceImpl implements ProductoService{

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private HistorialDePrecioService historialDePrecioService;

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


            Producto productoGuardado = productoRepository.save(producto);

        // Crear historial inicial
            historialDePrecioService.actualizarPrecioYHistorial(productoGuardado, producto.getPrecioProducto());

            return productoGuardado;
        }

    }

    @Override
    public Producto actualizarProducto(Long idProducto, Producto producto) {
        Producto productoExistente = productoRepository.findByIdProducto(idProducto)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado con Id: " + idProducto));

        
        boolean precioCambiado = !productoExistente.getPrecioProducto().equals(producto.getPrecioProducto());

        productoExistente.setCodigoProducto(producto.getCodigoProducto());
        productoExistente.setNombreProducto(producto.getNombreProducto());
        productoExistente.setMarcaProducto(producto.getMarcaProducto());
        productoExistente.setCategoriaProducto(producto.getCategoriaProducto());
        productoExistente.setCantidadProducto(producto.getCantidadProducto());
        productoExistente.setSucursal(producto.getSucursal());
        productoExistente.setPrecioProducto(producto.getPrecioProducto());

        Producto productoActualizado = productoRepository.save(productoExistente);

        if(precioCambiado){
            historialDePrecioService.actualizarPrecioYHistorial(productoActualizado, producto.getPrecioProducto());
        }
      
        return productoActualizado;
    }

    @Override
    public Producto actualizarParteDeProducto(Long idProducto, ProductoPatchDTO productoPatchDTO){
        Producto productoExistente = productoRepository.findByIdProducto(idProducto)
            .orElseThrow(()-> new RuntimeException("No existe un producto con Id: "+idProducto));
        
            boolean precioCambiado = false;

            if(productoPatchDTO.getCodigoProducto() != null){
                productoExistente.setCodigoProducto(productoPatchDTO.getCodigoProducto());
            }
            if(productoPatchDTO.getNombreProducto() != null){
                productoExistente.setNombreProducto(productoPatchDTO.getNombreProducto());
            }
            if(productoPatchDTO.getMarcaProducto() != null){
                productoExistente.setMarcaProducto(productoPatchDTO.getMarcaProducto());
            }
            if(productoPatchDTO.getCategoriaProducto() != null){
                productoExistente.setCategoriaProducto(productoPatchDTO.getCategoriaProducto());
            }
            if(productoPatchDTO.getCantidadProducto() != null){
                productoExistente.setCantidadProducto(productoPatchDTO.getCantidadProducto());
            }
            if(productoPatchDTO.getPrecioProducto() != null){
                precioCambiado = !Objects.equals(productoExistente.getPrecioProducto(),productoPatchDTO.getPrecioProducto());
                productoExistente.setPrecioProducto(productoPatchDTO.getPrecioProducto());
            }
            if(productoPatchDTO.getSucursal() != null){
                productoExistente.setSucursal(productoPatchDTO.getSucursal());
            }

            
            Producto productoActualizado = productoRepository.save(productoExistente);
            if(precioCambiado){
                historialDePrecioService.actualizarPrecioYHistorial(productoActualizado, productoPatchDTO.getPrecioProducto());
            }

        return productoActualizado;
    }


    @Override
    public void eliminarProducto(Long idProducto){
        Optional<Producto> producto = productoRepository.findByIdProducto(idProducto);
        if(producto.isPresent()){
            productoRepository.deleteById(idProducto);
        }
    }
}