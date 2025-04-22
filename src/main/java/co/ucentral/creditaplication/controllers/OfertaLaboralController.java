package co.ucentral.creditaplication.controllers;

import co.ucentral.creditaplication.models.OfertaLaboral;
import co.ucentral.creditaplication.services.OfertaLaboralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ofertas")
public class OfertaLaboralController {

    @Autowired
    private OfertaLaboralService ofertaLaboralService;

    @PostMapping("/crear")
    public ResponseEntity<OfertaLaboral> crearOferta(@RequestBody OfertaLaboral oferta) {
        OfertaLaboral nueva = ofertaLaboralService.crearOfertaLaboral(oferta);
        return ResponseEntity.ok(nueva);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<OfertaLaboral>> buscarOfertas(
            @RequestParam String titulo,
            @RequestParam(required = false) String ciudad,
            @RequestParam(required = false) String palabrasClave,
            @RequestParam(required = false) String fecha // formato: yyyy-MM-dd
    ) {
        List<OfertaLaboral> resultados = ofertaLaboralService.buscarOfertas(titulo, ciudad, palabrasClave, fecha);
        return ResponseEntity.ok(resultados);
    }


    @GetMapping("/todas")
    public ResponseEntity<List<OfertaLaboral>> obtenerTodasLasOfertas() {
        List<OfertaLaboral> ofertas = ofertaLaboralService.obtenerTodas();
        return ResponseEntity.ok(ofertas);
    }
}
