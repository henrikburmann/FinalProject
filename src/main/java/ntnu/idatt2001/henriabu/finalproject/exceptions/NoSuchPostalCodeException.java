package ntnu.idatt2001.henriabu.finalproject.exceptions;

import java.io.Serial;
import java.io.Serializable;

public class NoSuchPostalCodeException extends Exception {

    public NoSuchPostalCodeException(String message){
        super(message);
    }

}
