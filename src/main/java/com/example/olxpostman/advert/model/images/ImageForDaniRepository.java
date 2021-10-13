package com.example.olxpostman.advert.model.images;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageForDaniRepository extends JpaRepository<ImageForDani, Long> {
}
