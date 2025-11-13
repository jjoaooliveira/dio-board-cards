package com.jjoaooliveira.dio.cardboard.domain;

import java.time.OffsetDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Board {
    private final AtomicInteger atomicIntegerGenerator;
    private int cardIdTrack = 0;
    private final int CANCEL_COLUMN_ORDER = 0;
    private final int INITIAL_COLUMN_ORDER = 1;
    private Long id;
    private String name;
    private final Map<Integer, Column> columns;

    public Board(String name,
                 Map<Integer, Column> columns) {
        this.atomicIntegerGenerator = new AtomicInteger();
        this.name = name;
        this.columns = columns;
    }

    public Board(Long id,
                 String name,
                 Map<Integer, Column> columns,
                 int cardIdTrack) {
        this.id = id;
        this.name = name;
        this.columns = columns;
        this.cardIdTrack = cardIdTrack;
        this.atomicIntegerGenerator = new AtomicInteger(cardIdTrack);
    }

    public long getId() {
        return id;
    }

    public int getCardIdTrack() {
        return cardIdTrack;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Card getTransitiveCard(int columnOrder, int cardId) {
        return searchCardInColumn(columnOrder, cardId);
    }

    public Collection<Column> getColumns() {
        return columns.values();
    }

    public void addCard(String title, String description) {
        Card card = makeCard(title, description);
        Column column = columns.get(INITIAL_COLUMN_ORDER);
        column.addCard(card);
    }

    public Card removeCard(int columnOrder, int cardId) {
        Column column = columns.get(columnOrder);
        Optional<Card> card = column.removeCard(cardId);
        if(card.isEmpty()) throw new CardNotFoundException("Card does not exist with id: ", cardId);
        return card.get();
    }

    public void progressCard(final int columnOrder, final int cardId) {
        Card card = searchCardInColumn(columnOrder, cardId);
        int nextColumnOrder = columnOrder + 1;
        if(!columns.containsKey(nextColumnOrder)) return;
        moveCard(card, columnOrder, nextColumnOrder);
    }

    public void blockCard(int columnOrder, int cardId, String blockReason) {
        Card card = searchCardInColumn(columnOrder, cardId);
        if(card.isBlocked()) return;
        card.block(blockReason);
    }

    public void unblockCard(int columnOrder, int cardId, String unblockReason) {
        Card card = searchCardInColumn(columnOrder, cardId);
        if(!card.isBlocked()) return;
        card.unblock(unblockReason);
    }

    public void cancelCard(final int columnOrder, final int cardId) {
        Card card = searchCardInColumn(columnOrder, cardId);
        if(card.isCanceled()) return;
        card.cancel(columnOrder);
        moveCard(card, columnOrder, CANCEL_COLUMN_ORDER);
    }

    public void recoverCard(int cardId) {
        Card cardToRecover = searchCardInColumn(CANCEL_COLUMN_ORDER, cardId);
        int columnToMoveCardOrder = cardToRecover.uncancel();
        moveCard(cardToRecover, CANCEL_COLUMN_ORDER, columnToMoveCardOrder);
    }

    private Card makeCard(String title, String description) {
        int cardId = atomicIntegerGenerator.incrementAndGet();
        cardIdTrack = atomicIntegerGenerator.get();
        OffsetDateTime createdAt = OffsetDateTime.now();
        return new Card(cardId, title, description, createdAt);
    }

    private Card searchCardInColumn(final int columnOrder, final int cardId) {
        Column column = columns.get(columnOrder);
        Optional<Card> card = column.getCard(cardId);
        if(card.isEmpty()) throw new CardNotFoundException("Card does not exist with id: %d", cardId);
        return card.get();
    }

    private void moveCard(Card card, int currentColumnOrder, int destinationColumnOrder) {
        Column currentColumn = columns.get(currentColumnOrder);
        Column destinationColumn = columns.get(destinationColumnOrder);
        currentColumn.removeCard(card.getId());
        destinationColumn.addCard(card);
    }
}
