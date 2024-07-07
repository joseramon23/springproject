package com.example.api_letters.controller;

import com.example.api_letters.commons.constants.ApiPathVariables;
import com.example.api_letters.commons.entities.LetterModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping(ApiPathVariables.V1_ROUTE + ApiPathVariables.LETTER_ROUTE)
public interface ApiLetter {
    @GetMapping
    ResponseEntity<ArrayList<LetterModel>> getLetters();

    @GetMapping("/{id}")
    ResponseEntity<LetterModel> getLetterById(@PathVariable Long id);

    @PostMapping
    ResponseEntity<LetterModel> saveLetter(@PathVariable LetterModel letterRequest, @RequestHeader("userIdRequest") String userId);

    @PutMapping("/{id}")
    ResponseEntity<LetterModel> updateLetter(@PathVariable Long id, LetterModel letterRequest);

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteLetter(@PathVariable Long id);
}
