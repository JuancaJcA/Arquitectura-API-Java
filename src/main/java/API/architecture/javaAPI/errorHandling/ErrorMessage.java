package API.architecture.javaAPI.errorHandling;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorMessage {
    private Date timestamp;
    private String message;
}
