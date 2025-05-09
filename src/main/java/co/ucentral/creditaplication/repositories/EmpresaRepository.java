package co.ucentral.creditaplication.repositories;

import co.ucentral.creditaplication.models.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EmpresaRepository extends JpaRepository<Empresa, Long>, JpaSpecificationExecutor<Empresa> {
    Empresa findByNombreEmpresa(String nombreEmpresa);
    Empresa findByUser_Id(Long userId);
}

