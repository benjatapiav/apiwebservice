package cl.duoc.integracion.apiwebservice.Entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "empleado")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmpleado;

    @Column(name = "nombre_empleado")
    private String nombreEmpleado;
    
    @Column(name = "correo_empleado")
    private String correoEmpleado;

    @Column(name = "rut_empleado")
    private String rutEmpleado;

    @Column(name = "clave_empleado")
    private String claveEmpleado;

    @Column(name = "rol_empleado")
    private String rolEmpleado;

    //Constructo vacio
    public Empleado(){}
    
    //Constructor con parametros
    public Empleado(Integer idEmpleado, String nombreEmpleado, String correoEmpleado, String rutEmpleado,
            String claveEmpleado, String rolEmpleado) {
        this.idEmpleado = idEmpleado;
        this.nombreEmpleado = nombreEmpleado;
        this.correoEmpleado = correoEmpleado;
        this.rutEmpleado = rutEmpleado;
        this.claveEmpleado = claveEmpleado;
        this.rolEmpleado = rolEmpleado;
    }


    //Getters and Setters
    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

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


}
