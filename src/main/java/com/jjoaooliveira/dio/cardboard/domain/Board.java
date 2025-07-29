package com.jjoaooliveira.dio.cardboard.domain;

import java.util.List;

public class Board {
    private Long id;
    private String nome;
    private List<Column> columns;
    
    public Board(Long id, String nome, List<Column> columns) {
        this.id = id;
        this.nome = nome;
        this.columns = columns;
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

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    
}
