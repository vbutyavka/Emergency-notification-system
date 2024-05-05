package org.ens.sending.service.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class SmsJson implements Serializable {

    @JsonProperty("mail_id")
    private Long mailId;

    @JsonProperty("address")
    private String address;

    @JsonProperty("text")
    private String text;

    public String getAsJson() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("mail_id", this.mailId);
            jsonMap.put("address", this.address);
            jsonMap.put("text", this.text);
            return mapper.writeValueAsString(jsonMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert object to JSON", e);
        }
    }
}