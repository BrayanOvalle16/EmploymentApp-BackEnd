package co.ucentral.creditaplication.repositories;

import co.ucentral.creditaplication.models.Aplicacion;
import co.ucentral.creditaplication.models.OfertaTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfertaTrabajoRepository extends JpaRepository<OfertaTrabajo, Long> {}
