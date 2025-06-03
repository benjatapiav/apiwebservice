package cl.duoc.integracion.apiwebservice.Repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.integracion.apiwebservice.Entidades.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer>{
    List<Empleado> findByNombreEmpleadoContainingIgnoreCase(String nombreEmpleado);
    List<Empleado> findByRolEmpleadoContainingIgnoreCase(String rolEmpleado);
    List<Empleado> findBySucursalEmpleadoContainingIgnoreCase(String sucursalEmpleado);
    Optional<Empleado> findByRutEmpleado(String rutEmpleado);
    Optional<Empleado> findByIdEmpleado(Integer idEmpleado);
    Optional<Empleado> findByCorreoEmpleadoContainingIgnoreCase(String correoEmpleado);
    Optional<Empleado> findByCorreoEmpleadoAndClaveEmpleado(String correoEmpleado, String claveEmpleado);
}
