package cl.duoc.integracion.apiwebservice;
import cl.duoc.integracion.apiwebservice.DTO.ClienteDTO;
import cl.duoc.integracion.apiwebservice.DTO.ClientePatchDTO;
import cl.duoc.integracion.apiwebservice.Entidades.Cliente;
import cl.duoc.integracion.apiwebservice.Entidades.Empleado;
import cl.duoc.integracion.apiwebservice.Repositorios.ClienteRepository;
import cl.duoc.integracion.apiwebservice.Repositorios.EmpleadoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegracionClienteTest {

    @LocalServerPort
    private int port;
    
    private String url(String path) {
        return "http://localhost:"+port+path;
    }

    private TestRestTemplate restTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private EmpleadoRepository empleadoRepository;
    
    private Long idClienteCreado;


    @BeforeEach
    void setUp(){

        clienteRepository.deleteAll();
        empleadoRepository.deleteAll();
        
        
        Empleado empleado = new Empleado();
        empleado.setCorreoEmpleado("admin@ferremas.cl");
        empleado.setClaveEmpleado(passwordEncoder.encode("admin123"));
        empleado.setRolEmpleado("Administrador");
        empleado.setNombreEmpleado("Roberto Admin");
        empleado.setRutEmpleado("6209798-7");
        empleado.setSucursalEmpleado("MESAPU");
            
        empleadoRepository.save(empleado);
            
            
        this.restTemplate = new TestRestTemplate(
            new RestTemplateBuilder().basicAuthentication("admin@ferremas.cl", "admin123")
            );
            
        Cliente cliente = new Cliente();
        cliente.setNombreCliente("Cristian Tapia");
        cliente.setCorreoCliente("cris.tapia@gmail.com");
        cliente.setRutCliente("12345678-9");
        cliente.setClaveCliente("clave123");
        cliente.setMensajeCliente("Hola soy Cristian, linda ferreteria");
            
        clienteRepository.save(cliente);
        idClienteCreado = cliente.getIdCliente();
    }
        
        @Test
        void crearCliente_exitosamente(){
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNombreCliente("Tomas Leiva");
        clienteDTO.setCorreoCliente("tomas.leiva@gmail.com");
        clienteDTO.setRutCliente("17465798-8");
        clienteDTO.setClaveCliente("tomas123");
        clienteDTO.setMensajeCliente("Hola soy Tomas, linda ferreteria");

        ResponseEntity<String> respuesta = restTemplate.postForEntity(
            url("/api/clientes"),
            clienteDTO,
            String.class);

        assertEquals(HttpStatus.CREATED, respuesta.getStatusCode());
        assertTrue(respuesta.getBody().contains("creado con exito"));

    }

    @Test
    void listarClientes_deberiaRetornarListaDeClientes(){
        ResponseEntity<Cliente[]> respuesta = restTemplate.getForEntity(
            url("/api/clientes"),
            Cliente[].class
        );

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertTrue(respuesta.getBody().length >= 1);
    }

    @Test
    void obtenerClientePorId_deberiaRetornarClientePorId(){
        ResponseEntity<Cliente> respuesta = restTemplate.getForEntity(
            url("/api/clientes/" + idClienteCreado),
            Cliente.class
        );

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals("Cristian Tapia", respuesta.getBody().getNombreCliente());
    }

    @Test
    void actualizarCliente_completo_deberiaModificarDatos(){
        Cliente clienteActualizado = new Cliente();
        clienteActualizado.setNombreCliente("Benjamin Tapia");
        clienteActualizado.setCorreoCliente("benj.tapia@gmail.com");
        clienteActualizado.setRutCliente("19407867-7");
        clienteActualizado.setClaveCliente("clave456");
        clienteActualizado.setMensajeCliente("Hola soy Benjamin, linda ferreteria");

        HttpEntity<Cliente> requestEntity = new HttpEntity<>(clienteActualizado);

        ResponseEntity<Cliente> respuesta = restTemplate.exchange(
            url("/api/clientes/" + idClienteCreado),
            HttpMethod.PUT,
            requestEntity,
            Cliente.class
            );

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals("Benjamin Tapia", respuesta.getBody().getNombreCliente());

    }

    @Test
    void actualizarCliente_parcialmente_deberiaActualizarSoloCiertosCampos(){
        ClientePatchDTO clientePatchDTO = new ClientePatchDTO();
        clientePatchDTO.setCorreoCliente("benjamintapia.96@gmail.com");

        HttpEntity<ClientePatchDTO> requestEntity = new HttpEntity<>(clientePatchDTO);

        ResponseEntity<Cliente> response = restTemplate.exchange(
            url("/api/clientes/" + idClienteCreado),
            HttpMethod.PATCH,
            requestEntity,
            Cliente.class
            );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("benjamintapia.96@gmail.com", response.getBody().getCorreoCliente());
    }

    @Test
    void eliminarCliente_deberiaEliminarExitosamente(){
        ResponseEntity<String> respuesta = restTemplate.exchange(
            url("/api/clientes/" + idClienteCreado),
            HttpMethod.DELETE,
            null,
            String.class
            );
        
        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertFalse(clienteRepository.findById(idClienteCreado).isPresent());
    }

}
