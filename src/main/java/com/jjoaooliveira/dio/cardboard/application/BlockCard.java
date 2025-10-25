package com.jjoaooliveira.dio.cardboard.application;

import com.jjoaooliveira.dio.cardboard.domain.Board;

public class BlockCard {
    private final BoardRepositoryInterface repository;

    public BlockCard(BoardRepositoryInterface repository) {
        this.repository = repository;
    }

    public void block(Long boardId, int columnOrder, int cardId, String blockReason) {
        Board board = repository.getBoardById(boardId);
        board.blockCard(columnOrder, cardId, blockReason);
        repository.saveBoard(board);
    }
}
