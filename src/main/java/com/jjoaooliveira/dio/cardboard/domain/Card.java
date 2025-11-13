package com.jjoaooliveira.dio.cardboard.domain;

import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Card {
    private final Integer id;
    private String title;
    private String description;
    private final OffsetDateTime createdAt;
    private final Set<Block> blockRegistry;
    private Cancellation cancellation;

    public Card(Integer id, String title, String description, OffsetDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.blockRegistry = new LinkedHashSet<>();
        this.cancellation = null;
    }

    public Card(Integer id,
                String title,
                String description,
                OffsetDateTime createdAt,
                Set<Block> blockRegistry,
                Cancellation cancellation) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.blockRegistry = blockRegistry;
        this.cancellation = cancellation;
    }

    public Integer getId() {
        return id;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public Boolean isBlocked() {
        if (blockRegistry.isEmpty()) return false;
        Block block = blockRegistry.stream()
                .sorted(Comparator.comparing(Block::issueOn))
                .collect(Collectors.toCollection(LinkedHashSet::new))
                .getLast();
        return block.status().equals(BlockStatus.ACTIVE);
    }

    public void block(String reason) {
        OffsetDateTime blockedAt = OffsetDateTime.now();
        Block block = new Block(blockedAt, reason, BlockStatus.ACTIVE);
        blockRegistry.add(block);
    }

    public void unblock(String reason) {
        OffsetDateTime unblockTime = OffsetDateTime.now();
        Block unblock = new Block(unblockTime, reason, BlockStatus.FINISHED);
        blockRegistry.add(unblock);
    }

    public Set<Block> getBlockRegistry() {
        return blockRegistry;
    }

    public void cancel(int originColumnOrder) {
        if (!Objects.isNull(cancellation)) return;
        this.cancellation = new Cancellation(originColumnOrder);
    }

    public int uncancel() {
        if (Objects.isNull(cancellation)) throw new RuntimeException();
        int originColumnOrder = cancellation.originColumn();
        cancellation = null;
        return originColumnOrder;
    }

    public Cancellation getCancellation() {
        return cancellation;
    }

    public boolean isCanceled() {
        return !Objects.isNull(cancellation);
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
        return this.id.equals(card.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description);
    }
}
