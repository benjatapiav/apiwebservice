package cl.duoc.integracion.apiwebservice.DTO;

public class ClientePatchDTO {
    private String nombreCliente;
    private String correoCliente;
    private String mensajeCliente;
    private String claveCliente;
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
