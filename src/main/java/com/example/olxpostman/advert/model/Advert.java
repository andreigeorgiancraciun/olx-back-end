package com.example.olxpostman.advert.model;

import com.example.olxpostman.advert.model.images.Image;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "title",
        "description",
        "category_id",
        "advertiser_type",
        "contact",
        "location",
        "images",
        "price",
        "attributes"
})
@Data
@Entity
@Table(name = "adverts")
public class Advert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    Long id;

    @JsonProperty("title")
	@ManyToOne
    private Title title;
    @JsonProperty("description")
	@ManyToOne
    private Description description;
    @JsonProperty("category_id")
    private Integer categoryId;
    @JsonProperty("advertiser_type")
    private String advertiserType;

    @JsonProperty("contact")
    @ManyToOne
    private Contact contact;

    @JsonProperty("location")
    @ManyToOne
    private Location location;

    @JsonProperty("images")
	@Transient
    private List<Image> images = null;

    @JsonProperty("price")
    @ManyToOne
    private Price price;

    @JsonProperty("attributes")
    @OneToMany
    @JoinColumn(name = "advert_id")
    private List<Attribute> attributes = null;
}