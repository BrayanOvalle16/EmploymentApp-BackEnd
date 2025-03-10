package co.ucentral.creditaplication.controllers;

import co.ucentral.creditaplication.models.Cliente;
import co.ucentral.creditaplication.models.User;
import co.ucentral.creditaplication.models.dtos.ClienteCreateDto;
import co.ucentral.creditaplication.models.dtos.SignUpDto;
import co.ucentral.creditaplication.models.enums.UserRole;
import co.ucentral.creditaplication.models.excetions.InvalidJwtException;
import co.ucentral.creditaplication.services.AuthService;
import co.ucentral.creditaplication.services.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("Cliente")
public class ClienteControlador {

    private final AuthService service;

    final
    ClienteService clientService;

    private final ModelMapper modelMapper;

    public ClienteControlador(AuthService service, ClienteService clientService, ModelMapper modelMapper) {
        this.service = service;
        this.clientService = clientService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/clients")
    public ResponseEntity<List<Cliente>> list() {
        List<Cliente> users = clientService.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/client")
    public ResponseEntity<Cliente> clientePorId(@RequestParam(value = "id") long id) {
        Optional<Cliente> clienteOptional = clientService.getById(id);
        if(clienteOptional.isPresent()) {
            return new ResponseEntity<>(clienteOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Cliente> create(@RequestBody ClienteCreateDto clienteDto) {
        try {
            User user = service.signUp(new SignUpDto(clienteDto.getNumeroDeIdentificacion(), clienteDto.getPassword(), UserRole.USER));
            var cliente = modelMapper.map(clienteDto, Cliente.class);
            cliente.setUser(user);
            Cliente userCreated = clientService.save(cliente);
            return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
        } catch (InvalidJwtException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/delete")
    public ResponseEntity<Cliente> delete(@RequestParam(value = "id") long id) {
        clientService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
