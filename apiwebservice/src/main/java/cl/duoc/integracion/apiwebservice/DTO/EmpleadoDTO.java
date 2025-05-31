package cl.duoc.integracion.apiwebservice.DTO;

import jakarta.validation.constraints.*;

public class EmpleadoDTO {
    
    @NotBlank(message = "El nombre del empleado es obligatorio")
    private String nombreEmpleado;

    @NotBlank(message = "El correo del empleado es obligatorio")
    private String correoEmpleado;

    @NotBlank(message = "El rut del empleado es obligatorio")
    private String rutEmpleado;

    @NotBlank(message = "la clave del empleado es obligatoria")
    private String claveEmpleado;

    @NotBlank(message = "el rol del empleado es obligatorio")
    private String rolEmpleado;

    @NotBlank(message = "la sucursal del empleado es obligatoria")
    private String sucursalEmpleado;


    //Getters and Setters
    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getCorreoEmpleado() {
        return correoEmpleado;
    }

    public void setCorreoEmpleado(String correoEmpleado) {
        this.correoEmpleado = correoEmpleado;
    }

    public String getRutEmpleado() {
        return rutEmpleado;
    }

    public void setRutEmpleado(String rutEmpleado) {
        this.rutEmpleado = rutEmpleado;
    }

    public String getClaveEmpleado() {
        return claveEmpleado;
    }

    public void setClaveEmpleado(String claveEmpleado) {
        this.claveEmpleado = claveEmpleado;
    }

    public String getRolEmpleado() {
        return rolEmpleado;
    }

    public void setRolEmpleado(String rolEmpleado) {
        this.rolEmpleado = rolEmpleado;
    }

    public String getSucursalEmpleado() {
        return sucursalEmpleado;
    }

    public void setSucursalEmpleado(String sucursalEmpleado) {
        this.sucursalEmpleado = sucursalEmpleado;
    }
}
