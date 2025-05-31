package cl.duoc.integracion.apiwebservice.Repositorios;

import cl.duoc.integracion.apiwebservice.Entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByNombreClienteContainingIgnoreCase(String nombreProducto); 
    Optional<Cliente> findByIdCliente(Long idCliente);   
    Optional<Cliente> findByCorreoClienteContainingIgnoreCase(String correoCliente);
    Optional<Cliente> findByRutCliente(String rutCliente);
    boolean existsByCorreoCliente(String correoCliente);
    boolean existsByRutCliente(String rutCliente);
}
