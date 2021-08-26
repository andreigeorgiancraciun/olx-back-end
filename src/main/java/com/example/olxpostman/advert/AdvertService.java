package com.example.olxpostman.advert;

import com.example.olxpostman.advert.feign.OlxFeignClient;
import com.example.olxpostman.advert.model.Advert;
import com.example.olxpostman.advert.model.AdvertNotFoundResponseStatusException;
import com.example.olxpostman.advert.model.Contact;
import com.example.olxpostman.advert.model.ContactRepository;
import com.example.olxpostman.advert.model.Image;
import com.example.olxpostman.advert.model.ImageForDani;
import com.example.olxpostman.advert.model.ImageForDaniRepository;
import com.example.olxpostman.advert.model.ImageRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdvertService {

	private final OlxFeignClient olxFeignClient;
	private final AdvertRepository advertRepository;
	private final ImageRepository imageRepository;
	private final ImageForDaniRepository imageForDaniRepository;
	private final ContactRepository contactRepository;

	public AdvertService(OlxFeignClient olxFeignClient,
			AdvertRepository advertRepository,
			ImageRepository imageRepository, ImageForDaniRepository imageForDaniRepository, ContactRepository contactRepository) {
		this.olxFeignClient = olxFeignClient;
		this.advertRepository = advertRepository;
		this.imageRepository = imageRepository;
		this.imageForDaniRepository = imageForDaniRepository;
		this.contactRepository = contactRepository;
	}

	public Advert postAdvertFromDB(String authorization, String version, String contentType, Long id) throws JsonProcessingException {

		Advert advert = advertRepository.findById(id)
				.orElseThrow(AdvertNotFoundResponseStatusException::new);

		setImages(advert);

		ObjectMapper ow = new ObjectMapper();
		String advertJSON = ow.writeValueAsString(advert);
		return olxFeignClient.postAdvert(authorization, version, contentType, advertJSON);
	}

	private void setImages(Advert advert) {
		List<Image> images = imageRepository.findAll();
		Collections.shuffle(images);
		int randomSeriesLength = 8;
		List<Image> randomSeries = images.subList(0, randomSeriesLength);
		advert.setImages(randomSeries);
	}

	private void setImagesForDaniProfile(Advert advert) {
		List<ImageForDani> images = imageForDaniRepository.findAll();
		Collections.shuffle(images);
		int randomSeriesLength = 8;
		List<ImageForDani> randomSeries = images.subList(0, randomSeriesLength);
		List<Image> randomImages = randomSeries.stream()
				.map(Image::new)
				.collect(Collectors.toList());
		advert.setImages(randomImages);
	}

	public List<Advert> postAllAdvertFromDB(String authorization, String version, String contentType) {
		List<Advert> adverts = advertRepository.findAll();

		adverts.forEach((advert -> {
			setImages(advert);

			ObjectMapper ow = new ObjectMapper();
			try {
				String advertJSON = ow.writeValueAsString(advert);
				olxFeignClient.postAdvert(authorization, version, contentType, advertJSON);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}));

		return adverts;
	}

	public List<Advert> postAllAdvertFromDBWithDaniProfile(String authorization, String version, String contentType) {
		List<Advert> adverts = advertRepository.findAll();

		adverts.forEach((advert -> {
			Contact contactDaniProfile = contactRepository.findById(1L).orElseThrow();
			advert.setContact(contactDaniProfile);
			setImagesForDaniProfile(advert);

			ObjectMapper ow = new ObjectMapper();
			try {
				String advertJSON = ow.writeValueAsString(advert);
				olxFeignClient.postAdvert(authorization, version, contentType, advertJSON);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}));

		return adverts;
	}

	public Advert updateAdvert(String authorization, String version, String contentType) throws JsonProcessingException {
		Advert advert = advertRepository.findById(1L)
				.orElseThrow(AdvertNotFoundResponseStatusException::new);
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String advertJSON = ow.writeValueAsString(advert);
		return olxFeignClient.updateAdvert(authorization, version, contentType, advertJSON);
	}

	public String getMyInformation(String authorization, String version) {
		return olxFeignClient.getMyInformation(authorization, version);
	}

	public List<Integer> deleteAllAdvertFromDB(String authorization, String version) {
		List<Integer> adverts = List.of(

				223486462,
				221560372,
				221560398,
				221560409,
				221560414,
				221560422,
				221331339,
				221560430,
				221560449,
				221560487,
				221324137,
				221560493,
				221560498,
				221560263,
				221560279,
				221560221,
				221331409,
				221324125,
				221324069,
				221560230,
				221324107,
				221560305,
				221331542,
				221324090,
				221560389,
				221560478,
				221560365,
				221560381,
				221560466,
				221560436

		);

		adverts.forEach(id -> {
			olxFeignClient.deleteAdvert(authorization, version, id);
		});

		return adverts;
	}
}
