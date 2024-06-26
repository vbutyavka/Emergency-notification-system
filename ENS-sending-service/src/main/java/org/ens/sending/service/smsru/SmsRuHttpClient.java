package org.ens.sending.service.smsru;

import org.ens.sending.service.entity.SmsJson;
import org.ens.sending.service.exception.SendingException;
import org.ens.sending.service.exception.WrongUriException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

@Component
public class SmsRuHttpClient {

    private static final String apiId = "dont_use_original_api_id_in_test";

    public static int makePostRequest(UUID uuid, SmsJson smsJson){
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(new URI("https://sms.ru/sms/send"))
                    .POST(HttpRequest.BodyPublishers.ofString("api_id=" + apiId + "&to=" + smsJson.getAddress() + "&msg=" + smsJson.getText() + "&json=1"))
                    .header("Content-type", "application/x-www-form-urlencoded").build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.statusCode();

        } catch (URISyntaxException e) {
            throw new WrongUriException(uuid, e.getMessage());
        } catch (IOException | InterruptedException e){
            throw new SendingException(uuid, e.getMessage());
        }
    }
}
