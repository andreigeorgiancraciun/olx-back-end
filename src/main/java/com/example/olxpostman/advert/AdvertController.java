package com.example.olxpostman.advert;

import com.example.olxpostman.advert.model.Advert;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/advert")
public class AdvertController {

    private final AdvertService advertService;

    public AdvertController(AdvertService advertService) {
        this.advertService = advertService;

    }

    @PostMapping("/single-post/{id}")
    public Advert postSpecificAdvertDB(@RequestHeader("Authorization") String authorization,
                                       @RequestHeader("Version") String version,
                                       @RequestHeader("Content-Type") String contentType,
                                       @PathVariable Long id) throws JsonProcessingException {
        return this.advertService.postAdvertFromDB(authorization, version, contentType, id);
    }

    @PostMapping("/post-all-dmt")
    public List<Advert> postAllAdvertsDB(@RequestHeader("Authorization") String authorization,
                                         @RequestHeader("Version") String version,
                                         @RequestHeader("Content-Type") String contentType) {
        return this.advertService.postAllAdvertFromDB(authorization, version, contentType);
    }

	@PostMapping("/post-all-dani")
	public List<Advert> postAllAdvertsDBWithDaniProfile(@RequestHeader("Authorization") String authorization,
			@RequestHeader("Version") String version,
			@RequestHeader("Content-Type") String contentType) {
		return this.advertService.postAllAdvertFromDBWithDaniProfile(authorization, version, contentType);
	}

	@PostMapping("/post-all-dani-new-set")
	public List<Advert> postAllAdvertsDBWithDaniProfileNewSet(@RequestHeader("Authorization") String authorization,
			@RequestHeader("Version") String version,
			@RequestHeader("Content-Type") String contentType) {
		return this.advertService.postAllAdvertFromDBWithDaniProfileNewSet(authorization, version, contentType);
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

    @GetMapping("/me")
    public String getMyInformation(@RequestHeader("Authorization") String authorization,
                                   @RequestHeader("Version") String version) {
        return this.advertService.getMyInformation(authorization, version);
    }
}