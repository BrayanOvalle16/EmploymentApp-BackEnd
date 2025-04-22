package co.ucentral.creditaplication.repositories;

import co.ucentral.creditaplication.models.OfertaLaboral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobOfferRepository extends JpaRepository<OfertaLaboral, Long> {

    // Búsqueda por título
    List<OfertaLaboral> findByTituloContainingIgnoreCase(String titulo);

    // Búsqueda combinada con filtros opcionales que se pueden manejar desde el Service
    List<OfertaLaboral> findByTituloContainingIgnoreCaseAndCiudadContainingIgnoreCaseAndFechaPostulacionGreaterThanEqual(
            String titulo, String ciudad, String fechaDesde);
}
