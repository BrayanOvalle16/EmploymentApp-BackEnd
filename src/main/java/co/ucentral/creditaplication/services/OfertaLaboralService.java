package co.ucentral.creditaplication.services;

import co.ucentral.creditaplication.models.OfertaLaboral;
import co.ucentral.creditaplication.repositories.JobOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfertaLaboralService {

    @Autowired
    private JobOfferRepository ofertaLaboralRepository;

    // Guardar oferta (para empresas)
    public OfertaLaboral crearOfertaLaboral(OfertaLaboral oferta) {
        return ofertaLaboralRepository.save(oferta);
    }

    // Buscar ofertas (para usuarios)
    public List<OfertaLaboral> buscarOfertas(String titulo, String ciudad, String fechaPostulacion, String palabraClave) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("El título es obligatorio para buscar ofertas.");
        }

        // Si no se usa ciudad o fecha, se colocan comodines
        if (ciudad == null) ciudad = "";
        if (fechaPostulacion == null) fechaPostulacion = "0000-01-01"; // fecha mínima

        return ofertaLaboralRepository.findByTituloContainingIgnoreCaseAndCiudadContainingIgnoreCaseAndFechaPostulacionGreaterThanEqual(
                titulo, ciudad, fechaPostulacion);
    }

    // Obtener todas las ofertas (opcional)
    public List<OfertaLaboral> obtenerTodas() {
        return ofertaLaboralRepository.findAll();
    }
}

