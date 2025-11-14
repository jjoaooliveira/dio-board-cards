package com.jjoaooliveira.dio.cardboard.application;

import com.jjoaooliveira.dio.cardboard.domain.Board;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class ProgressCardTest {
    @Mock
    BoardRepositoryInterface mockRepository;

    @Mock
    Board mockBoard;

    @Test
    void test_whenProgressCard_thenInteractorShouldCallProgressCardMethodOneTime() {
        // Arrange
        ProgressCard interactor = new ProgressCard(mockRepository);
        Mockito.when(mockRepository.getBoardById(anyLong()))
                .thenReturn(mockBoard);

        // Act
        interactor.progress(1L, 1, 1);

        // Assert
        Mockito.verify(mockRepository, times(1))
                .updateBoard(any(Board.class));
    }
}
