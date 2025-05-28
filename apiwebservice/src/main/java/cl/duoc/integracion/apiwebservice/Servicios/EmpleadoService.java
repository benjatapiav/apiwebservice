package cl.duoc.integracion.apiwebservice.Servicios;

import cl.duoc.integracion.apiwebservice.Entidades.Empleado;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EmpleadoService {
        
    List<Empleado> listarEmpleado();
    List<Empleado> listarEmpleadoPorNombre(String nombreEmpleado);
    List<Empleado> listarEmpleadoPorRol(String rolEmpleado);
    Optional<Empleado> obtenerEmpleadoPorCorreoYClave(String correoEmpleado, String claveEmpleado);
    Optional<Empleado> obtenerEmpleadoPorCorreo(String correoEmpleado);
    Optional<Empleado> obtenerEmpleadoPorRut(String rutEmpleado);
    Optional<Empleado> obtenerEmpleadoPorId(Integer idEmpleado);
    Empleado crearEmpleado(Empleado empleado);
    Empleado actualizarEmpleado(Integer idEmpleado, Empleado empleado);
    Empleado actualizarParteDeEmpleado(Integer idEmpleado, Map<String, Object> camposEmpleado);
    void eliminarEmpleado(Integer idEmpleado);
}
