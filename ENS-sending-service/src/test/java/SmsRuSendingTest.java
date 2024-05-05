import org.ens.sending.service.entity.SmsJson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SmsRuSendingTest {


    //TODO: убрать перед пушем блеать!
    private String apiId = "";



    @Test
    public void meTest() throws URISyntaxException, IOException, InterruptedException {
        SmsJson smsJson = getSmsJson("89312808487");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(new URI("https://sms.ru/sms/send"))
                .POST(HttpRequest.BodyPublishers.ofString("api_id=" + apiId + "&from=baranov&to=" + smsJson.getAddress() + "&msg=" + smsJson.getText() + "&json=1"))
                .header("Content-type", "application/x-www-form-urlencoded").build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Assertions.assertEquals(200, response.statusCode());
        System.out.println(response.body());
    }

    private SmsJson getSmsJson(String address) {
        SmsJson smsJson = new SmsJson();
        smsJson.setAddress(address);
        smsJson.setText("Hi!\nIt's test message from Vladik's diploma.\nIf U got it - know, that he's happy!\nRespectfully!");
        return smsJson;
    }

}
