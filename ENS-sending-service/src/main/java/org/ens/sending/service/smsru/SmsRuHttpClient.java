package org.ens.sending.service.smsru;

import org.ens.sending.service.entity.SmsJson;
import org.ens.sending.service.exception.SendingException;
import org.ens.sending.service.exception.WrongUriException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    protected static Logger log;

    //TODO: убрать перед пушем блеать!
    private static final String apiId = "";

    public static int makePostRequest(UUID uuid, SmsJson smsJson){
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(new URI("https://sms.ru/sms/send"))
                    .POST(HttpRequest.BodyPublishers.ofString("api_id=" + apiId + "&to=" + smsJson.getAddress() + "&msg=" + smsJson.getText() + "&json=1"))
                    .header("Content-type", "application/x-www-form-urlencoded").build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            log.info("[" + uuid + "] " + response.body());
            return response.statusCode();

        } catch (URISyntaxException e) {
            log.error("[" + uuid + "] URISyntaxException: " + e.getMessage());
            throw new WrongUriException(uuid, e.getMessage());
        } catch (IOException | InterruptedException e){
            log.error("[" + uuid + "] SendingException: " + e.getMessage());
            throw new SendingException(uuid, e.getMessage());
        }
    }
}
