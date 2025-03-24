package co.ucentral.creditaplication.services;


import co.ucentral.creditaplication.dtos.CompanyLoginDto;
import co.ucentral.creditaplication.dtos.CustomerLoginDto;
import co.ucentral.creditaplication.models.Cliente;
import co.ucentral.creditaplication.models.Empresa;
import co.ucentral.creditaplication.models.dtos.EmpresaDto;
import org.springframework.stereotype.Service;
import co.ucentral.creditaplication.repositories.EmpresaRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService implements Serializable {
    final
    EmpresaRepository repository;

    public EmpresaService(EmpresaRepository repository) {
        this.repository = repository;
    }

    public Empresa save(Empresa empresa) {
        return repository.save(empresa);
    }

    public Empresa login(CompanyLoginDto login) {
        return repository.findByNombreEmpresa(login.getUsername());
    }

    public List<Empresa> getAll() {
        return repository.findAll();
    }

    public Optional<Empresa> getById(long id) {
        return repository.findById(id);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }
}
