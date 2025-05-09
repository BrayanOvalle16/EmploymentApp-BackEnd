package co.ucentral.creditaplication.repositories;

import co.ucentral.creditaplication.models.Aplicacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AplicacionRepository extends JpaRepository<Aplicacion, Long> {
    List<Aplicacion> findByOfertaEmpresaId(Long empresaId);
}
