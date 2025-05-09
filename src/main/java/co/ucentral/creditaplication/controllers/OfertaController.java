package co.ucentral.creditaplication.controllers;

import co.ucentral.creditaplication.models.Aplicacion;
import co.ucentral.creditaplication.models.Cliente;
import co.ucentral.creditaplication.models.OfertaTrabajo;
import co.ucentral.creditaplication.repositories.AplicacionRepository;
import co.ucentral.creditaplication.repositories.ClienteRepository;
import co.ucentral.creditaplication.repositories.OfertaTrabajoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ofertas")
@RequiredArgsConstructor
public class OfertaController {

    private final OfertaTrabajoRepository ofertaRepo;
    private final AplicacionRepository aplicacionRepo;
    private final ClienteRepository clienteRepo;

    // Crear nueva oferta
    @PostMapping("/crear")
    public ResponseEntity<OfertaTrabajo> crearOferta(@RequestBody OfertaTrabajo oferta) {
        return ResponseEntity.ok(ofertaRepo.save(oferta));
    }

    // Aplicar a una oferta
    @PostMapping("/{id}/aplicar")
    public ResponseEntity<?> aplicar(@PathVariable Long id, @RequestParam Long clienteId) {
        Optional<OfertaTrabajo> oferta = ofertaRepo.findById(id);
        Optional<Cliente> cliente = clienteRepo.findById(clienteId);

        if (oferta.isPresent() && cliente.isPresent()) {
            Aplicacion aplicacion = new Aplicacion();
            aplicacion.setOferta(oferta.get());
            aplicacion.setCliente(cliente.get());
            aplicacion.setFechaAplicacion(new Date(System.currentTimeMillis()));
            return ResponseEntity.ok(aplicacionRepo.save(aplicacion));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Oferta o cliente no encontrado");
    }

    // Ver aplicaciones de una empresa
    @GetMapping("/empresa/{empresaId}/aplicaciones")
    public ResponseEntity<List<Aplicacion>> verAplicaciones(@PathVariable Long empresaId) {
        return ResponseEntity.ok(aplicacionRepo.findByOfertaEmpresaId(empresaId));
    }

    // Descargar CV de un cliente
    @GetMapping("/cv/{clienteId}")
    public ResponseEntity<byte[]> descargarCv(@PathVariable Long clienteId) {
        Optional<Cliente> cliente = clienteRepo.findById(clienteId);
        if (cliente.isPresent() && cliente.get().getCv() != null) {
            byte[] cvBytes = Base64.getDecoder().decode(cliente.get().getCv());
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=cv.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(cvBytes);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Listar todas las ofertas
    @GetMapping("/")
    public ResponseEntity<List<OfertaTrabajo>> listarOfertas() {
        List<OfertaTrabajo> ofertas = ofertaRepo.findAll();
        return ResponseEntity.ok(ofertas);
    }
}
