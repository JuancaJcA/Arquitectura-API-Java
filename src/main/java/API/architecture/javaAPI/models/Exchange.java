package API.architecture.javaAPI.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Exchange extends Query {
    private String date;
    private BigDecimal result;
    private Boolean success;
    //Query model
    private Query query;
}
