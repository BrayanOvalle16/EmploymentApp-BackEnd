package co.ucentral.creditaplication.models.dtos;

import java.sql.Date;

public record CreditInfoByClientDto(
        Long id,
        Double pagoMinimo,
        Double pagoTotal,
        Date fechaPago,
        Double interes,
        Double totalPrestamo
) {
}
