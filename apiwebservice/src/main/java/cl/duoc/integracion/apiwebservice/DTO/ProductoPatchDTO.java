package cl.duoc.integracion.apiwebservice.DTO;


public class ProductoPatchDTO {
    private String codigoProducto;
    private String nombreProducto;
    private String marcaProducto;
    private String categoriaProducto;
    private Integer cantidadProducto;
    private Double precioProducto;
    private String sucursal;

    
    //Getters and Setters

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
    public Double getPrecioProducto() {
        return precioProducto;
    }
    public void setPrecioProducto(Double precioProducto) {
        this.precioProducto = precioProducto;
    }
    public String getSucursal() {
        return sucursal;
    }
    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    
}
