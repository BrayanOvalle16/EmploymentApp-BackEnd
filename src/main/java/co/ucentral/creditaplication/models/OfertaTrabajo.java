package co.ucentral.creditaplication.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "OFERTA_TRABAJO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfertaTrabajo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_OFERTA")
    @SequenceGenerator(name = "SEQ_OFERTA", sequenceName = "SEQ_OFERTA", allocationSize = 1)
    @Column(name = "OFERTA_ID")
    private Long id;

    @Column(name = "TITULO")
    private String titulo;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "REQUISITOS")
    private String requisitos;

    @Column(name = "SALARIO")
    private Double salario;

    @ManyToOne
    @JoinColumn(name = "EMP_ID")
    private Empresa empresa;
}
