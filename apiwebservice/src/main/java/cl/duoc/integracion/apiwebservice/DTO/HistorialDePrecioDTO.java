package cl.duoc.integracion.apiwebservice.DTO;

import java.time.LocalDate;

public class HistorialDePrecioDTO {
    private ProductoDTO productoDTO;
    private Double precio;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    //Getters and Setters
    public ProductoDTO getProductoDTO() {
        return productoDTO;
    }
    public void setProductoDTO(ProductoDTO productoDTO) {
        this.productoDTO = productoDTO;
    }
    public Double getPrecio() {
        return precio;
    }
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public LocalDate getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }
    

    
}
