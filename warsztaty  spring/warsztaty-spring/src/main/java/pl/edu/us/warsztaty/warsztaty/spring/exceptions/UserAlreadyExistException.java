package pl.edu.us.warsztaty.warsztaty.spring.exceptions;

public class UserAlreadyExistException extends RuntimeException{

    public UserAlreadyExistException(String msg){
        super(msg);
    }
}
