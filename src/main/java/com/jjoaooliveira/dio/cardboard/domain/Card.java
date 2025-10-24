package com.jjoaooliveira.dio.cardboard.domain;

import java.time.OffsetDateTime;
import java.util.Objects;

public class Card {
    private final int id;
    private int order;
    private String title;
    private String description;
    private final OffsetDateTime createdAt;
    private Boolean blocked;
    
    public Card(int id,
                int order,
                String title,
                String description,
                OffsetDateTime createdAt,
                Boolean blocked) {
        this.id = id;
        this.order = order;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.blocked = blocked;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void changeColumn(int newOrder) {
        this.order = newOrder;
    }

    public int getOrder() {
        return order;
    }

    public int getId() {
        return id;
    }

    public Boolean isBlocked() {
        return blocked;
    }

    public void block() {
        this.blocked = true;
    }

    public void unblock() {
        this.blocked = false;
    }

    public String getDescription() {
        return description;
    }

    public void changeDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void changeTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Card card)) return false;
        return this.id == card.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order);
    }
}
