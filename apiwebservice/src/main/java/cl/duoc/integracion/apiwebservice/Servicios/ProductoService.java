package cl.duoc.integracion.apiwebservice.Servicios;


import cl.duoc.integracion.apiwebservice.DTO.ProductoDTO;
import cl.duoc.integracion.apiwebservice.Entidades.Producto;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductoService {
    //Entidad PRODUCTO
    List<Producto> listarProducto(); //Listar todos los productos
    List<Producto> listarProductoPorNombreContainingIgnoreCase(String nombreProducto);//Listar productos por nombre
    List<Producto> listarProductoPorMarcaContainingIgnoreCase(String marcaProducto);//Listar productos por marca
    List<Producto> listarProductoPorCategoriaContainingIgnoreCase(String categoriaProducto);//Listar productos por categoria
    List<Producto> listarProductoPorSucursalContainingIgnoreCase(String sucursal);
    Optional<Producto> obtenerProductoPorId(Long idProducto); //Obtener producto por Id
    Optional<Producto> obtenerProductoPorCodigo(String codigoProducto);//Obtener producto por codigo
    Producto crearProducto(ProductoDTO productoDTO);// Crear un producto nuevo
    Producto actualizarProducto(Long idProducto,Producto producto);// Actualizar producto existente
    Producto actualizarParteDeProducto(Long idProducto, Map<String, Object> campos);
    void eliminarProducto(Long idProducto); // Eliminar producto por Id

}
