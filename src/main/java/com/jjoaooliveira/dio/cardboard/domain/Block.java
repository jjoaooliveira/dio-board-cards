package com.jjoaooliveira.dio.cardboard.domain;

import java.time.OffsetDateTime;

public class Block {
    private final int id;
    private final int cardId;
    private final OffsetDateTime blockedAt;
    private final String blockReason;
    private OffsetDateTime unblockedAt;
    private String unblockReason;
    private BlockStatus status;

    public Block(int id, int cardId, OffsetDateTime blockedAt, String blockReason) {
        this.id = id;
        this.cardId = cardId;
        this.blockedAt = blockedAt;
        this.blockReason = blockReason;
        this.status = BlockStatus.ACTIVE;
    }

    public Block(int id,
                 int cardId,
                 OffsetDateTime blockedAt,
                 String blockReason,
                 OffsetDateTime unblockedAt,
                 String unblockReason,
                 BlockStatus status) {
        this.id = id;
        this.cardId = cardId;
        this.blockedAt = blockedAt;
        this.blockReason = blockReason;
        this.unblockedAt = unblockedAt;
        this.unblockReason = unblockReason;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getCardId() {
        return cardId;
    }

    public OffsetDateTime getBlockedAt() {
        return blockedAt;
    }

    public String getBlockReason() {
        return blockReason;
    }

    public void setUnblockedAt(OffsetDateTime unblockedAt) {
        this.unblockedAt = unblockedAt;
    }

    public OffsetDateTime getUnblockedAt() {
        return unblockedAt;
    }

    public String getUnblockReason() {
        return unblockReason;
    }

    public void setUnblockReason(String unblockReason) {
        this.unblockReason = unblockReason;
    }

    public BlockStatus getStatus() {
        return status;
    }

    void finishBlock() {
        if(this.status.equals(BlockStatus.FINISHED)) return;
        this.status = BlockStatus.FINISHED;
    }
}
