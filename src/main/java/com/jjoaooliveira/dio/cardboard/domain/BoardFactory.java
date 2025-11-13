package com.jjoaooliveira.dio.cardboard.domain;

import java.util.*;

public class BoardFactory {
    public static Board makeBoard(String boardName, ColumnData[] columnArray) {
        Map<Integer, Column> columnMap = new HashMap<>();

        for(ColumnData columnData : columnArray) {
            List<Card> cards = new ArrayList<>();
            Column column = new Column(
                    columnData.order(),
                    columnData.columnName(),
                    ColumnType.valueOf(columnData.type()),
                    cards);
            columnMap.put(column.getOrder(), column);
        }

        return new Board(boardName, columnMap);
    }
}
