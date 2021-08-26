package com.example.olxpostman.advert.feign;

import com.example.olxpostman.advert.model.Advert;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "olx", url = "https://www.olx.ro/api/partner")
public interface OlxFeignClient {

    @PostMapping(value = "/adverts", consumes = "application/json", produces = "application/json")
    Advert postAdvert(@RequestHeader("Authorization") String authorization,
                      @RequestHeader("Version") String version,
                      @RequestHeader("Content-Type") String contentType,
                      @RequestBody String advert);

    @PutMapping(value = "/adverts/220036912", consumes = "application/json", produces = "application/json")
    Advert updateAdvert(@RequestHeader("Authorization") String authorization,
                        @RequestHeader("Version") String version,
                        @RequestHeader("Content-Type") String contentType,
                        @RequestBody String advert);

    @GetMapping(value = "/users/me")
    String getMyInformation(@RequestHeader("Authorization") String authorization,
                            @RequestHeader("Version") String version);

	@DeleteMapping(value = "/adverts/{id}", consumes = "application/json", produces = "application/json")
	Advert deleteAdvert(@RequestHeader("Authorization") String authorization,
			@RequestHeader("Version") String version,
			@PathVariable Integer id);
}
