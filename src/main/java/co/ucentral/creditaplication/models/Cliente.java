package co.ucentral.creditaplication.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "CLIENTE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CLIENTES")
    @SequenceGenerator(name = "SEQ_CLIENTES", sequenceName = "SEQ_CLIENTES", allocationSize = 1)
    @Column(name = "CLI_ID", nullable = false)
    private long id;
    @Column(name = "CLI_INGRESOS", nullable = true)
    private double ingresos;
    @Column(name = "CLI_NOMBRE", nullable = true)
    private String nombre;
    @Column(name = "CLI_APELLIDO", nullable = true)
    private String apellido;
    @Column(name = "CLI_NUMERO_TELEFONICO", nullable = true)
    private String numeroTelefonico;
    @Column(name = "CLI_FECHA_NACIMIENTO", nullable = true)
    private Date fechaNacimiento;
    @Column(name = "CLI_DIRRECCION", nullable = true)
    private String direccion;
    @Column(name = "CLI_CORREO_ELECTRONICO", nullable = true)
    private String correoElectronico;
    @Column(name = "CLI_NUMERO_IDENTIFICACION", nullable = true)
    private String numeroDeIdentificacion;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @Column(name = "CLI_CV", columnDefinition = "TEXT") // Guardar como TEXT en Base64
    private String cv;
    @Column(name = "CLI_PERFIL_LABORAL", nullable = true)
    private String profileDescription;
}
