package com.example.api_letters.repositories;

import com.example.api_letters.commons.entities.LetterModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LetterRepository extends JpaRepository<LetterModel, Long> {
}
