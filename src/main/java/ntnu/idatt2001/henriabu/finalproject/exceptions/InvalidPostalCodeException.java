package ntnu.idatt2001.henriabu.finalproject.exceptions;

/**
 * Exception that is being thrown when an invalid postal code is written
 */
public class InvalidPostalCodeException extends Exception {

    public InvalidPostalCodeException(String message){
        super(message);
    }
}
