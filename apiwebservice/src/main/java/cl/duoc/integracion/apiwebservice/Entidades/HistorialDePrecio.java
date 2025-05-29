package cl.duoc.integracion.apiwebservice.Entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.time.LocalDate;

@Entity
@Table(name = "historial_de_precio")
public class HistorialDePrecio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historial_de_precio")
    private Integer idHistorialDePrecio;

    @Column(name = "precio", nullable = false)
    private Double precio;
    
    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;
    
    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    //Constructor Vacio
    public HistorialDePrecio(){}

    //Constructor con parametros
    public HistorialDePrecio(Integer idHistorialDePrecio, Producto producto, Double precio, LocalDate fechaInicio,
            LocalDate fechaFin) {
        this.idHistorialDePrecio = idHistorialDePrecio;
        this.producto = producto;
        this.precio = precio;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }
    
    //Getters and Setters
    public Integer getIdHistorialDePrecio() {
        return idHistorialDePrecio;
    }

    public void setIdHistorialDePrecio(Integer idHistorialDePrecio) {
        this.idHistorialDePrecio = idHistorialDePrecio;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
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
