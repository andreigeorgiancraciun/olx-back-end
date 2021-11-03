package com.example.olxpostman.advert.model;

import com.example.olxpostman.advert.model.advert.Advert;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.persistence.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "code",
        "value"
})
@Data
@Entity
@Table(name = "attributes")
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @JsonProperty("code")
    private String code;
    @JsonProperty("value")
    private String value;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "advert_id")
    private Advert advert;
}