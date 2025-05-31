package cl.duoc.integracion.apiwebservice.DTO;

import jakarta.validation.constraints.*;

public class ClienteDTO {
    
    @NotBlank(message = "El nombre del cliente es obligatorio")
    private String nombreCliente;

    @NotBlank(message = "El correo del cliente es obligatorio")
    private String correoCliente;

    @NotBlank(message = "El mensaje del cliente es obligatorio")
    private String mensajeCliente;

    @NotBlank(message = "la clave del cliente es obligatoria")
    private String claveCliente;

    @NotBlank(message = "el rut del cliente es obligatorio")
    private String rutCliente;


    //Getters and Setters
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
