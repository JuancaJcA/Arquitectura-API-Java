package API.architecture.javaAPI.api;

import API.architecture.javaAPI.bl.ExchangeBL;
import API.architecture.javaAPI.models.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1")
public class ExchangeAPI {

    //Business Logic
    private final ExchangeBL exchangeBL;
    //Logger
    Logger logger = LoggerFactory.getLogger(ExchangeAPI.class);

    @Autowired
    public ExchangeAPI(ExchangeBL exchangeBL) {
        this.exchangeBL = exchangeBL;
    }

    @GetMapping(value = "exchange/{to}/{from}/{amount}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getExchange(@PathVariable Optional<String> to, @PathVariable Optional<String> from,
                              @PathVariable Optional<BigDecimal> amount) {
        //Logger
        logger.info("Custom Exchange API working");
        //Custom or Default values
        if (to.isPresent() && from.isPresent() && amount.isPresent()) {
            //Query values
            Query obj = new Query(amount.get(), from.get(), to.get());
            return exchangeBL.getCustomExchange(obj.getTo(), obj.getFrom(), obj.getAmount());
        } else {
            return exchangeBL.getDefaultExchange();
        }

    }

    @GetMapping(value = "exchange", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getDefaultExchange() {
        //Logger
        logger.info("Default Exchange API working");
        //Default values
        return exchangeBL.getDefaultExchange();
    }
}
