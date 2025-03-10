package co.ucentral.creditaplication.repositories;

import co.ucentral.creditaplication.models.Pagos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PagosRepository extends JpaRepository<Pagos, Long>, JpaSpecificationExecutor<Pagos> {
    public List<Pagos> findAllByCreditoClienteNumeroDeIdentificacion(String creditoClienteId);
    public List<Pagos> findAllByCreditoId(Long creditoId);
}
