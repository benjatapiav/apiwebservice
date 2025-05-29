package cl.duoc.integracion.apiwebservice.Servicios.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.integracion.apiwebservice.Entidades.Empleado;
import cl.duoc.integracion.apiwebservice.Repositorios.EmpleadoRepository;
import cl.duoc.integracion.apiwebservice.Servicios.EmpleadoService;

@Service
public class EmpleadoServiceImpl implements EmpleadoService{

    @Autowired
    private EmpleadoRepository empleadoRepository;
    
     @Override
    public List<Empleado> listarEmpleado(){
        return empleadoRepository.findAll();
    }

    @Override
    public List<Empleado> listarEmpleadoPorNombre(String nombreEmpleado){
        return empleadoRepository.findByNombreEmpleado(nombreEmpleado);
    }

    @Override
    public List<Empleado> listarEmpleadoPorRol(String rolEmpleado){
        return empleadoRepository.findByRolEmpleado(rolEmpleado);
    }

    @Override
    public List<Empleado> listarEmpleadoPorSucursal(String sucursalEmpleado){
        return empleadoRepository.findBySucursalEmpleado(sucursalEmpleado);
    }

    @Override
    public Optional<Empleado> obtenerEmpleadoPorRut(String rutEmpleado){
        return empleadoRepository.findByRutEmpleado(rutEmpleado);
    }
    
    @Override
    public Optional<Empleado> obtenerEmpleadoPorId(Integer idEmpleado){
        return empleadoRepository.findById(idEmpleado);
    }

    @Override
    public Empleado crearEmpleado(Empleado empleado){
        return empleadoRepository.save(empleado);
    }

    @Override
    public Empleado actualizarEmpleado(Integer idEmpleado, Empleado empleado){
        Optional<Empleado> empleadoExistente = empleadoRepository.findById(idEmpleado);
        if(empleadoExistente.isPresent()){
            empleado.setIdEmpleado(idEmpleado);
            return empleadoRepository.save(empleado);
        }
        return null;
    }

    @Override
    public Empleado actualizarParteDeEmpleado(Integer idEmpleado, Map<String, Object> camposEmpleado){
        Optional<Empleado> empleadoOptional = empleadoRepository.findById(idEmpleado);
        if(empleadoOptional.isPresent()){
            Empleado empleado = empleadoOptional.get();

            camposEmpleado.forEach((key,value) ->{
                switch(key){
                    case "nombre_empleado":
                        empleado.setNombreEmpleado(value.toString());
                        break;
                    case "correo_empleado":
                        empleado.setCorreoEmpleado(value.toString());
                        break;
                    case "clave_empleado":
                        empleado.setClaveEmpleado(value.toString());
                        break;
                    case "rol_empleado":
                        empleado.setRolEmpleado(value.toString());
                        break;
                    case "rut_empleado":
                        empleado.setRutEmpleado(value.toString());
                        break;
                }
            });
            return empleadoRepository.save(empleado);
        }else{
            throw new RuntimeException("Empleado no encontrado con Id: "+ idEmpleado);
        }
    }

    @Override 
    public void eliminarEmpleado(Integer idEmpleado){
        empleadoRepository.deleteById(idEmpleado);
    }

    @Override
    public Optional<Empleado> obtenerEmpleadoPorCorreoYClave(String correoEmpleado, String claveEmpleado){
        Optional<Empleado> empleado = empleadoRepository.findByCorreoEmpleado(correoEmpleado);
        return empleado.filter(e -> e.getClaveEmpleado().equals(claveEmpleado));
    }

    @Override
    public Optional<Empleado> obtenerEmpleadoPorCorreo(String correoEmpleado){
        return empleadoRepository.findByCorreoEmpleado(correoEmpleado);
    }
    
    
}
