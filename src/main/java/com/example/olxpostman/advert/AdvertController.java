package com.example.olxpostman.advert;

import com.example.olxpostman.advert.model.Advert;
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

    /*@PostMapping("/single-post/{id}")
    public Advert postSpecificAdvertDB(@RequestHeader("Authorization") String authorization,
                                       @RequestHeader("Version") String version,
                                       @RequestHeader("Content-Type") String contentType,
                                       @PathVariable Long id) {
        return this.advertService.postAdvertFromDB(authorization, version, contentType, id);
    }*/

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

	@PostMapping("/post-all-DMT-new-set")
	public List<Advert> postAllAdvertsDBWithDMTProfileNewSet(@RequestHeader("Authorization") String authorization,
			@RequestHeader("Version") String version,
			@RequestHeader("Content-Type") String contentType) {
		return this.advertService.postAllAdvertFromDBWithDMTProfileNewSet(authorization, version, contentType);
	}


	/*@DeleteMapping("/delete-all")
	public List<Integer> deleteAllAdverts(@RequestHeader("Authorization") String authorization,
			@RequestHeader("Version") String version) {
		return this.advertService.deleteAllAdvertFromDB(authorization, version);
	}*/

   /* @PutMapping("/update-db")
    public Advert updateAdvert(@RequestHeader("Authorization") String authorization,
                               @RequestHeader("Version") String version,
                               @RequestHeader("Content-Type") String contentType) throws JsonProcessingException {
        return this.advertService.updateAdvert(authorization, version, contentType);
    }*/
}