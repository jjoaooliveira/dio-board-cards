package com.jjoaooliveira.dio.cardboard.application;

import com.jjoaooliveira.dio.cardboard.domain.Board;
import com.jjoaooliveira.dio.cardboard.domain.ColumnData;
import com.jjoaooliveira.dio.cardboard.domain.ColumnType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class CreateBoardTest {
    @Mock
    BoardRepositoryInterface mockRepository;

    @Test
    void test_whenCreateBoard_thenInteractorShouldCallBoardFactory() {
        // Arrange
        ColumnData[] columnDataArray = {
                new ColumnData("Test", 1, "INITIAL"),
                new ColumnData("Test", 2, "PENDING"),
                new ColumnData("Test", 0, "CANCEL"),
                new ColumnData("Test", 3, "FINAL")
        };
        CreateBoard interactor = new CreateBoard(mockRepository);

        // Act
        interactor.create("Board test", columnDataArray);

        // Assert
        Mockito.verify(mockRepository, Mockito.times(1))
                .saveBoard(any(Board.class));
    }
}
