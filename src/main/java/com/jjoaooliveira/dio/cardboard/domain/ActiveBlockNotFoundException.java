package com.jjoaooliveira.dio.cardboard.domain;

public class ActiveBlockNotFoundException extends RuntimeException {
    public ActiveBlockNotFoundException(String message, int cardId) {
        super(String.format(message, cardId));
    }
}
