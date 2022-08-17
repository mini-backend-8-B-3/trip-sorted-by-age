package com.hanghae.minipj.Controller;


import com.hanghae.minipj.Service.CardService;
import com.hanghae.minipj.dto.ResponseDto;
import com.hanghae.minipj.dto.request.CardRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@RequiredArgsConstructor
@RestController
public class CardController {

    private final CardService cardService;

    @PostMapping("/api/auth/cards")
    public ResponseDto<?> createCard(@RequestPart("data") CardRequestDto requestDto,
                                     HttpServletRequest request,@RequestPart("image") @Nullable MultipartFile multipartFile){
        try {
            return cardService.createCard(requestDto, multipartFile,request);
        } catch (IOException e) {
            throw new RuntimeException("어라? 뭐가 잘못됐지?");
        }
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

