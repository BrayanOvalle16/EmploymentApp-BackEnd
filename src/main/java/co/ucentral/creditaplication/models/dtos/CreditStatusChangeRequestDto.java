package co.ucentral.creditaplication.models.dtos;

import lombok.Data;

@Data
public class CreditStatusChangeRequestDto {
    private long id;
    private Boolean isApproved;
}
