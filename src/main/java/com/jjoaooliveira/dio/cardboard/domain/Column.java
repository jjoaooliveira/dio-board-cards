package com.jjoaooliveira.dio.cardboard.domain;

import java.util.List;
import java.util.Optional;

public class Column {
    private final int order;
    private String nome;
    private final ColumnType type;
    private final List<Card> cards;

    public Column(int order,
                  String nome,
                  ColumnType type,
                  List<Card> cards) {
        this.order = order;
        this.nome = nome;
        this.type = type;
        this.cards = cards;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getOrder() {
        return order;
    }

    public ColumnType getType() {
        return type;
    }

    public void addCard(Card card) {
        this.cards.add(card);
        card.changeColumn(this.order);
    }

    public Optional<Card> removeCard(int cardId) {
        for (Card card : cards) {
            if (card.getId() == cardId) {
                card.changeColumn(0);
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
