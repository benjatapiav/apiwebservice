package cl.duoc.integracion.apiwebservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import cl.duoc.integracion.apiwebservice.DTO.ClienteDTO;
import cl.duoc.integracion.apiwebservice.DTO.ClientePatchDTO;
import cl.duoc.integracion.apiwebservice.Entidades.Cliente;
import cl.duoc.integracion.apiwebservice.Repositorios.ClienteRepository;
import cl.duoc.integracion.apiwebservice.Servicios.impl.ClienteServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    private Cliente cliente;
    private ClienteDTO clienteDTO;
    private ClientePatchDTO clientePatchDTO;

    @BeforeEach
    void setUp(){

        cliente = new Cliente();
        cliente.setIdCliente(1L);
        cliente.setNombreCliente("Victor Perez");
        cliente.setCorreoCliente("victor.perez@gmail.com");
        cliente.setClaveCliente("vicper123");
        cliente.setRutCliente("19307967-6");
        cliente.setMensajeCliente("Me encanta Ferremas, muy buena ferreteria");
        

        clienteDTO = new ClienteDTO();
        clienteDTO.setNombreCliente("Victor Perez");
        clienteDTO.setCorreoCliente("victor.perez@gmail.com");
        clienteDTO.setClaveCliente("vicper123");
        clienteDTO.setRutCliente("19307967-6");
        clienteDTO.setMensajeCliente("Me encanta Ferremas, muy buena ferreteria");

        clientePatchDTO = new ClientePatchDTO();
        clientePatchDTO.setNombreCliente("Victor Perez Pereira");
        clientePatchDTO.setCorreoCliente("victor.perez.pereira@gmail.com");
    }

    @Test
    void crearCliente_exitosamente(){
        when(clienteRepository.findByRutCliente("19307967-6")).thenReturn(Optional.empty());
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente resultado = clienteService.crearCliente(clienteDTO);

        assertNotNull(resultado);
        assertEquals("Victor Perez", resultado.getNombreCliente());
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @Test
    void crearCliente_existente_DebeLanzarExcepcion(){

        when(clienteRepository.findByRutCliente("19307967-6")).thenReturn(Optional.of(cliente));

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                clienteService.crearCliente(clienteDTO)
            );

        assertEquals("Ya existe un cliente con el rut: 19307967-6", ex.getMessage());
        verify(clienteRepository, never()).save(any());
    }

    @Test
    void actualizarCliente_conCambioDeNombre(){
        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setRutCliente("19307967-6");
        nuevoCliente.setNombreCliente("Victor Perez Pereira");
        nuevoCliente.setCorreoCliente("victor.perez@gmail.com");
        nuevoCliente.setClaveCliente("vicper123");
        nuevoCliente.setMensajeCliente("Me encanta Ferremas, muy buena ferreteria");

        when(clienteRepository.findByIdCliente(1L)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(nuevoCliente);

        Cliente resultado = clienteService.actualizarCliente(1L, nuevoCliente);

        assertEquals("Victor Perez Pereira", resultado.getNombreCliente());
        verify(clienteRepository, times(1)).save(any());
        
    }

    @Test
    void actualizarParteDeCliente_deberiaActualizarSoloElCorreo(){
        when(clienteRepository.findByIdCliente(1L)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente resultado = clienteService.actualizarParteDeCliente(1L, clientePatchDTO);

        assertEquals("victor.perez.pereira@gmail.com", resultado.getCorreoCliente());
        verify(clienteRepository, times(1)).save(any());

    }

    @Test
    void eliminarCliente_deberiaLlamarAlRepositorio(){
        clienteService.eliminarCliente(1L);
        verify(clienteRepository, times(1)).deleteById(1L);
    }

    @Test
    void listarCliente_deberiaRetornarLista(){

        Cliente clienteEjemplo = new Cliente();
        clienteEjemplo.setNombreCliente("Victor");

        when(clienteRepository.findAll()).thenReturn(List.of(clienteEjemplo));

        List<Cliente> resultado = clienteService.listarCliente();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Victor", resultado.get(0).getNombreCliente());
    }
    
}
