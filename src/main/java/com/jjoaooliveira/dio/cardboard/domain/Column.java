package com.jjoaooliveira.dio.cardboard.domain;

public class Column {
    private String nome;
    private int order;
    private ColumnType type;

    public Column(String nome, int order, ColumnType type) {
        this.nome = nome;
        this.order = order;
        this.type = type;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public ColumnType getType() {
        return type;
    }

    public void setType(ColumnType type) {
        this.type = type;
    }
}
