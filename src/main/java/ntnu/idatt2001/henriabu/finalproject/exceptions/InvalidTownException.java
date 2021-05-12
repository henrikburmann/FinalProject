package ntnu.idatt2001.henriabu.finalproject.exceptions;

/**
  Exception that is being thrown when an invalid town is written
 */

public class InvalidTownException extends Exception{
    public InvalidTownException(String message){
        super(message);
    }
}
