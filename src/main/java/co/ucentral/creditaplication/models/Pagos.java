package co.ucentral.creditaplication.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "PAGOS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pagos {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PAGOS")
    @SequenceGenerator(name = "SEQ_PAGOS", sequenceName = "SEQ_PAGOS", allocationSize = 1)
    @Column(name = "PAG_ID", nullable = false)
    private long id;
    @Column(name = "PAG_CANTIDAD", nullable = true)
    private Double cantidad;
    @ManyToOne
    @JoinColumn(name="PAG_CREDITO_ID", nullable=true)
    private co.ucentral.creditaplication.models.Credito credito;
    @Column(name = "PAG_FECHA_PAGO", nullable = true)
    private Date fechaPago;

}
