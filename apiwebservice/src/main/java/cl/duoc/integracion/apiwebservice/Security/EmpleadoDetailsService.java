package cl.duoc.integracion.apiwebservice.Security;

import cl.duoc.integracion.apiwebservice.Entidades.Empleado;
import cl.duoc.integracion.apiwebservice.Repositorios.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class EmpleadoDetailsService implements UserDetailsService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public UserDetails loadUserByUsername(String correoEmpleado) throws UsernameNotFoundException {
        Empleado empleado = empleadoRepository
                .findByCorreoEmpleadoContainingIgnoreCase(correoEmpleado)
                .orElseThrow(() -> new UsernameNotFoundException("Empleado no encontrado con correo: " + correoEmpleado));

        // Spring espera que los roles tengan el prefijo "ROLE_"
        String rol = "ROLE_" + empleado.getRolEmpleado();

        return new User(
                empleado.getCorreoEmpleado(),
                empleado.getClaveEmpleado(), // clave ya encriptada con BCrypt
                Collections.singletonList(new SimpleGrantedAuthority(rol))
        );
    }
}
