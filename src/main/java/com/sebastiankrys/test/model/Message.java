package com.sebastiankrys.test.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name = "message")
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    private String message;

    @Column(unique = true, nullable = false)
    private String uuid;

    public Message() {
        this.uuid = generateUUID();
    }

    private String generateUUID() {
        return UUID.randomUUID().toString();
    }

}
