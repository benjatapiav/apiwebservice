package cl.duoc.integracion.apiwebservice.Servicios;

import cl.duoc.integracion.apiwebservice.Entidades.Producto;
import cl.duoc.integracion.apiwebservice.Entidades.HistorialDePrecio;
import cl.duoc.integracion.apiwebservice.Entidades.Cliente;
import cl.duoc.integracion.apiwebservice.Entidades.Empleado;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductoService {
    //Entidad PRODUCTO
    List<Producto> listarProducto(); //Listar todos los productos
    Optional<Producto> obtenerProductoPorId(Long idProducto); //Obtener producto por Id
    Optional<Producto> obtenerProductoPorCodigo(String codigoProducto);//Obtener producto por codigo
    List<Producto> listarProductoPorNombre(String nombreProducto);//Listar productos por nombre
    List<Producto> listarProductoPorMarca(String marcaProducto);//Listar productos por marca
    List<Producto> listarProductoPorCategoria(String categoriaProducto);//Listar productos por categoria
    Producto crearProducto(Producto producto);// Crear un producto nuevo
    Producto actualizarProducto(Long idProducto,Producto producto);// Actualizar producto existente
    Producto actualizarParteDeProducto(Long idProducto, Map<String, Object> campos);
    void eliminarProducto(Long idProducto); // Eliminar producto por Id
    
    //Entidad Cliente
    List<Cliente> listarCliente();
    List<Cliente> listarClientePorNombre(String nombreCliente);
    Optional<Cliente> obtenerClientePorId(Long idCliente);
    Optional<Cliente> obtenerClientePorCorreo(String correoCliente);
    Cliente crearCliente(Cliente cliente);
    Cliente actualizarCliente(Long idCliente,Cliente cliente);
    void eliminarCliente(Long idCliente);

    //Entidad Empleado
    List<Empleado> listarEmpleado();
    List<Empleado> listarEmpleadoPorNombre(String nombreEmpleado);
    List<Empleado> listarEmpleadoPorRol(String rolEmpleado);
    Optional<Empleado> obtenerEmpleadoPorCorreoYClave(String correoEmpleado, String claveEmpleado);
    Optional<Empleado> obtenerEmpleadoPorRut(String rutEmpleado);
    Optional<Empleado> obtenerEmpleadoPorId(Integer idEmpleado);
    Empleado crearEmpleado(Empleado empleado);
    Empleado actualizarEmpleado(Integer idEmpleado, Empleado empleado);
    void eliminarEmpleado(Integer idEmpleado);
    
    //Entidad HistorialDePrecio
    List<HistorialDePrecio> listarHistorialPorProducto(Producto producto);
    List<HistorialDePrecio> listarHistorialPorProductoOrdenadoPorFechaDesc(Producto producto);
    void eliminarHistorialDePrecio(Integer idHistorialDePrecio);

}
