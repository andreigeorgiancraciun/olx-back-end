package com.example.olxpostman.advert;

import com.example.olxpostman.advert.feign.OlxFeignClient;
import com.example.olxpostman.advert.model.advert.Advert;
import com.example.olxpostman.advert.model.advert.AdvertRepository;
import com.example.olxpostman.advert.model.contact.Contact;
import com.example.olxpostman.advert.model.contact.ContactRepository;
import com.example.olxpostman.advert.model.images.Image;
import com.example.olxpostman.advert.model.images.ImageRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AdvertService {
	public static final String CASA_MODULARA = "casa-modulara";
	private static final String CONTAINER = "container";
	public static final String DMT = "dmt";
	public static final String DANI = "dani";

	private final OlxFeignClient olxFeignClient;
	private final AdvertRepository advertRepository;
	private final ImageRepository imageRepository;
	private final ContactRepository contactRepository;

	public AdvertService(OlxFeignClient olxFeignClient,
			AdvertRepository advertRepository,
			ImageRepository imageRepository,
			ContactRepository contactRepository) {
		this.olxFeignClient = olxFeignClient;
		this.advertRepository = advertRepository;
		this.imageRepository = imageRepository;
		this.contactRepository = contactRepository;
	}

	public List<Advert> postAdvertForDaniProfile(String authorization, String version, String contentType) {
		List<Advert> adverts = advertRepository.findAllByAdvertType(CONTAINER);
		adverts.forEach((advert -> {
			Contact contactIonescuDanProfile = getContactIonescuDanProfile();
			advert.setContact(contactIonescuDanProfile);
			setImagesForDaniProfile(advert);

			ObjectMapper ow = new ObjectMapper();
			try {
				String advertJSON = ow.writeValueAsString(advert);
				System.out.println(advertJSON);
				olxFeignClient.postAdvert(authorization, version, contentType, advertJSON);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}));

		return adverts;
	}

	public List<Advert> postAdvertForDmtProfile(String authorization, String version, String contentType) {
		List<Advert> adverts = advertRepository.findAllByAdvertType(CONTAINER);
		adverts.forEach((advert -> {
			Contact contactDMTProfile = getContactDMTProfile();
			advert.setContact(contactDMTProfile);
			setImagesForDmt(advert);

			ObjectMapper ow = new ObjectMapper();
			try {
				String advertJSON = ow.writeValueAsString(advert);
				System.out.println(advertJSON);
				olxFeignClient.postAdvert(authorization, version, contentType, advertJSON);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}));

		return adverts;
	}

	public List<Advert> postAdvertForCasaModularaProfile(String authorization, String version, String contentType) {
		List<Advert> adverts = advertRepository.findAllByAdvertType(CASA_MODULARA);
		adverts.forEach((advert -> {
			Contact contactIonescuDaniProfile = getContactIonescuDanProfile();
			advert.setContact(contactIonescuDaniProfile);
			setImagesForCasaModularaProfile(advert);

			ObjectMapper ow = new ObjectMapper();
			try {
				String advertJSON = ow.writeValueAsString(advert);
				System.out.println(advertJSON);
				olxFeignClient.postAdvert(authorization, version, contentType, advertJSON);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}));

		return adverts;
	}

	private void setImagesForDaniProfile(Advert advert) {
		shuffleImagesForSpecificProfile(DANI, advert);
	}

	private void setImagesForDmt(Advert advert) {
		shuffleImagesForSpecificProfile(DMT, advert);
	}

	private void setImagesForCasaModularaProfile(Advert advert) {
		shuffleImagesForSpecificProfile(CASA_MODULARA, advert);
	}

	private void shuffleImagesForSpecificProfile(String profile, Advert advert) {
		List<Image> images = imageRepository.findAllByType(profile);
		Collections.shuffle(images);
		int randomSeriesLength = 8;
		List<Image> randomSeries = images.subList(0, randomSeriesLength);
		advert.setImages(randomSeries);
	}

	private Contact getContactIonescuDanProfile() {
		return contactRepository.findById(1L).orElseThrow();
	}

	private Contact getContactDMTProfile() {
		return contactRepository.findById(2L).orElseThrow();
	}
}
