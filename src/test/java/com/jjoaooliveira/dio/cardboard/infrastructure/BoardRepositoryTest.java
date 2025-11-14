package com.jjoaooliveira.dio.cardboard.infrastructure;

import com.jjoaooliveira.dio.cardboard.domain.ColumnType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class BoardRepositoryTest {
    @Autowired
    BoardRepository repository;

    BoardEntity boardEntity;

    @BeforeEach
    void setUp() {
        BoardEntity board = new BoardEntity();
        ColumnEntity columnEntity1 = new ColumnEntity();
        columnEntity1.setId(new ColumnId(1L, 1));
        columnEntity1.setName("Column Test");
        columnEntity1.setBoard(board);
        columnEntity1.setCards(Set.of());
        columnEntity1.setType(ColumnType.INITIAL);

        ColumnEntity columnEntity2 = new ColumnEntity();
        columnEntity2.setId(new ColumnId(1L, 2));
        columnEntity2.setName("Column Test");
        columnEntity2.setBoard(board);
        columnEntity2.setCards(Set.of());
        columnEntity2.setType(ColumnType.PENDING);

        ColumnEntity columnEntity3 = new ColumnEntity();
        columnEntity3.setId(new ColumnId(1L, 3));
        columnEntity3.setName("Column Test");
        columnEntity3.setBoard(board);
        columnEntity3.setCards(Set.of());
        columnEntity3.setType(ColumnType.FINAL);

        ColumnEntity columnEntity4 = new ColumnEntity();
        columnEntity4.setId(new ColumnId(1L, 0));
        columnEntity4.setName("Column Test");
        columnEntity4.setBoard(board);
        columnEntity4.setCards(Set.of());
        columnEntity4.setType(ColumnType.CANCEL);
        Set<ColumnEntity> columnSet = Set.of(
                columnEntity1,
                columnEntity2,
                columnEntity3,
                columnEntity4
        );
        board.setName("Board Test");
        board.setColumns(columnSet);
        board.setCardIdTrack(0);
        boardEntity = board;
    }

    @Test
    void test_whenSaveBoard_thenBoardShouldBePersisted() {
        // Arrange
        String expectBoardName = "Board Test";
        int expectBoardTotalColumn = 4;
        int expectCardIdTrack = 0;

        // Act
        var savedBoard = repository.save(boardEntity);

        // Assert
        assertNotNull("Saved board should not be null", savedBoard);
        assertEquals(
                "Saved board should have the correct name",
                expectBoardName,
                savedBoard.getName()
        );
        assertEquals(
                "Saved board should have 4 columns",
                expectBoardTotalColumn,
                savedBoard.getColumns().size()
        );
        assertEquals(
                "Saved board card id track should be 0",
                expectCardIdTrack,
                savedBoard.getCardIdTrack()
        );
    }

    @Test
    void test_whenFindCompleteBoard_thenBoardShouldBeReturned() {
        // Arrange
        repository.save(boardEntity);

        // Act
        var queriedBoard = repository.findCompleteBoard(1L);

        // Assert
        assertNotNull("Queried board should not be null", queriedBoard);
    }
}
