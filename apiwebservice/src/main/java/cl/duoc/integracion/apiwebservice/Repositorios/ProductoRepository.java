package cl.duoc.integracion.apiwebservice.Repositorios;

import cl.duoc.integracion.apiwebservice.Entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByNombreProducto(String nombreProducto); 
    Optional<Producto> findByIdProducto(Long idProducto);   
    Optional<Producto> findByCodigoProducto(String codigoProducto);
    List<Producto> findByMarcaProducto(String marcaProducto);
    List<Producto> findByCategoriaProducto(String nombreCategoria);
}
