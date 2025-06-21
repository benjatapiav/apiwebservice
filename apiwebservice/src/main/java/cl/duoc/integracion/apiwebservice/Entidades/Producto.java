package cl.duoc.integracion.apiwebservice.Entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

@Entity
@Table(name="producto")
public class Producto {
    
    //Primary Key IdProducto
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;

    //Columnas
    @Column(name = "codigo_producto", nullable = false)
    private String codigoProducto;

    @Column(name = "nombre_producto", nullable = false)
    private String nombreProducto;

    @Column(name = "marca_producto", nullable = false)
    private String marcaProducto;

    @Column(name = "categoria_producto", nullable = false)
    private String categoriaProducto;

    @Column(name = "cantidad_producto", nullable = false)
    private Integer cantidadProducto;

    @Column(name = "sucursal", nullable = false)
    private String sucursal;

    @Column(name = "precio_producto", nullable = false)
    private Double precioProducto;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true )
    @JsonIgnore
    private List<HistorialDePrecio> historialDePrecios;

    //Constructor vacio
    public Producto(){}

    //Constructor con parametros
        public Producto(Long idProducto, String codigoProducto, String nombreProducto, String marcaProducto,
            String categoriaProducto, Integer cantidadProducto ,String sucursal, Double precioProducto) {
                
        this.idProducto = idProducto;
        this.codigoProducto = codigoProducto;
        this.nombreProducto = nombreProducto;
        this.marcaProducto = marcaProducto;
        this.categoriaProducto = categoriaProducto;
        this.cantidadProducto = cantidadProducto;
        this.sucursal = sucursal;
        this.precioProducto = precioProducto;
    }

    //Getters & Setters
    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getMarcaProducto() {
        return marcaProducto;
    }

    public void setMarcaProducto(String marcaProducto) {
        this.marcaProducto = marcaProducto;
    }

    public String getCategoriaProducto() {
        return categoriaProducto;
    }

    public void setCategoriaProducto(String categoriaProducto) {
        this.categoriaProducto = categoriaProducto;
    }
    
    public Integer getCantidadProducto() {
    return cantidadProducto;
    }
    
    public void setCantidadProducto(Integer cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }
    
    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public Double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(Double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public List<HistorialDePrecio> getHistorialDePrecios() {
        return historialDePrecios;
    }

    public void setHistorialDePrecios(List<HistorialDePrecio> historialDePrecios) {
        this.historialDePrecios = historialDePrecios;
    }

    

    
}
