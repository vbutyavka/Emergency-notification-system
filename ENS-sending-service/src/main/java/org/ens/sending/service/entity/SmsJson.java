package org.ens.sending.service.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Entity
@Table(name = "Sms_Json")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class SmsJson implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonProperty("mail_id")
    private Long mailId;

    @JsonProperty("address")
    private String address;

    @JsonProperty("text")
    private String text;
}