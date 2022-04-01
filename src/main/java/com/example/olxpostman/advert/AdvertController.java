package com.example.olxpostman.advert;

import com.example.olxpostman.advert.model.advert.Advert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/advert")
public class AdvertController {

	private final AdvertService advertService;

	public AdvertController(AdvertService advertService) {
		this.advertService = advertService;

	}

	@PostMapping("/dani")
	public List<Advert> postDani(
			@RequestHeader("Authorization") String authorization,
			@RequestHeader("Version") String version,
			@RequestHeader("Content-Type") String contentType) {
		return this.advertService.postAdvertForDaniProfile(authorization, version, contentType);
	}

	@PostMapping("/dmt")
	public List<Advert> postDmt(
			@RequestHeader("Authorization") String authorization,
			@RequestHeader("Version") String version,
			@RequestHeader("Content-Type") String contentType) {
		return this.advertService.postAdvertForDmtProfile(authorization, version, contentType);
	}

	@PostMapping("/casa-modulara")
	public List<Advert> postAdvertForCasaModulara(
			@RequestHeader("Authorization") String authorization,
			@RequestHeader("Version") String version,
			@RequestHeader("Content-Type") String contentType) {
		return this.advertService.postAdvertForCasaModularaProfile(authorization, version, contentType);
	}
}