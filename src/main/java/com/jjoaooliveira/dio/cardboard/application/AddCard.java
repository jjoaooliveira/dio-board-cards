package com.jjoaooliveira.dio.cardboard.application;

import com.jjoaooliveira.dio.cardboard.domain.Board;

public class AddCard {
    private final BoardRepositoryInterface repository;

    public AddCard(BoardRepositoryInterface repository) {
        this.repository = repository;
    }

    public void add(Long boardId, String cardTitle, String cardDescription) {
        Board board = repository.getBoardById(boardId);
        board.addCard(cardTitle, cardDescription);
        repository.saveBoard(board);
    }
}
