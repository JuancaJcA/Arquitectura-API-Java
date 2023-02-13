package API.architecture.javaAPI.bl;

import API.architecture.javaAPI.errorHandling.CustomMessageError;
import API.architecture.javaAPI.models.Exchange;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Objects;

@Service
public class ExchangeBL {
    //Logger
    Logger logger = LoggerFactory.getLogger(ExchangeBL.class);

    public String getCustomExchange(String to, String from, BigDecimal amount) {
        //Logger
        logger.info("Custom Exchange BL working");
        //Verify amount greater than ZERO
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new CustomMessageError("Amount value greater or equals to ZERO.");
        } else {
            //Ok Http
            OkHttpClient client = new OkHttpClient().newBuilder().build();

            Request request = new Request.Builder()
                    .url("https://api.apilayer.com/exchangerates_data/convert?to=" + to + "&from=" + from + "&amount=" + amount)
                    .addHeader("apikey", "gybHqhso62y1RsHXMa24UTLgwPy7l4AS")
                    .build();
            Response response;

            try {
                response = client.newCall(request).execute();
                String jsonData = Objects.requireNonNull(response.body()).string();
                //Jackson Library
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                Exchange obj = objectMapper.readValue(jsonData, Exchange.class);
                //Logger
                logger.info("Custom Response: " + obj.toString());

                return obj.toString();
            } catch (IOException e) {
                throw new CustomMessageError(e.toString());
            }
        }
    }

    public String getDefaultExchange() {
        //Logger
        logger.info("Default Exchange BL working (Amount: 100 - From: USD - To: BOB)");
        //Ok Http
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        Request request = new Request.Builder()
                .url("https://api.apilayer.com/exchangerates_data/convert?to=USD&from=BOB&amount=100")
                .addHeader("apikey", "gybHqhso62y1RsHXMa24UTLgwPy7l4AS")
                .build();
        Response response;

        try {
            response = client.newCall(request).execute();
            String jsonData = Objects.requireNonNull(response.body()).string();
            //Jackson Library
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            Exchange obj = objectMapper.readValue(jsonData, Exchange.class);
            //Logger
            logger.info("Default Response: " + obj.toString());

            return obj.toString();
        } catch (IOException e) {
            throw new CustomMessageError(e.toString());
        }
    }
}
