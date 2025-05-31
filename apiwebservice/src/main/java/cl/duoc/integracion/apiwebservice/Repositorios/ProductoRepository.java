package cl.duoc.integracion.apiwebservice.Repositorios;

import cl.duoc.integracion.apiwebservice.Entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByNombreProductoContainingIgnoreCase(String nombreProducto); 
    List<Producto> findByMarcaProductoContainingIgnoreCase(String marcaProducto);
    List<Producto> findByCategoriaProductoContainingIgnoreCase(String categoriaProducto);
    List<Producto> findBySucursalContainingIgnoreCase(String sucursal);
    Optional<Producto> findByIdProducto(Long idProducto);   
    Optional<Producto> findByCodigoProducto(String codigoProducto);
    
}
