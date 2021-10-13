package com.example.olxpostman.advert.model.images;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "url"
})

@RequiredArgsConstructor
@Data
@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @JsonProperty("url")
    private String url;

	public Image(ImageForDani imageForDani) {
		this.id = imageForDani.getId();
		this.url = imageForDani.getUrl();
	}

	public Image(ImageForDaniNewSet imageForDaniNewSet) {
		this.id = imageForDaniNewSet.getId();
		this.url = imageForDaniNewSet.getUrl();
	}
}