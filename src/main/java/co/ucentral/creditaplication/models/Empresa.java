package co.ucentral.creditaplication.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "EMPRESA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_EMPRESAS")
    @SequenceGenerator(name = "SEQ_EMPRESAS", sequenceName = "SEQ_EMPRESAS", allocationSize = 1)
    @Column(name = "EMP_ID", nullable = false)
    private long id;
    @Column(name = "EMP_NOMBRE", nullable = true)
    private String nombreEmpresa;
    @Column(name = "EMP_INDUSTRIA", nullable = true)
    private String industria;
    @Column(name = "EMP_SITIOWEB", nullable = true)
    private String sitioWeb;
    @Column(name = "EMP_CORREO", nullable = true)
    private String correo;
    @Column(name = "EMP_TELEFONO", nullable = true)
    private String telefono;
    @Column(name = "EMP_DIRRECCION", nullable = true)
    private String direccion;
    @Column(name = "EMP_DESCRIPCION", nullable = true)
    private String descripcion;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @Column(name = "EMP_TAMANO", nullable = true)
    private int tamano;
    @Column(name = "EMP_FUNDACION", nullable = true)
    private int anioFundacion;
    @Column(name = "EMP_LINKEDING", nullable = true)
    private String linkeding;
    @Column(name = "EMP_TWITTER", nullable = true)
    private String twitter;
}
