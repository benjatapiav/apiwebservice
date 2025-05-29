package cl.duoc.integracion.apiwebservice.Entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;

    @Column(name = "nombre_cliente", nullable = false)
    private String nombreCliente;

    @Column(name = "correo_cliente", nullable = false, unique = true)
    private String correoCliente;

    @Column(name = "mensaje_cliente", nullable = false)
    private String mensajeCliente;

    @Column(name = "clave_cliente", nullable = false)
    private String claveCliente;

    @Column(name = "rut_cliente", nullable = false, unique = true)
    private String rutCliente;


    //Constructor vacio
    public Cliente(){}

    
    //Constructor con parametros
    public Cliente(Long idCliente, String nombreCliente, String correoCliente, String mensajeCliente,
            String claveCliente, String rutCliente) {
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.correoCliente = correoCliente;
        this.mensajeCliente = mensajeCliente;
        this.claveCliente = claveCliente;
        this.rutCliente = rutCliente;
    }


    //Getters and Setters
    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }

    public String getMensajeCliente() {
        return mensajeCliente;
    }

    public void setMensajeCliente(String mensajeCliente) {
        this.mensajeCliente = mensajeCliente;
    }

    public String getClaveCliente() {
        return claveCliente;
    }

    public void setClaveCliente(String claveCliente) {
        this.claveCliente = claveCliente;
    }

    public String getRutCliente() {
        return rutCliente;
    }

    public void setRutCliente(String rutCliente) {
        this.rutCliente = rutCliente;
    }


}
