package com.hanghae.minipj.controller;


import com.hanghae.minipj.service.CardService;
import com.hanghae.minipj.dto.ResponseDto;
import com.hanghae.minipj.dto.request.CardRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class CardController {

    private final CardService cardService;

    @PostMapping("/api/auth/cards")
    public ResponseDto<?> createCard(@RequestBody CardRequestDto requestDto,
                                     HttpServletRequest request) {
        return cardService.createCard(requestDto, request);
    }

    @GetMapping("/api/cards/{cardId}")
    public ResponseDto<?> getCard(@PathVariable Long cardId) {
        return cardService.getCard(cardId);
    }

    @GetMapping("/api/cards")
    public ResponseDto<?> getAllCards() {
        return cardService.getAllCard();
    }

    @PutMapping("/api/auth/cards/{cardId}")
    public ResponseDto<?> updateCard(@PathVariable Long cardId, @RequestBody CardRequestDto cardRequestDto,
                                     HttpServletRequest request) {
        return cardService.updateCard(cardId, cardRequestDto, request);
    }

    @DeleteMapping("/api/auth/cards/{cardId}")
    public ResponseDto<?> deleteCard(@PathVariable Long cardId,
                                     HttpServletRequest request) {
        return cardService.deleteCard(cardId, request);
    }

    @GetMapping("/api/cards/menu/{ages}")
    public ResponseDto<?> getCardsByAges(@PathVariable String ages){
        return cardService.getCardsByAges(ages);
    }
//MultipartHttpServletRequest




}

