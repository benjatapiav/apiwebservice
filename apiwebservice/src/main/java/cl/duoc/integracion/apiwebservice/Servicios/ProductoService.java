package cl.duoc.integracion.apiwebservice.Servicios;

import cl.duoc.integracion.apiwebservice.Entidades.Producto;

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

}
