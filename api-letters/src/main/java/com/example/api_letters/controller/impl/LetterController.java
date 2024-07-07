package com.example.api_letters.controller.impl;

import com.example.api_letters.commons.entities.LetterModel;
import com.example.api_letters.controller.ApiLetter;
import com.example.api_letters.services.LetterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class LetterController implements ApiLetter {
    private final LetterService letterService;

    public LetterController(LetterService letterService) {
        this.letterService = letterService;
    }

    @Override
    public ResponseEntity<ArrayList<LetterModel>> getLetters() {
        return ResponseEntity.ok(this.letterService.getLetters());
    }

    @Override
    public ResponseEntity<LetterModel> getLetterById(Long id) {
        return ResponseEntity.ok(this.letterService.getLetterById(id));
    }

    @Override
    public ResponseEntity<LetterModel> saveLetter(@RequestBody LetterModel letterRequest, @RequestHeader String userId) {
        letterRequest.setSender(Long.valueOf(userId));
        return ResponseEntity.ok(this.letterService.createLetter(letterRequest));
    }

    @Override
    public ResponseEntity<LetterModel> updateLetter(Long id, LetterModel letterRequest) {
        return ResponseEntity.ok(this.letterService.updateLetter(String.valueOf(id), letterRequest));
    }

    @Override
    public ResponseEntity<String> deleteLetter(Long id) {
        return ResponseEntity.ok(this.letterService.deleteLetter(String.valueOf(id)));
    }
}
