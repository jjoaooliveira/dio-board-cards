package com.jjoaooliveira.dio.cardboard.application;

import com.jjoaooliveira.dio.cardboard.domain.Board;

public class ProgressCard {
    private final BoardRepositoryInterface repository;

    public ProgressCard(BoardRepositoryInterface repository) {
        this.repository = repository;
    }

    public void progress(Long boardId, int columnOrder, int cardId) {
        Board board = repository.getBoardById(boardId);
        board.progressCard(columnOrder, cardId);
        repository.saveBoard(board);
    }
}
