package cl.duoc.integracion.apiwebservice.Repositorios;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.integracion.apiwebservice.Entidades.HistorialDePrecio;
import cl.duoc.integracion.apiwebservice.Entidades.Producto;

@Repository
public interface HistorialDePrecioRepository extends JpaRepository<HistorialDePrecio, Integer> {
    List<HistorialDePrecio> findByProducto(Producto producto);
    Optional<HistorialDePrecio> findByFechaInicio(LocalDate fechaInicio);
    Optional<HistorialDePrecio> findByFechaFin(LocalDate fechaFin);
    Optional<HistorialDePrecio> findByProductoAndFechaFinIsNull(Producto producto); // Historial activo
}
