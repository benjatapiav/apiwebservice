package cl.duoc.integracion.apiwebservice.Servicios.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.integracion.apiwebservice.Entidades.Producto;
import cl.duoc.integracion.apiwebservice.Repositorios.ProductoRepository;
import cl.duoc.integracion.apiwebservice.Entidades.HistorialDePrecio;
import cl.duoc.integracion.apiwebservice.Repositorios.HistorialDePrecioRepository;
import cl.duoc.integracion.apiwebservice.Servicios.HistorialDePrecioService;

@Service
public class HistorialDePrecioServiceImpl implements HistorialDePrecioService {
    
    @Autowired
    public HistorialDePrecioRepository historialDePrecioRepository;

    @Autowired
    public ProductoRepository productoRepository;

    @Override
    public List<HistorialDePrecio> listarHistorialPorProducto(Producto producto){
        return historialDePrecioRepository.findByProducto(producto);
    }

    @Override
    public Optional<HistorialDePrecio> obtenerPrecioPorFechaInicio(LocalDate fechaInicio){
        return historialDePrecioRepository.findByFechaInicio(fechaInicio);
    }

    @Override
    public Optional<HistorialDePrecio> obtenerPrecioPorFechaFin(LocalDate fechaFin){
        return historialDePrecioRepository.findByFechaFin(fechaFin);
    }

    @Override
    public void eliminarHistorialDePrecio(Integer idHistorialDePrecio){
        historialDePrecioRepository.deleteById(idHistorialDePrecio);
    }

    @Override
    public Optional<HistorialDePrecio> obtenerHistorialActivoPorProducto(Producto producto) {
        return historialDePrecioRepository.findByProductoAndFechaFinIsNull(producto);
    }

    @Override
    public void actualizarPrecioYHistorial(Producto producto, Double nuevoPrecio) {
    Optional<HistorialDePrecio> historialActivo = historialDePrecioRepository.findByProductoAndFechaFinIsNull(producto);

    LocalDate hoy = LocalDate.now();

    // Si ya existe un historial activo, cerrarlo
    historialActivo.ifPresent(historial -> {
        historial.setFechaFin(hoy);
        historialDePrecioRepository.save(historial);
    });

    // Crear nuevo historial de precio
    HistorialDePrecio nuevoHistorial = new HistorialDePrecio();
    nuevoHistorial.setProducto(producto);
    nuevoHistorial.setPrecio(nuevoPrecio);
    nuevoHistorial.setFechaInicio(hoy);
    nuevoHistorial.setFechaFin(null);

    historialDePrecioRepository.save(nuevoHistorial);
}

}
