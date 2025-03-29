package co.ucentral.creditaplication.models;

public class InvalidJwtException extends Exception{

    public InvalidJwtException(String usernameAlreadyExists) {
        super(usernameAlreadyExists);
    }
}