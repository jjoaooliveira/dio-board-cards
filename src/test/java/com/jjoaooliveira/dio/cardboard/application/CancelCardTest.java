package com.jjoaooliveira.dio.cardboard.application;

import com.jjoaooliveira.dio.cardboard.domain.Board;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
public class CancelCardTest {
    @Mock
    BoardRepositoryInterface mockRepository;

    @Mock
    Board mockBoard;

    @Test
    void test_whenCancelCard_thenInteractorShouldCallCancelCardMethod() {
        // Arrange
        CancelCard interactor = new CancelCard(mockRepository);
        Mockito.when(mockRepository.getBoardById(anyLong())).thenReturn(mockBoard);

        // Act
        interactor.cancel(1L, 1, 1);

        // Assert
        Mockito.verify(mockBoard, Mockito.times(1))
                .cancelCard(anyInt(), anyInt());
    }
}
