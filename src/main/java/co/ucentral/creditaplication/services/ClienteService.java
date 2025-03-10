package co.ucentral.creditaplication.services;

import co.ucentral.creditaplication.dtos.CustomerLoginDto;
import co.ucentral.creditaplication.models.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.ucentral.creditaplication.repositories.ClienteRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements Serializable {
    final
    ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public Cliente save(Cliente cliente) {
        return repository.save(cliente);
    }

    public Cliente login(CustomerLoginDto login) {
        return repository.findByNumeroDeIdentificacion(login.getUsername());
    }

    public List<Cliente> getAll() {
        return repository.findAll();
    }

    public Optional<Cliente> getById(long id) {
        return repository.findById(id);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }
}
