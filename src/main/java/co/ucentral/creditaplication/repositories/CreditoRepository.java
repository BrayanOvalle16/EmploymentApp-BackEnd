package co.ucentral.creditaplication.repositories;

import co.ucentral.creditaplication.models.Credito;
import co.ucentral.creditaplication.models.CreditoEstadoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CreditoRepository extends JpaRepository<Credito, Long>, JpaSpecificationExecutor<Credito> {

    public List<Credito> findByEsAprobadoEquals(CreditoEstadoEnum esAprobado);

    public Credito findByClienteNumeroDeIdentificacion(String clienteId);
}
