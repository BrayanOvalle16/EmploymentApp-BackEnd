package co.ucentral.creditaplication.services;

import co.ucentral.creditaplication.models.Credito;

import co.ucentral.creditaplication.models.CreditoEstadoEnum;
import co.ucentral.creditaplication.models.Pagos;
import co.ucentral.creditaplication.models.TipoCredito;
import co.ucentral.creditaplication.models.dtos.CreditInfoByClientDto;
import co.ucentral.creditaplication.models.dtos.CreditStatusChangeRequestDto;
import co.ucentral.creditaplication.repositories.PagosRepository;
import org.springframework.stereotype.Service;
import co.ucentral.creditaplication.repositories.CreditoRepository;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class CreditoService implements Serializable {
    final
    CreditoRepository repository;

    final
    PagosRepository pagosRepository;

    public CreditoService(CreditoRepository repository, PagosRepository pagosRepository) {
        this.repository = repository;
        this.pagosRepository = pagosRepository;
    }

    public Credito save(Credito credito) {
        if(!ObjectUtils.isEmpty(credito.getTipo())){
            credito.setPorcentajeInteres(calculateInterestRate(credito.getTipo()));
        }
        credito.setEsAprobado(CreditoEstadoEnum.PENDIENTE);
        return repository.save(credito);
    }
    public List<Credito> getAll() {
        return repository.findAll();
    }

    public List<Credito> getAllPending() {
        return repository.findByEsAprobadoEquals(CreditoEstadoEnum.PENDIENTE);
    }

    public void updateCreditState(CreditStatusChangeRequestDto creditStatusChangeRequestDto) {
        Optional<Credito> credito = getById(creditStatusChangeRequestDto.getId());
        if(credito.isPresent()) {
            CreditoEstadoEnum estado = creditStatusChangeRequestDto.getIsApproved() ? CreditoEstadoEnum.APPROVADO : CreditoEstadoEnum.RECHAZADO;
            credito.get().setEsAprobado(estado);
            this.repository.save(credito.get());
        }
    }

    public Optional<Credito> getById(long id) {
        return repository.findById(id);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }


    public CreditInfoByClientDto getCreditInfoByCLientId(String clientId) {
        Credito credito = this.repository.findByClienteNumeroDeIdentificacion(clientId);
        if(credito != null) {
            Double tasaInteres = calculateInterestRate(credito.getTipo());
            Double pagoTotal =getTotalPayment(credito);
            Date fechaPago = getCurrentPaymertDate(credito);
            Double pagoMinimo = getMinumumPayment(credito);
            return new CreditInfoByClientDto(credito.getId(), pagoMinimo,
                    pagoTotal, fechaPago, tasaInteres, credito.getCantidadSolicitada());
        }
        return null;
    }

    protected Double getMinumumPayment(Credito credito) {
        var tasaInteresMensual = credito.getPorcentajeInteres() / 100;
        return credito.getCantidadSolicitada() * (tasaInteresMensual / (1 - Math.pow(1 + tasaInteresMensual, - credito.getNumeroDeCuotas())));
    }
    protected Date getCurrentPaymertDate(Credito credito) {
        List<Pagos> pagos = this.pagosRepository.findAllByCreditoId(credito.getId());
        int diaPago = credito.getDiaDePago();
        Date diaPagoFecha = new Date(System.currentTimeMillis());
        diaPagoFecha.setDate(diaPago);

        if(pagos != null && !pagos.isEmpty()) {
            Date finalDiaPagoFecha = diaPagoFecha;
            Boolean esMesPago = pagos.stream().anyMatch(x -> x.getCredito().getId() == credito.getId() &&
                    x.getFechaPago().getMonth() == finalDiaPagoFecha.getMonth());
            if(Boolean.TRUE.equals(esMesPago)) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(diaPagoFecha);
                calendar.add(Calendar.MONTH, 1);
                diaPagoFecha = new Date(calendar.getTimeInMillis()) ;
            }
        }
        return  diaPagoFecha;
    }
    protected Double getTotalPayment(Credito credito) {
        Double cantidadSolicitada = credito.getCantidadSolicitada();
        List<Pagos> pagos = pagosRepository.findAllByCreditoId(credito.getId());
        Double totalPago = getTotalPago(pagos);

        Double totalpago = cantidadSolicitada - totalPago;
        Double pagoMinimo = getMinumumPayment(credito);

        Double totalPagoMenosUnaCuota = totalpago - (totalpago / credito.getNumeroDeCuotas());
        return totalPagoMenosUnaCuota + pagoMinimo;
    }

    protected Double getTotalPago(List<Pagos> pagos) {
        return pagos.stream()
                .map(x -> x.getCantidad())
                .reduce(0d, Double::sum);
    }
    protected Double calculateInterestRate(TipoCredito tipoCredito) {
        switch (tipoCredito){
            case VIVIENDA -> {
                return 1.1;
            }
            case ESTUDIO -> {
                return 0.9;
            }
            case VEHICULO -> {
                return 1.7;
            }
            case COMPRA_DE_CARTERA -> {
                return 0.8;
            }
            case LIBREINVERSION -> {
                return 1.5;
            }
            default -> {
                return 0d;
            }
        }
    }
}
