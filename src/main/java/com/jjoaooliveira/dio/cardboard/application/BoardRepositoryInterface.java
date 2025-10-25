package com.jjoaooliveira.dio.cardboard.application;

import com.jjoaooliveira.dio.cardboard.domain.Board;

import java.util.List;

public interface BoardRepositoryInterface {
    Board getBoardById(Long boardId);
    List<Board> getAllBoards();
    void saveBoard(Board board);
}
