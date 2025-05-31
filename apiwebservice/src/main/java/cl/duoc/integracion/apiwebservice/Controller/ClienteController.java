package cl.duoc.integracion.apiwebservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cl.duoc.integracion.apiwebservice.DTO.ClienteDTO;
import cl.duoc.integracion.apiwebservice.Entidades.Cliente;
import cl.duoc.integracion.apiwebservice.Servicios.ClienteService;

import java.util.Optional;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("api/clientes")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> listarCliente(){
        List<Cliente> clienteData = clienteService.listarCliente();
        return new ResponseEntity<>(clienteData,HttpStatus.OK);
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<Optional<Cliente>> obtenerClientePorId(@PathVariable Long idCliente) {
        Optional<Cliente> clienteData = clienteService.obtenerClientePorId(idCliente);
        if(clienteData.isPresent()){
            return new ResponseEntity<>(clienteData,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/rutCliente/{rutCliente}")
    public ResponseEntity<Optional<Cliente>> obtenerClientePorRut(@PathVariable String rutCliente){
        Optional<Cliente> clienteData = clienteService.obtenerClientePorRut(rutCliente);
        if(clienteData.isPresent()){
            return new ResponseEntity<>(clienteData,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/correoCliente/{correoCliente}")
    public ResponseEntity<Optional<Cliente>> obtenerClientePorCorreo(@PathVariable String correoCliente){
        Optional<Cliente> clienteData = clienteService.obtenerClientePorCorreoContainingIgnoreCase(correoCliente);
        if(clienteData.isPresent()){
            return new ResponseEntity<>(clienteData,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/nombreCliente/{nombreCliente}")
    public ResponseEntity<List<Cliente>> listarClientePorNombre(@PathVariable String nombreCliente){
        List<Cliente> clienteData = clienteService.listarClientePorNombreContainingIgnoreCase(nombreCliente);
        if(clienteData.isEmpty()){
            return new ResponseEntity<>(clienteData,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
     public ResponseEntity<String> crearCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
    clienteService.crearCliente(clienteDTO);
    return new ResponseEntity<>("Empleado de Nombre: "+clienteDTO.getNombreCliente()+ " \ncreado con exito", HttpStatus.CREATED);
    }
    
    @PutMapping("/{idCliente}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long idCliente, @Valid @RequestBody Cliente cliente) {
        Cliente clienteActualizado = clienteService.actualizarCliente(idCliente, cliente);
        return new ResponseEntity<>(clienteActualizado,HttpStatus.OK);
    }

    @PatchMapping("/{idCliente}")
    public ResponseEntity<Cliente> actualizarParteDeCliente(@PathVariable Long idCliente, @RequestBody Map<String,Object> camposCliente){
        Cliente clienteActualizado = clienteService.actualizarParteDeCliente(idCliente, camposCliente);
        return new ResponseEntity<>(clienteActualizado,HttpStatus.OK);
    }

    @DeleteMapping("/{idCliente}")
    public ResponseEntity<String> eliminarCliente(@PathVariable Long idCliente){
        clienteService.eliminarCliente(idCliente);
        return new ResponseEntity<>("Cliente eliminado",HttpStatus.OK);
    }
      
}
    





