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
import org.springframework.security.crypto.password.PasswordEncoder;

import cl.duoc.integracion.apiwebservice.DTO.EmpleadoDTO;
import cl.duoc.integracion.apiwebservice.DTO.EmpleadoPatchDTO;
import cl.duoc.integracion.apiwebservice.Entidades.Empleado;
import cl.duoc.integracion.apiwebservice.Repositorios.EmpleadoRepository;
import cl.duoc.integracion.apiwebservice.Servicios.impl.EmpleadoServiceImpl;

@ExtendWith(MockitoExtension.class)
public class EmpleadoServiceTest {

    @Mock
    private EmpleadoRepository empleadoRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private EmpleadoServiceImpl empleadoService;

    private Empleado empleado;
    private EmpleadoDTO empleadoDTO;
    private EmpleadoPatchDTO empleadoPatchDTO;

    @BeforeEach
    void setUp(){
        empleado = new Empleado();
        empleado.setIdEmpleado(1);
        empleado.setNombreEmpleado("Juan Perez");
        empleado.setCorreoEmpleado("juan.perez@gmail.com");
        empleado.setRutEmpleado("12345678-9");
        empleado.setClaveEmpleado("clave123");
        empleado.setRolEmpleado("Administrador");
        empleado.setSucursalEmpleado("MESAPU");

        empleadoDTO = new EmpleadoDTO();
        empleadoDTO.setNombreEmpleado("Juan Perez");
        empleadoDTO.setCorreoEmpleado("juan.perez@gmail.com");
        empleadoDTO.setRutEmpleado("12345678-9");
        empleadoDTO.setClaveEmpleado("clave123");
        empleadoDTO.setRolEmpleado("Administrador");
        empleadoDTO.setSucursalEmpleado("MESAPU");

        empleadoPatchDTO = new EmpleadoPatchDTO();
        empleadoPatchDTO.setCorreoEmpleado("j.perez@gmail.com");
        empleadoPatchDTO.setRolEmpleado("Vendedor");

    }

    @Test
    void crearEmpleado_exitosamente(){
        when(empleadoRepository.findByRutEmpleado("12345678-9")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("clave123")).thenReturn("claveEncriptada");
        when(empleadoRepository.save(any(Empleado.class))).thenReturn(empleado);

        Empleado resultado = empleadoService.crearEmpleado(empleadoDTO);

        assertNotNull(resultado);
        assertEquals("Juan Perez", resultado.getNombreEmpleado());
        verify(empleadoRepository, times(1)).save(any(Empleado.class));

    }

    @Test
    void crearEmpleado_existente_DebeLanzarExcepcion(){
        when(empleadoRepository.findByRutEmpleado("12345678-9")).thenReturn(Optional.of(empleado));

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
            empleadoService.crearEmpleado(empleadoDTO)
        );

        assertEquals("Ya existe un empleado con el rut: 12345678-9", ex.getMessage());
        verify(empleadoRepository, never()).save(any());

    }

    @Test
    void actualizarEmpleado_conCambioDeCorreo(){
        Empleado nuevoEmpleado = new Empleado();
        nuevoEmpleado.setNombreEmpleado("Juan Perez");
        nuevoEmpleado.setCorreoEmpleado("juan.perez@gmail.com");
        nuevoEmpleado.setRutEmpleado("12345678-9");
        nuevoEmpleado.setClaveEmpleado("clave123");
        nuevoEmpleado.setRolEmpleado("Administrador");
        nuevoEmpleado.setSucursalEmpleado("MESAPU");

        when(empleadoRepository.findById(1)).thenReturn(Optional.of(empleado));
        when(passwordEncoder.encode("clave123")).thenReturn("claveEncriptada");
        when(empleadoRepository.save(any(Empleado.class))).thenReturn(empleado);

        Empleado resultado = empleadoService.actualizarEmpleado(1, nuevoEmpleado);

        assertEquals("Juan Perez", resultado.getNombreEmpleado());
        verify(empleadoRepository, times(1)).save(any());

    }

    @Test
    void actualizarParteDeEmpleado_deberiaActualizarCorreoYRol() {
    
    when(empleadoRepository.findByIdEmpleado(1)).thenReturn(Optional.of(empleado));
    when(empleadoRepository.save(any(Empleado.class))).thenReturn(empleado);

    Empleado resultado = empleadoService.actualizarParteDeEmpleado(1, empleadoPatchDTO);

    assertEquals("j.perez@gmail.com", resultado.getCorreoEmpleado());
    assertEquals("Vendedor", resultado.getRolEmpleado());
    verify(empleadoRepository, times(1)).save(any());
    }
    
    @Test
    void eliminarEmpleado_deberiaLlamarAlRespositorui(){
        empleadoService.eliminarEmpleado(1);
        verify(empleadoRepository,times(1)).deleteById(1);
    }

    @Test
    void listarEmpleado_deberiaRetornarLista(){
        when(empleadoRepository.findAll()).thenReturn(List.of(empleado));

        List<Empleado> resultado = empleadoService.listarEmpleado();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Juan Perez", resultado.get(0).getNombreEmpleado());
    }

    @Test
    void buscarPorNombre_deberiaRetornarCoincidencias(){
        when(empleadoRepository.findByNombreEmpleadoContainingIgnoreCase("juan")).thenReturn(List.of(empleado));

        List<Empleado> resultado = empleadoService.listarEmpleadoPorNombreContainingIgnoreCase("juan");

        assertEquals(1, resultado.size());        
    }

    @Test
    void buscarPorRol_deberiaRetornarCoincidencias(){
         when(empleadoRepository.findByRolEmpleadoContainingIgnoreCase("Administrador"))
         .thenReturn(List.of(empleado));

        List<Empleado> resultado = empleadoService.listarEmpleadoPorRolContainingIgnoreCase("Administrador");

        assertEquals(1, resultado.size());         
    }




}
