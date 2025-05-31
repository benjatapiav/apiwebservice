package cl.duoc.integracion.apiwebservice.Servicios.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.integracion.apiwebservice.DTO.ClienteDTO;
import cl.duoc.integracion.apiwebservice.Entidades.Cliente;
import cl.duoc.integracion.apiwebservice.Repositorios.ClienteRepository;
import cl.duoc.integracion.apiwebservice.Servicios.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService{
    
    @Autowired
    public ClienteRepository clienteRepository;

    @Override 
    public List<Cliente> listarCliente(){
        return clienteRepository.findAll();
    }

    @Override
    public List<Cliente> listarClientePorNombreContainingIgnoreCase(String nombreCliente){
        return clienteRepository.findByNombreClienteContainingIgnoreCase(nombreCliente);
    }

    @Override
    public Optional<Cliente> obtenerClientePorId(Long idCliente){
        return clienteRepository.findByIdCliente(idCliente);
    }

    @Override
    public Optional<Cliente> obtenerClientePorRut(String rutCliente){
        return clienteRepository.findByRutCliente(rutCliente);
    }

    @Override
    public Optional<Cliente> obtenerClientePorCorreoContainingIgnoreCase(String correoCliente){
        return clienteRepository.findByCorreoClienteContainingIgnoreCase(correoCliente);
    }

    @Override
    public Cliente actualizarParteDeCliente(Long idCliente, Map<String,Object> camposCliente){
        Optional<Cliente> clienteOptional = clienteRepository.findById(idCliente);
        if(clienteOptional.isPresent()){
            Cliente cliente = clienteOptional.get();

            camposCliente.forEach((key,value) ->{
                switch(key){
                    case "nombre_cliente":
                        cliente.setNombreCliente(value.toString());
                        break;
                    case "correo_cliente":
                        cliente.setCorreoCliente(value.toString());
                        break;
                    case "rut_cliente":
                        cliente.setRutCliente(value.toString());
                        break;
                    case "clave_cliente":
                        cliente.setClaveCliente(value.toString());
                        break;
                }       
            });
            return clienteRepository.save(cliente);
        }else{
            throw new RuntimeException("Cliente no encontrado con Id: "+ idCliente);
        }
    }

    @Override
    public Cliente crearCliente(ClienteDTO clienteDTO){
        Optional<Cliente> clienteExistente = clienteRepository.findByRutCliente(clienteDTO.getRutCliente());
        if(clienteExistente.isPresent()){
            throw new IllegalArgumentException("Ya existe un cliente con el rut: "+clienteDTO.getRutCliente());
        }else{
            Cliente cliente = new Cliente();
            cliente.setNombreCliente(clienteDTO.getNombreCliente());
            cliente.setCorreoCliente(clienteDTO.getCorreoCliente());
            cliente.setClaveCliente(clienteDTO.getClaveCliente());
            cliente.setRutCliente(clienteDTO.getRutCliente());
            cliente.setMensajeCliente(clienteDTO.getMensajeCliente());

            return clienteRepository.save(cliente);
        }
    }

    @Override
    public void eliminarCliente(Long idCliente){
        clienteRepository.deleteById(idCliente);
    }

    @Override
    public Cliente actualizarCliente(Long idCliente,Cliente cliente){
        Optional<Cliente> clienteData = clienteRepository.findByIdCliente(idCliente);
        if(clienteData.isPresent()){
            cliente.setIdCliente(idCliente);
            return clienteRepository.save(cliente);
        }else{
            throw new RuntimeException("Cliente no encontrado con Id: " + idCliente);
        }
    }
}
