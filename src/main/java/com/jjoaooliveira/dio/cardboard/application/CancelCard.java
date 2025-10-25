package com.jjoaooliveira.dio.cardboard.application;

import com.jjoaooliveira.dio.cardboard.domain.Board;

public class CancelCard {
    private final BoardRepositoryInterface repository;

    public CancelCard(BoardRepositoryInterface repository) {
        this.repository = repository;
    }

    public void cancel(Long boardId, int columnOrder, int cardId) {
        Board board = repository.getBoardById(boardId);
        board.cancelCard(columnOrder, cardId);
    }
}
