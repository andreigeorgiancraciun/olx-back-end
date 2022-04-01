package com.example.olxpostman.advert.model.advert;

import com.example.olxpostman.advert.model.Attribute;
import com.example.olxpostman.advert.model.Description;
import com.example.olxpostman.advert.model.Location;
import com.example.olxpostman.advert.model.Price;
import com.example.olxpostman.advert.model.Title;
import com.example.olxpostman.advert.model.contact.Contact;
import com.example.olxpostman.advert.model.images.Image;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
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
@Getter
@Setter
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

	@JsonIgnore
	private String advertType;
}