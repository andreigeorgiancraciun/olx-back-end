package com.example.olxpostman.advert;

import com.example.olxpostman.advert.model.advert.Advert;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/advert")
public class AdvertController {

	private final AdvertService advertService;

	public AdvertController(AdvertService advertService) {
		this.advertService = advertService;

	}

	@PostMapping("/post-all-dmt")
	public List<Advert> postAllAdvertsDB(@RequestHeader("Authorization") String authorization,
			@RequestHeader("Version") String version,
			@RequestHeader("Content-Type") String contentType) {
		return this.advertService.postAllAdvertFromDBWithDMTProfile(authorization, version, contentType);
	}

	@PostMapping("/post-all-dani")
	public List<Advert> postAllAdvertsDBWithDaniProfile(@RequestHeader("Authorization") String authorization,
			@RequestHeader("Version") String version,
			@RequestHeader("Content-Type") String contentType) {
		return this.advertService.postAllAdvertFromDBWithDaniProfile(authorization, version, contentType);
	}

	@PostMapping("/post-all-DMT-new-set")
	public List<Advert> postAllAdvertsDBWithDMTProfileNewSet(@RequestHeader("Authorization") String authorization,
			@RequestHeader("Version") String version,
			@RequestHeader("Content-Type") String contentType) {
		return this.advertService.postAllAdvertFromDBWithDMTProfileNewSet(authorization, version, contentType);
	}

	@PostMapping("/post-all-casa-modulara")
	public List<Advert> postAllAdvertsDBWithCasaModularaProfile(@RequestHeader("Authorization") String authorization,
			@RequestHeader("Version") String version,
			@RequestHeader("Content-Type") String contentType) {
		return this.advertService.postAllAdvertFromDBWithCasaModularaProfile(authorization, version, contentType);
	}

	@DeleteMapping("/delete-all")
	public List<Integer> deleteAllAdverts(@RequestHeader("Authorization") String authorization,
			@RequestHeader("Version") String version) {
		return this.advertService.deleteAllAdvertFromDB(authorization, version);
	}

	@PutMapping("/update-db")
	public Advert updateAdvert(@RequestHeader("Authorization") String authorization,
			@RequestHeader("Version") String version,
			@RequestHeader("Content-Type") String contentType) throws JsonProcessingException {
		return this.advertService.updateAdvert(authorization, version, contentType);
	}

	@GetMapping("/user")
	public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
		return Collections.singletonMap("name", principal.getAttribute("name"));
	}

	@GetMapping("/me")
	public String getMyInformation(@RequestHeader("Authorization") String authorization,
			@RequestHeader("Version") String version) {
		return this.advertService.getMyInformation(authorization, version);
	}
}