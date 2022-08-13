package com.hanghae.minipj.controller;


import javax.servlet.http.HttpServletRequest;

import com.hanghae.minipj.dto.CardRequestDto;
import com.hanghae.minipj.response.ResponseDto;
import com.hanghae.minipj.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CardController {

    private final CardService cardService;

    @RequestMapping(value = "/api/auth/posts", method = RequestMethod.POST)
    public ResponseDto<?> createCard(@RequestBody CardRequestDto requestDto,
                                     HttpServletRequest request) {
        return cardService.createCard(requestDto, request);
    }

    @RequestMapping(value = "/api/cards/{postId}", method = RequestMethod.GET)
    public ResponseDto<?> getCard(@PathVariable Long cardId) {
        return cardService.getCard(cardId);
    }

    @RequestMapping(value = "/api/posts", method = RequestMethod.GET)
    public ResponseDto<?> getAllCards() {
        return cardService.getAllPost();
    }

    @RequestMapping(value = "/api/auth/cards/{cardId}", method = RequestMethod.PUT)
    public ResponseDto<?> updatePost(@PathVariable Long cardId, @RequestBody CardRequestDto cardRequestDto,
                                     HttpServletRequest request) {
        return cardService.updatePost(cardId, cardRequestDto, request);
    }

    @RequestMapping(value = "/api/auth/cards/{cardId}", method = RequestMethod.DELETE)
    public ResponseDto<?> deleteCard(@PathVariable Long cardId,
                                     HttpServletRequest request) {
        return cardService.deleteCard(cardId, request);
    }
//MultipartHttpServletRequest
}

