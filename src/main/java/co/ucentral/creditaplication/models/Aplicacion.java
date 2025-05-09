package co.ucentral.creditaplication.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "APLICACION")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aplicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_APLICACION")
    @SequenceGenerator(name = "SEQ_APLICACION", sequenceName = "SEQ_APLICACION", allocationSize = 1)
    @Column(name = "APL_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "OFERTA_ID")
    private OfertaTrabajo oferta;

    @ManyToOne
    @JoinColumn(name = "CLI_ID")
    private Cliente cliente;

    @Column(name = "FECHA_APLICACION")
    private Date fechaAplicacion;
}
