package co.ucentral.creditaplication.models.enums;

public enum CompanyRole {
    ADMINISTRADOR("administrador"),
    COMPANY("company");

    private String role;

    CompanyRole(String role) {
        this.role = role;
    }

    public String getValue() {
        return role;
    }
}
