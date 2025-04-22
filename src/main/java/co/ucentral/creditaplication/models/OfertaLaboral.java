package co.ucentral.creditaplication.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfertaLaboral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String descripcion;

    private String ciudad;

    private String requisitos;

    private LocalDate fechaPublicacion;

    private String palabrasClave;

    private String empresaNombre;

}