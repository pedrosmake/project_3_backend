package org.example.validators;


import org.example.exceptions.InvalidException;
import org.example.models.RegisterRequest;
import org.example.exceptions.Entity;

public class RegisterValidator {

    public void validate(RegisterRequest registerRequest)
            throws InvalidException {
        if(!registerRequest.getPassword().equals(registerRequest.getRepeatPassword())){
            throw new InvalidException(Entity.REGISTER_PASSWORD, " passwords must be the same");
        }
    }

}
