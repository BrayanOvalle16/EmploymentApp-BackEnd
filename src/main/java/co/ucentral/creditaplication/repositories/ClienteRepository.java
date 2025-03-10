package co.ucentral.creditaplication.repositories;

import co.ucentral.creditaplication.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ClienteRepository extends JpaRepository<Cliente, Long>, JpaSpecificationExecutor<Cliente> {
    Cliente findByNumeroDeIdentificacion(String numeroDeIdentificacion);
}
