package co.ucentral.creditaplication.services;

import co.ucentral.creditaplication.models.Pagos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.ucentral.creditaplication.repositories.PagosRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public class PagosService implements Serializable {
    final
    PagosRepository repository;

    public PagosService(PagosRepository repository) {
        this.repository = repository;
    }

    public Pagos save(Pagos pagos) {
        return repository.save(pagos);
    }

    public List<Pagos> getAll() {
        return repository.findAll();
    }

    public List<Pagos> getAllByCliente(String clienteId) {
        return repository.findAllByCreditoClienteNumeroDeIdentificacion(clienteId);
    }

    public Optional<Pagos> getById(long id) {
        return repository.findById(id);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }
}
