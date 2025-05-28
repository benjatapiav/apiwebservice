package cl.duoc.integracion.apiwebservice.Repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.duoc.integracion.apiwebservice.Entidades.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer>{
    List<Empleado> findByNombreEmpleado(String nombreEmpleado);
    List<Empleado> findByRolEmpleado(String rolEmpleado);
    Optional<Empleado> findByRutEmpleado(String rutEmpleado);
    Optional<Empleado> findByIdEmpleado(Integer idEmpleado);
    Optional<Empleado> findByCorreoEmpleado(String correoEmpleado);
    Optional<Empleado> findByCorreoEmpleadoAndClaveEmpleado(String correoEmpleado, String claveEmpleado);
}
