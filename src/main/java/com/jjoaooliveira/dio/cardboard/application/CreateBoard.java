package com.jjoaooliveira.dio.cardboard.application;

import com.jjoaooliveira.dio.cardboard.domain.Board;
import com.jjoaooliveira.dio.cardboard.domain.BoardFactory;
import com.jjoaooliveira.dio.cardboard.domain.ColumnData;

public class CreateBoard {
    private final BoardRepositoryInterface repository;

    public CreateBoard(BoardRepositoryInterface repository) {
        this.repository = repository;
    }

    public void create(String boardName, ColumnData... columns) {
        Board newBoard = BoardFactory.makeBoard(boardName, columns);
        repository.saveBoard(newBoard);
    }
}
