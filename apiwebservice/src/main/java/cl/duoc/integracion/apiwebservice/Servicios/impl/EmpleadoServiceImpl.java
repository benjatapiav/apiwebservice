package cl.duoc.integracion.apiwebservice.Servicios.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cl.duoc.integracion.apiwebservice.DTO.EmpleadoDTO;
import cl.duoc.integracion.apiwebservice.DTO.EmpleadoPatchDTO;
import cl.duoc.integracion.apiwebservice.Entidades.Empleado;
import cl.duoc.integracion.apiwebservice.Repositorios.EmpleadoRepository;
import cl.duoc.integracion.apiwebservice.Servicios.EmpleadoService;

@Service
public class EmpleadoServiceImpl implements EmpleadoService{

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
     @Override
    public List<Empleado> listarEmpleado(){
        return empleadoRepository.findAll();
    }

    @Override
    public List<Empleado> listarEmpleadoPorNombreContainingIgnoreCase(String nombreEmpleado){
        return empleadoRepository.findByNombreEmpleadoContainingIgnoreCase(nombreEmpleado);
    }

    @Override
    public List<Empleado> listarEmpleadoPorRolContainingIgnoreCase(String rolEmpleado){
        return empleadoRepository.findByRolEmpleadoContainingIgnoreCase(rolEmpleado);
    }

    @Override
    public List<Empleado> listarEmpleadoPorSucursalContainingIgnoreCase(String sucursalEmpleado){
        return empleadoRepository.findBySucursalEmpleadoContainingIgnoreCase(sucursalEmpleado);
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
    public Empleado crearEmpleado(EmpleadoDTO empleadoDTO){
        Optional<Empleado> empleadoExistente = empleadoRepository.findByRutEmpleado(empleadoDTO.getRutEmpleado());
        if(empleadoExistente.isPresent()){
            throw new IllegalArgumentException("Ya existe un empleado con el rut: "+empleadoDTO.getRutEmpleado());
        }else{

            Empleado empleado = new Empleado();
            empleado.setNombreEmpleado(empleadoDTO.getNombreEmpleado());;
            empleado.setCorreoEmpleado(empleadoDTO.getCorreoEmpleado());
            empleado.setRolEmpleado(empleadoDTO.getRolEmpleado());
            empleado.setClaveEmpleado(empleadoDTO.getClaveEmpleado());
            empleado.setRutEmpleado(empleadoDTO.getRutEmpleado());
            empleado.setSucursalEmpleado(empleadoDTO.getSucursalEmpleado());

            empleado.setClaveEmpleado(passwordEncoder.encode(empleadoDTO.getClaveEmpleado()));
            
            return empleadoRepository.save(empleado);
        }
    }

    @Override
    public Empleado actualizarEmpleado(Integer idEmpleado, Empleado empleado){
        Empleado empleadoExistente = empleadoRepository.findById(idEmpleado)
            .orElseThrow(()-> new RuntimeException("No existe empleado con id: "+idEmpleado));
        
            empleadoExistente.setNombreEmpleado(empleado.getNombreEmpleado());
            empleadoExistente.setCorreoEmpleado(empleado.getCorreoEmpleado());
            empleadoExistente.setRutEmpleado(empleado.getRutEmpleado());
            empleadoExistente.setRolEmpleado(empleado.getRolEmpleado());
            empleadoExistente.setSucursalEmpleado(empleado.getSucursalEmpleado());
            empleadoExistente.setClaveEmpleado(empleado.getClaveEmpleado());

            empleadoExistente.setClaveEmpleado(passwordEncoder.encode(empleado.getClaveEmpleado()));

            return empleadoRepository.save(empleadoExistente);
    }

    @Override
    public Empleado actualizarParteDeEmpleado(Integer idEmpleado, EmpleadoPatchDTO empleadoPatchDTO){
        Empleado empleadoExistente = empleadoRepository.findByIdEmpleado(idEmpleado)
            .orElseThrow(()-> new RuntimeException("No existe empleado con Id: "+idEmpleado));

        if(empleadoPatchDTO.getNombreEmpleado() != null){
            empleadoExistente.setNombreEmpleado(empleadoPatchDTO.getNombreEmpleado());
        }
        if(empleadoPatchDTO.getCorreoEmpleado() != null){
            empleadoExistente.setCorreoEmpleado(empleadoPatchDTO.getCorreoEmpleado());
        }
        if (empleadoPatchDTO.getClaveEmpleado() != null) {
            empleadoExistente.setClaveEmpleado(passwordEncoder.encode(empleadoPatchDTO.getClaveEmpleado()));
        }
        if(empleadoPatchDTO.getRolEmpleado() != null){
            empleadoExistente.setRolEmpleado(empleadoPatchDTO.getRolEmpleado());
        }
        if(empleadoPatchDTO.getRutEmpleado() != null){
            empleadoExistente.setRutEmpleado(empleadoPatchDTO.getRutEmpleado());
        }
        if(empleadoPatchDTO.getSucursalEmpleado() != null){
            empleadoExistente.setSucursalEmpleado(empleadoPatchDTO.getSucursalEmpleado());
        }
        return empleadoRepository.save(empleadoExistente);
    }

    @Override 
    public void eliminarEmpleado(Integer idEmpleado){
        empleadoRepository.deleteById(idEmpleado);
    }

    @Override
    public Optional<Empleado> obtenerEmpleadoPorCorreoContainingIgnoreCase(String correoEmpleado){
        return empleadoRepository.findByCorreoEmpleadoContainingIgnoreCase(correoEmpleado);
    }
    
    
}
