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
public class BlockCardTest {
    @Mock
    BoardRepositoryInterface mockRepository;

    @Mock
    Board mockBoard;

    @Test
    void test_whenBlockCard_thenInteractorShouldCallBlockCardOneTime() {
        // Arrange
        long boardId = 1L;
        int columnOrder = 1, cardId = 1;
        String blockReason = "Block Test";
        BlockCard blockCardInteractor = new BlockCard(mockRepository);
        Mockito.when(mockRepository.getBoardById(1L))
                .thenReturn(mockBoard);

        // Act
        blockCardInteractor.block(boardId, columnOrder, cardId, blockReason);

        // Assert
        Mockito.verify(mockBoard, times(1))
                .blockCard(columnOrder, cardId, blockReason);
    }
}
