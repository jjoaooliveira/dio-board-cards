package com.jjoaooliveira.dio.cardboard.domain;

import java.util.List;
import java.util.Optional;

public class Column {
    private final Integer order;
    private String name;
    private final ColumnType type;
    private final List<Card> cards;

    public Column(Integer order,
                  String name,
                  ColumnType type,
                  List<Card> cards) {
        this.order = order;
        this.name = name;
        this.type = type;
        this.cards = cards;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public ColumnType getType() {
        return type;
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public Optional<Card> removeCard(int cardId) {
        for (Card card : cards) {
            if (card.getId() == cardId) {
                cards.remove(card);
                return Optional.of(card);
            }
        }
        return Optional.empty();
    }

    public Optional<Card> getCard(int cardId) {
        for (Card card : cards) {
            if (card.getId() == cardId) return Optional.of(card);
        }
        return Optional.empty();
    }

    public List<Card> getCards() {
        return cards;
    }
}
