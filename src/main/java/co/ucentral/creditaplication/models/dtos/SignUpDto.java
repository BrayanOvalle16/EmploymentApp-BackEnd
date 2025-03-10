package co.ucentral.creditaplication.models.dtos;

import co.ucentral.creditaplication.models.enums.UserRole;

public record SignUpDto(
        String login,
        String password,
        UserRole role) {
}
