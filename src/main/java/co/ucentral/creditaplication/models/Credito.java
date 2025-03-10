package co.ucentral.creditaplication.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "CREDITO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Credito {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CREDITO")
    @SequenceGenerator(name = "SEQ_CREDITO", sequenceName = "SEQ_CREDITO", allocationSize = 1)
    @Column(name = "CRE_ID", nullable = false)
    private long id;
    @Column(name = "CRE_TIPO", nullable = true)
    private TipoCredito tipo;
    @Column(name = "CRE_CREDITO_APROBADO", nullable = true)
    private Double creditoAprobado;
    @Column(name = "CRE_PORCENTAJE_INTERES", nullable = true)
    private Double porcentajeInteres;
    @Column(name = "CRE_CANTIDAD_SOLICITADA", nullable = true)
    private Double cantidadSolicitada;
    @Column(name = "CRE_ES_APROBADO", nullable = true)
    private CreditoEstadoEnum esAprobado;
    @Column(name = "CRE_FECHA_APROBACION", nullable = true)
    private Date fechaDeAprobacion;
    @Column(name = "CRE_NUMERO_CUOTAS", nullable = true)
    private int numeroDeCuotas;
    @Column(name = "CRE_DIA_DE_PAGO", nullable = true)
    private int diaDePago;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="CRE_CLIENT_ID", nullable=true)
    private Cliente cliente;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Pagos> pagosList;
}
