package cl.duoc.integracion.apiwebservice.Servicios;

import cl.duoc.integracion.apiwebservice.DTO.EmpleadoDTO;
import cl.duoc.integracion.apiwebservice.Entidades.Empleado;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EmpleadoService {
        
    List<Empleado> listarEmpleado();
    List<Empleado> listarEmpleadoPorNombreContainingIgnoreCase(String nombreEmpleado);
    List<Empleado> listarEmpleadoPorRolContainingIgnoreCase(String rolEmpleado);
    List<Empleado> listarEmpleadoPorSucursalContainingIgnoreCase(String sucursalEmpleado);
    Optional<Empleado> obtenerEmpleadoPorCorreoYClave(String correoEmpleado, String claveEmpleado);
    Optional<Empleado> obtenerEmpleadoPorCorreoContainingIgnoreCase(String correoEmpleado);
    Optional<Empleado> obtenerEmpleadoPorRut(String rutEmpleado);
    Optional<Empleado> obtenerEmpleadoPorId(Integer idEmpleado);
    Empleado crearEmpleado(EmpleadoDTO empleadoDTO);
    Empleado actualizarEmpleado(Integer idEmpleado, Empleado empleado);
    Empleado actualizarParteDeEmpleado(Integer idEmpleado, Map<String, Object> camposEmpleado);
    void eliminarEmpleado(Integer idEmpleado);
}
