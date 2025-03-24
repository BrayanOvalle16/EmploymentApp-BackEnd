package co.ucentral.creditaplication.models.dtos;


import lombok.Data;

@Data
public class EmpresaDto {
    private String nombreEmpresa;
    private String industria;
    private String sitioWeb;
    private String correo;
    private String telefono;
    private String direccion;
    private String descripcion;
    private int tamano;
    private int anioFundacion;
    private String linkeding;
    private String twitter;
}
