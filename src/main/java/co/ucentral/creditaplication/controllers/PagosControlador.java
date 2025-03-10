package co.ucentral.creditaplication.controllers;
import co.ucentral.creditaplication.models.Pagos;
import co.ucentral.creditaplication.services.PagosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("Pagos")
public class PagosControlador {

    final
    PagosService paymentsService;

    public PagosControlador(PagosService paymentsService) {
        this.paymentsService = paymentsService;
    }

    @GetMapping(value = "/payments")
    public ResponseEntity<List<Pagos>> list() {
        List<Pagos> users = paymentsService.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/payment")
    public ResponseEntity<Pagos> pagosPorId(@RequestParam(value = "id") long id) {
        Optional<Pagos> pagosOptional = paymentsService.getById(id);
        if(pagosOptional.isPresent()) {
            return new ResponseEntity<>(pagosOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/payment-by-client")
    public ResponseEntity<List<Pagos>> pagosPorClientId(@RequestParam(value = "id") String id) {
        List<Pagos> pagos = paymentsService.getAllByCliente(id);
        return new ResponseEntity<>(pagos, HttpStatus.OK);
    }


    @PostMapping(value = "/payments")
    public ResponseEntity<Pagos> create(@RequestBody Pagos pagos) {
        Pagos userCreated = paymentsService.save(pagos);
        return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/payments")
    public ResponseEntity<Pagos> delete(@RequestParam(value = "id") long id) {
        paymentsService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

