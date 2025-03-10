package co.ucentral.creditaplication.models.dtos;

import lombok.Data;

import java.sql.Date;

@Data
public class ClienteCreateDto {
    private long id;
    private double ingresos;
    private String nombre;
    private String apellido;
    private String numeroTelefonico;
    private Date fechaNacimiento;
    private String direccion;
    private String correoElectronico;
    private String numeroDeIdentificacion;
    private String password;
}
