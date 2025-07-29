package com.jjoaooliveira.dio.cardboard.domain;

import java.time.OffsetDateTime;

public class Card {
    private String title;
    private String description;
    private OffsetDateTime createdAt;
    private Boolean blocked;
    
    public Card(String title, String description, OffsetDateTime createdAt, Boolean blocked) {
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.blocked = blocked;
    }

    
}
