package com.jjoaooliveira.dio.cardboard.application;

import com.jjoaooliveira.dio.cardboard.domain.Board;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class AddCardTest {
    @Mock
    BoardRepositoryInterface mockRepository;

    @Mock
    Board mockBoard;

    @Test
    void test_whenAddCard_thenInteractorShouldCallBoardAddCardOneTime() {
        // Arrange
        long boardId = 1L;
        String cardTitle = "Card Title", cardDescription = "Card Description";
        AddCard interactor = new AddCard(mockRepository);
        Mockito.when(mockRepository.getBoardById(anyLong())).thenReturn(mockBoard);

        // Act
        interactor.add(boardId, cardTitle, cardDescription);

        // Assert
        Mockito.verify(mockBoard, Mockito.times(1))
                .addCard(anyString(), anyString());
    }
}
