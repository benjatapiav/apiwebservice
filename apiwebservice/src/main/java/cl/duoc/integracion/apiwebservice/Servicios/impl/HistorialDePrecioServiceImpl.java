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
    public HistorialDePrecioRepository historialDePreciorRepository;

    @Autowired
    public ProductoRepository productoRepository;

    @Override
    public List<HistorialDePrecio> listarHistorialPorProducto(Producto producto){
        return historialDePreciorRepository.findByProducto(producto);
    }

    @Override
    public List<HistorialDePrecio> listarHistorialPorProductoOrdenadoPorFechaDesc(Producto producto){
        return historialDePreciorRepository.findByProductoOrderByFechaDesc(producto);
    }

    @Override
    public Optional<HistorialDePrecio> obtenerPrecioPorFechaInicio(LocalDate fechaInicio){
        return historialDePreciorRepository.findByfechaInicioPrecio(fechaInicio);
    }

    @Override
    public Optional<HistorialDePrecio> obtenerPrecioPorFechaFin(LocalDate fechaFin){
        return historialDePreciorRepository.findByfechaFinPrecio(fechaFin);
    }

    @Override
    public void eliminarHistorialDePrecio(Integer idHistorialDePrecio){
        historialDePreciorRepository.deleteById(idHistorialDePrecio);
    }

}
