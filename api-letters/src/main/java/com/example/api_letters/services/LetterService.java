package com.example.api_letters.services;

import com.example.api_letters.commons.entities.LetterModel;

import java.util.ArrayList;

public interface LetterService {
    LetterModel getLetterById(Long id);

    ArrayList<LetterModel> getLetters();

    LetterModel createLetter(LetterModel letterRequest);

    LetterModel updateLetter(String id, LetterModel letterRequest);

    String deleteLetter(String id);
}
