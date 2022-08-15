package com.hanghae.minipj.Controller;


import com.hanghae.minipj.Service.CardService;
import com.hanghae.minipj.dto.ResponseDto;
import com.hanghae.minipj.dto.request.CardRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class CardController {

    private final CardService cardService;

    @PostMapping("/api/cards/posts")
    public ResponseDto<?> createCard(@RequestBody CardRequestDto requestDto,
                                     HttpServletRequest request) {
        return cardService.createCard(requestDto, request);
    }

    @GetMapping("/api/cards/{postId}")
    public ResponseDto<?> getCard(@PathVariable Long cardId) {
        return cardService.getCard(cardId);
    }

    @GetMapping("/api/cards/posts")
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

    @GetMapping("/api/cards/{ages}")
    public ResponseDto<?> getCardsByAges(@PathVariable String ages){
        return cardService.getCardsByAges(ages);
    }
//MultipartHttpServletRequest




}

