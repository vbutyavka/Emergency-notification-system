package org.ens.sending.service.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

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
}