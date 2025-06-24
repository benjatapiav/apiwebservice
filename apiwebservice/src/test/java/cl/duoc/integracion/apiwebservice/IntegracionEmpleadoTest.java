package cl.duoc.integracion.apiwebservice;

import cl.duoc.integracion.apiwebservice.DTO.EmpleadoDTO;
import cl.duoc.integracion.apiwebservice.DTO.EmpleadoPatchDTO;
import cl.duoc.integracion.apiwebservice.Entidades.Empleado;
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
public class IntegracionEmpleadoTest {

    @LocalServerPort
    private int port;

    private String url(String path) {
        return "http://localhost:" + port + path;
    }

    private TestRestTemplate restTemplate;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Integer idEmpleadoCreado;

    @BeforeEach
    void setUp() {
        empleadoRepository.deleteAll();

        // Crear empleado admin para autenticación
        Empleado admin = new Empleado();
        admin.setCorreoEmpleado("admin@ferremas.cl");
        admin.setClaveEmpleado(passwordEncoder.encode("admin123"));
        admin.setNombreEmpleado("Administrador Principal");
        admin.setRolEmpleado("Administrador");
        admin.setRutEmpleado("6209798-7");
        admin.setSucursalEmpleado("MESAPU");

        empleadoRepository.save(admin);

        this.restTemplate = new TestRestTemplate(
            new RestTemplateBuilder().basicAuthentication("admin@ferremas.cl", "admin123")
        );

        // Crear un empleado para pruebas
        Empleado empleado = new Empleado();
        empleado.setNombreEmpleado("Juan Pérez");
        empleado.setCorreoEmpleado("juan.perez@gmail.com");
        empleado.setRutEmpleado("12345678-9");
        empleado.setClaveEmpleado(passwordEncoder.encode("clave123"));
        empleado.setRolEmpleado("Vendedor");
        empleado.setSucursalEmpleado("LOVALLEDOR");

        empleadoRepository.save(empleado);
        idEmpleadoCreado = empleado.getIdEmpleado();
    }

    @Test
    void crearEmpleado_exitosamente() {
        EmpleadoDTO dto = new EmpleadoDTO();
        dto.setNombreEmpleado("Ana Soto");
        dto.setCorreoEmpleado("ana.soto@gmail.com");
        dto.setRutEmpleado("17654321-8");
        dto.setClaveEmpleado("ana123");
        dto.setRolEmpleado("Bodeguero");
        dto.setSucursalEmpleado("SANMIGUEL");

        ResponseEntity<String> response = restTemplate.postForEntity(
            url("/api/empleados"),
            dto,
            String.class
        );

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(response.getBody().contains("creado con exito"));
    }

    @Test
    void listarEmpleados_deberiaRetornarLista() {
        ResponseEntity<Empleado[]> response = restTemplate.getForEntity(
            url("/api/empleados"),
            Empleado[].class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().length >= 1);
    }

    @Test
    void obtenerEmpleadoPorId_deberiaRetornarEmpleado() {
        ResponseEntity<Empleado> response = restTemplate.getForEntity(
            url("/api/empleados/" + idEmpleadoCreado),
            Empleado.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Juan Pérez", response.getBody().getNombreEmpleado());
    }

    @Test
    void actualizarEmpleado_completo_deberiaModificar() {
        Empleado actualizado = new Empleado();
        actualizado.setNombreEmpleado("Mario Soto");
        actualizado.setCorreoEmpleado("mario.soto@gmail.com");
        actualizado.setRutEmpleado("23456789-0");
        actualizado.setClaveEmpleado("nuevaClave123");
        actualizado.setRolEmpleado("Administrador");
        actualizado.setSucursalEmpleado("MESAPU");

        HttpEntity<Empleado> entity = new HttpEntity<>(actualizado);

        ResponseEntity<Empleado> response = restTemplate.exchange(
            url("/api/empleados/" + idEmpleadoCreado),
            HttpMethod.PUT,
            entity,
            Empleado.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Mario Soto", response.getBody().getNombreEmpleado());
    }

    @Test
    void actualizarEmpleado_parcialmente_deberiaModificarUnCampo() {
        EmpleadoPatchDTO patchDTO = new EmpleadoPatchDTO();
        patchDTO.setCorreoEmpleado("juan.actualizado@gmail.com");

        HttpEntity<EmpleadoPatchDTO> entity = new HttpEntity<>(patchDTO);

        ResponseEntity<Empleado> response = restTemplate.exchange(
            url("/api/empleados/" + idEmpleadoCreado),
            HttpMethod.PATCH,
            entity,
            Empleado.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("juan.actualizado@gmail.com", response.getBody().getCorreoEmpleado());
    }

    @Test
    void eliminarEmpleado_deberiaEliminarExitosamente() {
        ResponseEntity<String> response = restTemplate.exchange(
            url("/api/empleados/" + idEmpleadoCreado),
            HttpMethod.DELETE,
            null,
            String.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(empleadoRepository.findById(idEmpleadoCreado).isPresent());
    }
}
