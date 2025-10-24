package com.jjoaooliveira.dio.cardboard.domain;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Board {
    private final int CANCEL_COLUMN_ORDER = 0;
    private final int INITIAL_COLUMN_ORDER = 1;
    private Long id;
    private String nome;
    private final Map<Integer, Column> columns;
    private final List<Block> blocks;
    private final List<Cancel> cancels;

    public Board(Long id,
                 String nome,
                 Map<Integer, Column> columns,
                 List<Block> blocks,
                 List<Cancel> cancels) {
        this.id = id;
        this.nome = nome;
        this.columns = columns;
        this.blocks = blocks;
        this.cancels = cancels;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public List<Cancel> getCancels() {
        return cancels;
    }

    public Card getTransitiveCard(int columnOrder, int cardId) {
        return searchCardInColumn(columnOrder, cardId);
    }

    public Collection<Column> getColumns(int order) {
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

    public void progressCard(int columnOrder, int cardId) {
        Card card = searchCardInColumn(columnOrder, cardId);
        int actualCardColumnOrder = card.getOrder();
        int nextColumnOrder = actualCardColumnOrder + 1;
        if(!columns.containsKey(nextColumnOrder)) return;
        moveCard(card, nextColumnOrder);
    }

    public void blockCard(int columnOrder, int cardId, String blockReason) {
        // get card and column
        Card card = searchCardInColumn(columnOrder, cardId);

        // verify if card is already blocked
        if(card.isBlocked()) return;

        // block card and add new block object
        card.block();
        OffsetDateTime blockedAt = OffsetDateTime.now();
        blocks.add(new Block(
                0,
                cardId,
                blockedAt,
                blockReason));
    }

    public void unblockCard(int columnOrder, int cardId, String unblockReason) {
        // get column and card
        Card card = searchCardInColumn(columnOrder, cardId);

        // verify if card is not block
        if(!card.isBlocked()) return;

        // unblock card
        card.unblock();
        OffsetDateTime unblockTime = OffsetDateTime.now();
        Block block = getActiveBlockByCardId(cardId);
        block.setUnblockReason(unblockReason);
        block.setUnblockedAt(unblockTime);
    }

    public void cancelCard(int columnOrder, int cardId) {
        // check if card is already canceled
        boolean cardIsAlreadyCanceled = cancels.stream()
                .anyMatch(cancel -> cancel.cardId() == cardId);
        if(cardIsAlreadyCanceled) return;

        // find card
        Card card = searchCardInColumn(columnOrder, cardId);

        // move card to cancel column
        Cancel cancel = new Cancel(cardId, 0, card.getOrder());
        cancels.add(cancel);
        moveCard(card, CANCEL_COLUMN_ORDER);
    }

    public Cancel recoverCard(int cardId) {
        // get cancel object
        Cancel cancelToRecover = cancels.stream()
                .filter(cancel -> cancel.cardId() == cardId)
                .findFirst()
                .orElseThrow(() -> new CancellationNotFoundException(
                        "Cancellation object does not exist with id: %d",
                        cardId));

        // move card to original column
        Card cardToRecover = searchCardInColumn(CANCEL_COLUMN_ORDER, cardId);
        moveCard(cardToRecover, cancelToRecover.cancelFrom());

        // remove cancel from list
        cancels.remove(cancelToRecover);
        return cancelToRecover;
    }

    private Card makeCard(String title, String description) {
        OffsetDateTime createdAt = OffsetDateTime.now();
        return new Card(0, INITIAL_COLUMN_ORDER, title, description, createdAt, false);
    }

    private Card searchCardInColumn(int columnOrder, int cardId) {
        Column column = columns.get(columnOrder);
        Optional<Card> card = column.getCard(cardId);
        if(card.isEmpty()) throw new CardNotFoundException("Card does not exist with id: %d", cardId);
        return card.get();
    }

    private Block getActiveBlockByCardId(int cardId) {
        return blocks.stream()
                .filter(block -> block.getCardId() == cardId)
                .filter(block -> block.getStatus().equals(BlockStatus.ACTIVE))
                .findFirst()
                .orElseThrow(() -> new ActiveBlockNotFoundException("Active block not found for card id: %d", cardId));
    }

    private void moveCard(Card card, int destinationColumnOrder) {
        Column currentColumn = columns.get(card.getOrder());
        Column destinationColumn = columns.get(destinationColumnOrder);
        currentColumn.removeCard(card.getId());
        destinationColumn.addCard(card);
    }
}
