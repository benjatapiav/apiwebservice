package cl.duoc.integracion.apiwebservice.Repositorios;

import cl.duoc.integracion.apiwebservice.Entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByNombreCliente(String nombreProducto); 
    Optional<Cliente> findByIdCliente(Long idCliente);   
    boolean existsByCorreoCliente(String correoCliente);
    boolean existsByRutCliente(String rutCliente);
}
