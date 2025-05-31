package cl.duoc.integracion.apiwebservice.Servicios;

import cl.duoc.integracion.apiwebservice.DTO.ClienteDTO;
import cl.duoc.integracion.apiwebservice.Entidades.Cliente;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ClienteService {
        
    List<Cliente> listarCliente();
    List<Cliente> listarClientePorNombreContainingIgnoreCase(String nombreCliente);
    Optional<Cliente> obtenerClientePorId(Long idCliente);
    Optional<Cliente> obtenerClientePorCorreoContainingIgnoreCase(String correoCliente);
    Optional<Cliente> obtenerClientePorRut(String rutCliente);
    Cliente crearCliente(ClienteDTO clienteDTO);
    Cliente actualizarCliente(Long idCliente,Cliente cliente);
    Cliente actualizarParteDeCliente(Long idCliente, Map<String, Object> camposCliente);
    void eliminarCliente(Long idCliente);
}
