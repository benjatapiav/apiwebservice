package cl.duoc.integracion.apiwebservice.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cl.duoc.integracion.apiwebservice.DTO.EmpleadoDTO;
import cl.duoc.integracion.apiwebservice.DTO.EmpleadoPatchDTO;
import cl.duoc.integracion.apiwebservice.Entidades.Empleado;
import cl.duoc.integracion.apiwebservice.Servicios.EmpleadoService;

import java.util.Optional;
import jakarta.validation.Valid;
import java.util.List;





@RestController
@RequestMapping("api/empleados")
public class EmpleadoController {


    
    @Autowired
    private EmpleadoService empleadoService;


    //Listar todos los empleados
    @GetMapping
    public ResponseEntity<List<Empleado>> listarEmpleados() {
        List<Empleado> empleadoData = empleadoService.listarEmpleado();
        if(!empleadoData.isEmpty()){
            return new ResponseEntity<>(empleadoData,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Obtener empleado por Id
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Empleado>> obtenerEmpleadoPorId(@PathVariable Integer id) {
        Optional<Empleado> empleadoData = empleadoService.obtenerEmpleadoPorId(id);
        if(empleadoData.isPresent()){
            return new ResponseEntity<>(empleadoData,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Obtener empleado por correo
    @GetMapping("/correoEmpleado/{correoEmpleado}")
    public ResponseEntity<Optional<Empleado>> obtenerEmpleadoPorCorreo(@PathVariable String correoEmpleado){
        Optional<Empleado> empleadoData = empleadoService.obtenerEmpleadoPorCorreoContainingIgnoreCase(correoEmpleado);
        if(empleadoData.isPresent()){
            return new ResponseEntity<>(empleadoData,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Obtener empleado por Rut
    @GetMapping("/rutEmpleado/{rutEmpleado}")
    public ResponseEntity<Optional<Empleado>> obtenerEmpleadoPorRut(@PathVariable String rutEmpleado) {
        Optional<Empleado> empleadoData = empleadoService.obtenerEmpleadoPorRut(rutEmpleado);
        if(empleadoData.isPresent()){
            return new ResponseEntity<>(empleadoData,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Listar empleados por Nombre
    @GetMapping("/nombreEmpleado/{nombreEmpleado}")
    public ResponseEntity<List<Empleado>> listarEmpleadoPorNombre(@PathVariable String nombreEmpleado) {
        List<Empleado> empleadoData = empleadoService.listarEmpleadoPorNombreContainingIgnoreCase(nombreEmpleado);
        if(!empleadoData.isEmpty()){
            return new ResponseEntity<>(empleadoData,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Listar empleados por Sucursal
    @GetMapping("/sucursalEmpleado/{sucursalEmpleado}")
    public ResponseEntity<List<Empleado>> listarEmpleadoPorSucursal(@PathVariable String sucursalEmpleado){
        List<Empleado> empleadoData = empleadoService.listarEmpleadoPorSucursalContainingIgnoreCase(sucursalEmpleado);
        if(!empleadoData.isEmpty()){
            return new ResponseEntity<>(empleadoData,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Listar empleados por Rol
    @GetMapping("/rolEmpleado/{rolEmpleado}")
    public ResponseEntity<List<Empleado>> listarEmpleadoPorRol(@PathVariable String rolEmpleado){
        List<Empleado> empleadoData = empleadoService.listarEmpleadoPorRolContainingIgnoreCase(rolEmpleado);
        if(!empleadoData.isEmpty()){
            return new ResponseEntity<>(empleadoData,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping
     public ResponseEntity<String> crearEmpleado(@Valid @RequestBody EmpleadoDTO empleadoDTO) {
        empleadoService.crearEmpleado(empleadoDTO);
        return new ResponseEntity<>("Empleado de Nombre: "+empleadoDTO.getNombreEmpleado()+ "\nRol: " + empleadoDTO.getRolEmpleado() + " \ncreado con exito", HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Integer id, @RequestBody Empleado empleado) {
        Empleado empleadoActualizado = empleadoService.actualizarEmpleado(id, empleado);
        return new ResponseEntity<>(empleadoActualizado,HttpStatus.OK);
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<Empleado> actualizarParteDeEmpleado(@PathVariable Integer id, @RequestBody EmpleadoPatchDTO empleadoPatchDTO){
        Empleado empleadoActualizado = empleadoService.actualizarParteDeEmpleado(id, empleadoPatchDTO);
        return new ResponseEntity<>(empleadoActualizado,HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEmpleado(@PathVariable Integer id){
        empleadoService.eliminarEmpleado(id);
        return new ResponseEntity<>("Empleado eliminado",HttpStatus.OK);
    }
}
