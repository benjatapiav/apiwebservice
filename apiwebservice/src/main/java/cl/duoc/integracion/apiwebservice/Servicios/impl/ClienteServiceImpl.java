package cl.duoc.integracion.apiwebservice.Servicios.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.integracion.apiwebservice.DTO.ClienteDTO;
import cl.duoc.integracion.apiwebservice.DTO.ClientePatchDTO;
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
    public Cliente actualizarParteDeCliente(Long idCliente, ClientePatchDTO clientePatchDTO){
        Cliente clienteExistente = clienteRepository.findByIdCliente(idCliente)
            .orElseThrow(()-> new RuntimeException("No existe un cliente con id: "+idCliente));

            if(clientePatchDTO.getNombreCliente() != null){
                clienteExistente.setNombreCliente(clientePatchDTO.getNombreCliente());
            }
            if(clientePatchDTO.getCorreoCliente() != null){
                clienteExistente.setCorreoCliente(clientePatchDTO.getCorreoCliente());
            }
            if(clientePatchDTO.getClaveCliente() != null){
                clienteExistente.setClaveCliente(clientePatchDTO.getClaveCliente());
            }
            if(clientePatchDTO.getRutCliente() != null){
                clienteExistente.setRutCliente(clientePatchDTO.getRutCliente());
            }
            if(clientePatchDTO.getMensajeCliente() != null){
                clienteExistente.setMensajeCliente(clientePatchDTO.getMensajeCliente());
            }

            return clienteRepository.save(clienteExistente);
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
        Cliente clienteExistente = clienteRepository.findByIdCliente(idCliente)
            .orElseThrow(()-> new RuntimeException("No existe cliente con Id: "+idCliente));

            clienteExistente.setNombreCliente(cliente.getNombreCliente());
            clienteExistente.setCorreoCliente(cliente.getCorreoCliente());
            clienteExistente.setClaveCliente(cliente.getClaveCliente());
            clienteExistente.setMensajeCliente(cliente.getMensajeCliente());
            clienteExistente.setRutCliente(cliente.getRutCliente());

            return clienteRepository.save(clienteExistente);
    }
}
