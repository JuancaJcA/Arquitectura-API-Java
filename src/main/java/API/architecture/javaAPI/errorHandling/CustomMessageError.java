package API.architecture.javaAPI.errorHandling;

public class CustomMessageError extends RuntimeException{
    public CustomMessageError (String message) {
        super(message);
    }
}