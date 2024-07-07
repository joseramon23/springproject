package com.example.api_letters.services.impl;

import com.example.api_letters.commons.constants.TopicsConstants;
import com.example.api_letters.commons.entities.LetterModel;
import com.example.api_letters.commons.exceptions.LetterException;
import com.example.api_letters.repositories.LetterRepository;
import com.example.api_letters.services.LetterService;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class LetterServiceImpl implements LetterService {
    public final LetterRepository letterRepository;
    public final StreamBridge streamBridge;

    public LetterServiceImpl(LetterRepository letterRepository, StreamBridge streamBridge) {
        this.letterRepository = letterRepository;
        this.streamBridge = streamBridge;
    }

    @Override
    public LetterModel getLetterById(Long id) {
        return this.letterRepository.findById(id)
                .orElseThrow(() -> new LetterException(HttpStatus.NOT_FOUND, "Error finding letter"));
    }

    @Override
    public ArrayList<LetterModel> getLetters() {
        return (ArrayList<LetterModel>) this.letterRepository.findAll();
    }

    @Override
    public LetterModel createLetter(LetterModel letterRequest) {
        return Optional.of(letterRequest)
                .map(this::mapToLetter)
                .map(letterRepository::save)
                .map(this::sendLetterEvent)
                .orElseThrow(() -> new LetterException(HttpStatus.BAD_REQUEST, "Error creating letter"));
    }

    private LetterModel sendLetterEvent(LetterModel letterModel) {
        Optional.of(letterModel)
                .map(givenLetter -> this.streamBridge.send(TopicsConstants.LETTER_CREATED_TOPIC, letterModel))
                .map(bool -> letterModel);

        return letterModel;
    }

    private LetterModel mapToLetter(LetterModel letterRequest) {
        return LetterModel.builder()
                .sender(letterRequest.getSender())
                .receiver(letterRequest.getReceiver())
                .content(letterRequest.getContent())
                .destination(letterRequest.getDestination())
                .date(letterRequest.getDate())
                .build();
    }

    @Override
    public LetterModel updateLetter(String id, LetterModel letterRequest) {
        LetterModel existsLetter = this.letterRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new LetterException(HttpStatus.NOT_FOUND, "Error finding letter"));

        existsLetter.setSender(letterRequest.getSender());
        existsLetter.setReceiver(letterRequest.getReceiver());
        existsLetter.setContent(letterRequest.getContent());
        existsLetter.setDestination(letterRequest.getDestination());
        existsLetter.setDate(letterRequest.getDate());

        return this.letterRepository.save(existsLetter);
    }

    @Override
    public String deleteLetter(String id) {
        LetterModel existsLetter = this.letterRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new LetterException(HttpStatus.NOT_FOUND, "Error finding letter"));

        this.letterRepository.deleteById(Long.valueOf(id));
        return "Letter ID: " + id + " Has been deleted";
    }
}
