package cl.duoc.integracion.apiwebservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import cl.duoc.integracion.apiwebservice.Entidades.Empleado;
import cl.duoc.integracion.apiwebservice.Repositorios.EmpleadoRepository;
import cl.duoc.integracion.apiwebservice.Security.EmpleadoDetailsService;

@ExtendWith(MockitoExtension.class)
public class EmpleadoDetailsServiceTest {
    
    @Mock
    private EmpleadoRepository empleadoRepository;

    @InjectMocks
    private EmpleadoDetailsService empleadoDetailsService;

    @Test
    void cargarUsuarioPorCorreo_existente(){
        Empleado empleado = new Empleado();
        empleado.setCorreoEmpleado("admin@ferremas.cl");
        empleado.setClaveEmpleado("claveEncriptada");
        empleado.setRolEmpleado("Administrador");

        when(empleadoRepository.findByCorreoEmpleadoContainingIgnoreCase("admin@ferremas.cl"))
            .thenReturn(Optional.of(empleado));

        UserDetails userDetails = empleadoDetailsService.loadUserByUsername("admin@ferremas.cl");

        assertEquals("admin@ferremas.cl", userDetails.getUsername());
        assertEquals("claveEncriptada", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream()
            .anyMatch(auth -> auth.getAuthority().equals("ROLE_Administrador")));

    }

    @Test
    void cargarUsuarioPorCorreo_noExistente(){
        when(empleadoRepository.findByCorreoEmpleadoContainingIgnoreCase("noexiste@ferremas.cl"))
            .thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class,() ->
            empleadoDetailsService.loadUserByUsername("noexiste@ferremas.cl"));
    }

}
