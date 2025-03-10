package co.ucentral.creditaplication.controllers;

import co.ucentral.creditaplication.models.Credito;
import co.ucentral.creditaplication.models.dtos.CreditInfoByClientDto;
import co.ucentral.creditaplication.models.dtos.CreditStatusChangeRequestDto;
import co.ucentral.creditaplication.services.CreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("Credito")
public class CreditoControlador {

    final
    CreditoService creditService;

    public CreditoControlador(CreditoService creditService) {
        this.creditService = creditService;
    }

    @GetMapping(value = "/credits")
    public ResponseEntity<List<Credito>> list() {
        List<Credito> users = creditService.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/non-approved-credits")
    public ResponseEntity<List<Credito>> listPendingCredits() {
        List<Credito> creditoList = creditService.getAllPending();
        return new ResponseEntity<>(creditoList, HttpStatus.OK);
    }

    @GetMapping(value = "/payment-by-client")
    public ResponseEntity<CreditInfoByClientDto> pagosPorClientId(@RequestParam(value = "id") String id) {
        CreditInfoByClientDto pagos = creditService.getCreditInfoByCLientId(id);
        return new ResponseEntity<>(pagos, HttpStatus.OK);
    }

    @GetMapping(value = "/credit")
    public ResponseEntity<Credito> creditoPorId(@RequestParam(value = "id") long id) {
        Optional<Credito> creditoOptional = creditService.getById(id);
        if(creditoOptional.isPresent()) {
            return new ResponseEntity<>(creditoOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/update-state")
    public ResponseEntity<HttpStatus> changeStatusCredit(@RequestBody CreditStatusChangeRequestDto creditoCreditStatusChangeRequestDto) {
        creditService.updateCreditState(creditoCreditStatusChangeRequestDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Credito> create(@RequestBody Credito credito) {
        Credito userCreated = creditService.save(credito);
        return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<Credito> delete(@RequestParam(value = "id") long id) {
        creditService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

