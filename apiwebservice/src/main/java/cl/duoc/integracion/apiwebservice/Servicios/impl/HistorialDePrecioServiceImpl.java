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
        return historialDePrecioRepository.findByfechaInicio(fechaInicio);
    }

    @Override
    public Optional<HistorialDePrecio> obtenerPrecioPorFechaFin(LocalDate fechaFin){
        return historialDePrecioRepository.findByfechaFin(fechaFin);
    }

    @Override
    public void eliminarHistorialDePrecio(Integer idHistorialDePrecio){
        historialDePrecioRepository.deleteById(idHistorialDePrecio);
    }

}
