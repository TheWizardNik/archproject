package com.arhproject.start.jpa.repository;

import com.arhproject.start.jpa.domain.TextOrImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TextOrImageRepository extends JpaRepository<TextOrImage, Long> {
}
