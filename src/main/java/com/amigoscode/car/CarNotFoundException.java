package com.amigoscode.car;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//class to create own exception called car not found
//extends runtime (checked vs unchecked exceptions)
@ResponseStatus(value = HttpStatus.NOT_FOUND) //annotation changes default 500 error to 'not found' so that we have control
public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException(String message) {
        super(message); //allows you to pass message and invoke message in superclass
    }
}
