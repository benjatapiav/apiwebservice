package cl.duoc.integracion.apiwebservice.Servicios;


import cl.duoc.integracion.apiwebservice.Entidades.HistorialDePrecio;
import cl.duoc.integracion.apiwebservice.Entidades.Producto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface HistorialDePrecioService {
        
    List<HistorialDePrecio> listarHistorialPorProducto(Producto producto);
    Optional<HistorialDePrecio> obtenerPrecioPorFechaInicio(LocalDate fechaInicio);
    Optional<HistorialDePrecio> obtenerPrecioPorFechaFin(LocalDate fechaFin);
    Optional<HistorialDePrecio> obtenerHistorialActivoPorProducto(Producto producto);
    void actualizarPrecioYHistorial(Producto producto, Double nuevoPrecio);
    void eliminarHistorialDePrecio(Integer idHistorialDePrecio);
    
}
