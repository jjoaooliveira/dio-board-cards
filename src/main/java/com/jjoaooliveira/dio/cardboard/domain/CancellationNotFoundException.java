package com.jjoaooliveira.dio.cardboard.domain;

public class CancellationNotFoundException extends RuntimeException {
    public CancellationNotFoundException(String message, int cardId) {
        super(String.format(message, cardId));
    }
}
