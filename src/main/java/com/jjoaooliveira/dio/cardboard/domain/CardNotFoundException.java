package com.jjoaooliveira.dio.cardboard.domain;

public class CardNotFoundException extends RuntimeException {

    public CardNotFoundException(String message, int cardId) {
        super(String.format(message, cardId));
    }
}
