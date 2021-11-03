package com.example.olxpostman.advert.model.advert;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertRepository extends JpaRepository<Advert, Long> {

	List<Advert> findAllByAdvertType(String advertType);
}
