package cl.duoc.integracion.apiwebservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import cl.duoc.integracion.apiwebservice.DTO.ProductoDTO;
import cl.duoc.integracion.apiwebservice.DTO.ProductoPatchDTO;
import cl.duoc.integracion.apiwebservice.Entidades.Producto;
import cl.duoc.integracion.apiwebservice.Repositorios.ProductoRepository;
import cl.duoc.integracion.apiwebservice.Servicios.HistorialDePrecioService;
import cl.duoc.integracion.apiwebservice.Servicios.impl.ProductoServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private HistorialDePrecioService historialDePrecioService;

    @InjectMocks
    private ProductoServiceImpl productoService;

    private Producto producto;
    private ProductoDTO productoDTO;

    @BeforeEach
    void setUp(){

        producto = new Producto();
        producto.setIdProducto(1L);
        producto.setCodigoProducto("HEMA-STA-DES-0001");
        producto.setNombreProducto("Destornillador");
        producto.setMarcaProducto("Stanley");
        producto.setCategoriaProducto("Herramienta Manual");
        producto.setCantidadProducto(10); 
        producto.setPrecioProducto(4990.0);
        producto.setSucursal("MESALC");

        producto.setHistorialDePrecios(new ArrayList<>());

        productoDTO = new ProductoDTO();
        productoDTO.setCodigoProducto("HEMA-STA-DES-0001");
        productoDTO.setNombreProducto("Destornillador");
        productoDTO.setMarcaProducto("Stanley");
        productoDTO.setCategoriaProducto("Herramienta Manual");
        productoDTO.setCantidadProducto(10);
        productoDTO.setPrecioProducto(4990.0);
        productoDTO.setSucursal("MESALC");
        
    }

    @Test
    void crearProducto_exitosamente(){
        when(productoRepository.findByCodigoProducto("HEMA-STA-DES-0001")).thenReturn(Optional.empty());
        when(productoRepository.save(any(Producto.class))).thenReturn(producto);

        Producto resultado = productoService.crearProducto(productoDTO);

        assertNotNull(resultado);
        assertEquals("Destornillador", resultado.getNombreProducto());
        verify(historialDePrecioService, times(1)).actualizarPrecioYHistorial(any(Producto.class), eq(4990.0));

    }

    @Test
    void crearProducto_Existente_DebeLanzarExcepcion(){

        when(productoRepository.findByCodigoProducto("HEMA-STA-DES-0001")).thenReturn(Optional.of(producto));

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                productoService.crearProducto(productoDTO)
            );

        assertEquals("Ya existe un producto con el codigo: HEMA-STA-DES-0001", ex.getMessage());
        verify(productoRepository, never()).save(any());

    }

    @Test
    void actualizarProducto_conCambioDePrecio(){
        Producto nuevoProducto = new Producto();
        nuevoProducto.setCodigoProducto("HEMA-STA-DES-0001");
        nuevoProducto.setNombreProducto("Destornillador");
        nuevoProducto.setMarcaProducto("Stanley");
        nuevoProducto.setCategoriaProducto("Herramienta Manual");
        nuevoProducto.setCantidadProducto(10); 
        nuevoProducto.setPrecioProducto(6990.0);
        nuevoProducto.setSucursal("MESALC");

        when(productoRepository.findByIdProducto(1L)).thenReturn(Optional.of(producto));
        when(productoRepository.save(any(Producto.class))).thenReturn(nuevoProducto);

        Producto resultado = productoService.actualizarProducto(1L, nuevoProducto);

        assertEquals(6990.0, resultado.getPrecioProducto());
        verify(historialDePrecioService, times(1)).actualizarPrecioYHistorial(any(Producto.class), eq(6990.0));
    }

    @Test
    void actualizarProducto_sinCambioDePrecio(){
        Producto sinCambio = new Producto();
        sinCambio.setCodigoProducto("HEMA-STA-DES-0001");
        sinCambio.setNombreProducto("Destornillador");
        sinCambio.setMarcaProducto("Stanley");
        sinCambio.setCategoriaProducto("Herramienta Manual");
        sinCambio.setCantidadProducto(10);
        sinCambio.setPrecioProducto(4990.0);
        sinCambio.setSucursal("MESALC");

        when(productoRepository.findByIdProducto(1L)).thenReturn(Optional.of(producto));
        when(productoRepository.save(any(Producto.class))).thenReturn(sinCambio);

        Producto resultado = productoService.actualizarProducto(1L, sinCambio);

        assertEquals(4990.0, resultado.getPrecioProducto());
        verify(historialDePrecioService, never()).actualizarPrecioYHistorial(any(), anyDouble());
        


    }

    @Test
    void eliminarProducto_Existente(){
        producto.setHistorialDePrecios(new ArrayList<>());
        when(productoRepository.findByIdProducto(1L)).thenReturn(Optional.of(producto));

        productoService.eliminarProducto(1L);

        verify(productoRepository, times(1)).delete(producto);
    }

    @Test
    void eliminarProducto_inexistente(){

        when(productoRepository.findByIdProducto(1L)).thenReturn(Optional.empty());

        productoService.eliminarProducto(1L);

        verify(productoRepository, never()).delete(any());

    }

    @Test
    void actualizarParteDeProducto_conCambioDePrecio(){
        ProductoPatchDTO patch = new ProductoPatchDTO();
        patch.setPrecioProducto(7990.0);

        when(productoRepository.findByIdProducto(1L)).thenReturn((Optional.of(producto)));
        when(productoRepository.save(any(Producto.class))).thenReturn(producto);

        Producto resultado = productoService.actualizarParteDeProducto(1L,patch);

        assertEquals(7990.0, resultado.getPrecioProducto());
        verify(historialDePrecioService, times(1)).actualizarPrecioYHistorial(any(), eq(7990.0));
    }
    
}
