package com.example.olxpostman.advert;

import com.example.olxpostman.advert.feign.OlxFeignClient;
import com.example.olxpostman.advert.model.Advert;
import com.example.olxpostman.advert.model.AdvertNotFoundResponseStatusException;
import com.example.olxpostman.advert.model.Contact;
import com.example.olxpostman.advert.model.ContactRepository;
import com.example.olxpostman.advert.model.images.Image;
import com.example.olxpostman.advert.model.images.ImageForDani;
import com.example.olxpostman.advert.model.images.ImageForDaniNewSet;
import com.example.olxpostman.advert.model.images.ImageForDaniNewSetRepository;
import com.example.olxpostman.advert.model.images.ImageForDaniRepository;
import com.example.olxpostman.advert.model.images.ImageRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

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
	private final ImageForDaniNewSetRepository imageForDaniNewSetRepository;

	public AdvertService(OlxFeignClient olxFeignClient,
			AdvertRepository advertRepository,
			ImageRepository imageRepository,
			ImageForDaniRepository imageForDaniRepository,
			ContactRepository contactRepository,
			ImageForDaniNewSetRepository imageForDaniNewSetRepository) {
		this.olxFeignClient = olxFeignClient;
		this.advertRepository = advertRepository;
		this.imageRepository = imageRepository;
		this.imageForDaniRepository = imageForDaniRepository;
		this.contactRepository = contactRepository;
		this.imageForDaniNewSetRepository = imageForDaniNewSetRepository;
	}

	public Advert postAdvertFromDB(String authorization, String version, String contentType, Long id) {

		Advert advert = advertRepository.findById(id)
				.orElseThrow(AdvertNotFoundResponseStatusException::new);

		setImages(advert);

		ObjectMapper ow = new ObjectMapper();
		try {
			String advertJSON = ow.writeValueAsString(advert);
			olxFeignClient.postAdvert(authorization, version, contentType, advertJSON);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return advert;
	}

	public List<Advert> postAllAdvertFromDB(String authorization, String version, String contentType) {
		List<Advert> adverts = advertRepository.findAll();

		adverts.forEach((advert -> {
			Contact contactDaniProfile = contactRepository.findById(2L).orElseThrow();
			advert.setContact(contactDaniProfile);
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

	public List<Advert> postAllAdvertFromDBWithDMTProfileNewSet(String authorization, String version, String contentType) {
		List<Advert> adverts = advertRepository.findAll();

		adverts.forEach((advert -> {
			Contact contactDaniProfile = contactRepository.findById(2L).orElseThrow();
			advert.setContact(contactDaniProfile);
			setImagesForDMTProfileNewProfile(advert);

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

	/*public Advert updateAdvert(String authorization, String version, String contentType) throws JsonProcessingException {
		Advert advert = advertRepository.findById(1L)
				.orElseThrow(AdvertNotFoundResponseStatusException::new);
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String advertJSON = ow.writeValueAsString(advert);
		return olxFeignClient.updateAdvert(authorization, version, contentType, advertJSON);
	}*/

	/*public List<Integer> deleteAllAdvertFromDB(String authorization, String version) {
		List<Integer> adverts = List.of(
				225635274
		);

		adverts.forEach(id -> {
			olxFeignClient.deleteAdvert(authorization, version, id);
		});

		return adverts;
	}*/

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

	private void setImagesForDMTProfileNewProfile(Advert advert) {
		List<ImageForDaniNewSet> images = imageForDaniNewSetRepository.findAll();
		Collections.shuffle(images);
		int randomSeriesLength = 8;
		List<ImageForDaniNewSet> randomSeries = images.subList(0, randomSeriesLength);
		List<Image> randomImages = randomSeries.stream()
				.map(Image::new)
				.collect(Collectors.toList());
		advert.setImages(randomImages);
	}
}
