package com.jjoaooliveira.dio.cardboard.application;

import com.jjoaooliveira.dio.cardboard.domain.Board;

import java.util.List;

public class GetAllBoards {
    private BoardRepositoryInterface repository;

    public GetAllBoards(BoardRepositoryInterface repository) {
        this.repository = repository;
    }

    public List<Board> getAll() {
        return repository.getAllBoards();
    }
}
