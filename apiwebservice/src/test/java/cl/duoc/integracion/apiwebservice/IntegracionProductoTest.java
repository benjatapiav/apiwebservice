package cl.duoc.integracion.apiwebservice;

import cl.duoc.integracion.apiwebservice.Entidades.Empleado;
import cl.duoc.integracion.apiwebservice.Entidades.Producto;
import cl.duoc.integracion.apiwebservice.DTO.ProductoDTO;
import cl.duoc.integracion.apiwebservice.DTO.ProductoPatchDTO;
import cl.duoc.integracion.apiwebservice.Repositorios.EmpleadoRepository;
import cl.duoc.integracion.apiwebservice.Repositorios.ProductoRepository;

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
public class IntegracionProductoTest {

    @LocalServerPort
    private int port;

    private String url(String path) {
        return "http://localhost:" + port + path;
    }

    private TestRestTemplate restTemplate;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Long idProductoCreado;

    @BeforeEach
    void setUp() {
        productoRepository.deleteAll();
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

        Producto producto = new Producto();
        producto.setCodigoProducto("P001");
        producto.setNombreProducto("Taladro Bosch");
        producto.setMarcaProducto("Bosch");
        producto.setCategoriaProducto("Herramientas");
        producto.setCantidadProducto(10);
        producto.setSucursal("MESAPU");
        producto.setPrecioProducto(49990.0);

        productoRepository.save(producto);
        idProductoCreado = producto.getIdProducto();
    }

    @Test
    void crearProducto_exitosamente() {
        ProductoDTO dto = new ProductoDTO();
        dto.setCodigoProducto("P002");
        dto.setNombreProducto("Martillo Stanley");
        dto.setMarcaProducto("Stanley");
        dto.setCategoriaProducto("Herramientas");
        dto.setCantidadProducto(20);
        dto.setSucursal("MAIPU");
        dto.setPrecioProducto(5990.0);

        ResponseEntity<String> respuesta = restTemplate.postForEntity(
            url("/api/productos"),
            dto,
            String.class
        );

        assertEquals(HttpStatus.CREATED, respuesta.getStatusCode());
        assertTrue(respuesta.getBody().contains("creado con exito"));
    }

    @Test
    void listarProductos_deberiaRetornarLista() {
        ResponseEntity<Producto[]> respuesta = restTemplate.getForEntity(
            url("/api/productos"),
            Producto[].class
        );

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertTrue(respuesta.getBody().length >= 1);
    }

    @Test
    void obtenerProductoPorId_deberiaRetornarProducto() {
        ResponseEntity<Producto> respuesta = restTemplate.getForEntity(
            url("/api/productos/" + idProductoCreado),
            Producto.class
        );

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals("Taladro Bosch", respuesta.getBody().getNombreProducto());
    }

    @Test
    void actualizarProducto_completo() {
        Producto nuevo = new Producto();
        nuevo.setCodigoProducto("P001");
        nuevo.setNombreProducto("Taladro Makita");
        nuevo.setMarcaProducto("Makita");
        nuevo.setCategoriaProducto("Herramientas");
        nuevo.setCantidadProducto(15);
        nuevo.setSucursal("MAIPU");
        nuevo.setPrecioProducto(52990.0);

        HttpEntity<Producto> request = new HttpEntity<>(nuevo);

        ResponseEntity<Producto> respuesta = restTemplate.exchange(
            url("/api/productos/" + idProductoCreado),
            HttpMethod.PUT,
            request,
            Producto.class
        );

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals("Taladro Makita", respuesta.getBody().getNombreProducto());
    }

    @Test
    void actualizarProducto_parcialmente() {
        ProductoPatchDTO patchDTO = new ProductoPatchDTO();
        patchDTO.setPrecioProducto(45990.0);

        HttpEntity<ProductoPatchDTO> request = new HttpEntity<>(patchDTO);

        ResponseEntity<Producto> respuesta = restTemplate.exchange(
            url("/api/productos/" + idProductoCreado),
            HttpMethod.PATCH,
            request,
            Producto.class
        );

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals(45990.0, respuesta.getBody().getPrecioProducto());
    }

    @Test
    void eliminarProducto_exitosamente() {
        ResponseEntity<String> respuesta = restTemplate.exchange(
            url("/api/productos/" + idProductoCreado),
            HttpMethod.DELETE,
            null,
            String.class
        );

        assertEquals(HttpStatus.NO_CONTENT, respuesta.getStatusCode());
        assertFalse(productoRepository.findById(idProductoCreado).isPresent());
    }
}
