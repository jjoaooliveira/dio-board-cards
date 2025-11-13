package com.jjoaooliveira.dio.cardboard.domain;

import com.jjoaooliveira.dio.cardboard.testapi.BoardDomainTestAPI;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.*;

public class BoardTest {

    @Test
    void test_boardShouldAddCardInInitialColumn() {
        // Arrange

        // Act
        var addedCard = BoardDomainTestAPI.boardAddCardInInitialColumn();

        // Assert
        assertNotNull(
                "Initial Column should have one card",
                addedCard
                );
    }

    @Test
    void test_whenProgressCardFromColumnInitialColumn_thenCardShouldMoveToNextColumn() {
        // Arrange
        int expectedCardColumnOrder = 2;

        // Act
        var progressCard = BoardDomainTestAPI.boardProgressCardAndCardIsMovedToNextColumn();

        // Assert
        assertNotNull(
                "Column with order two should have one card after progress",
                progressCard
        );
    }

    @Test
    void test_whenBlockCard_thenCardShouldBeBlock() {
        // Arrange
        String blockReason = "Block test";

        // Act
        var isCardBlocked = BoardDomainTestAPI.boardBlocksCardAndCardIsBlocked(blockReason);

        // Assert
        assertTrue("Card must be block", isCardBlocked);
    }

    @Test
    void test_whenBlockCard_thenCardShouldBeBlocked() {
        // Arrange

        // Act
        var cardIsBlocked = BoardDomainTestAPI.boardBlockCardAndCardIsBlocked();

        // Assert
        assertTrue("One block must be created after block card", cardIsBlocked);
    }

    @Test
    void test_whenUnblockCard_thenCardShouldBeUnblocked() {
        // Arrange
        // Act
        var isCardBlocked = BoardDomainTestAPI.boardUnblockCardAndCardIsUnblocked();

        // Assert
        assertFalse("Card must be unblocked", isCardBlocked);
    }

    @Test
    void test_whenCancelCard_thenCardShouldBeMovedToCancelColumn() {
        // Arrange

        // Act
        var canceledCard = BoardDomainTestAPI.boardCancelCardAndCardIsMovedToCancelColumn();

        // Assert
        assertNotNull(
                "Card should be moved to cancel column",
                canceledCard
        );
    }

    @Test
    void test_whenCancelCard_thenCardShouldBeCanceled() {
        // Arrange
        // Act
        var cardIsCanceled = BoardDomainTestAPI.boardCancelCardAndCardIsCanceled();

        // Assert
        assertTrue(
                "Card should be canceled",
                cardIsCanceled
        );
    }

    @Test
    void test_whenRemoveCard_thenRemovedCardShouldBeReturned() {
        // Arrange
        // Act
        var removeCard = BoardDomainTestAPI.boardRemovesCardAndReturnRemovedCard();

        // Assert
        assertNotNull("Removed card should not be null", removeCard);
    }

    @Test
    void test_whenAccessUnexistentCard_thenBoardShouldThrowCardNotFoundException() {
        // Arrange/Act/Assert
        assertThrows(CardNotFoundException.class,
                BoardDomainTestAPI::boardAccessUnexistentCardAndThrowsException,
                "CardNotFoundException must be threw"
        );
    }

    @Test
    void test_whenRecoverCard_thenCardShouldBeMovedFromCancelColumn() {
        // Arrange

        // Act
        var recoveredCard = BoardDomainTestAPI.boardRecoverCancelCardAndCardIsMovedToPreviousColumn();

        // Assert
        assertNotNull(
                "Card should be recovered to its original column",
                recoveredCard
        );
    }

    @Test
    void test_whenRecoverCard_thenCardShouldNotBeCanceled() {
        // Arrange

        // Act
        var cardIsCanceled = BoardDomainTestAPI.boardRecoverCanceledCardAndCardIsNotCanceled();

        // Assert
        assertFalse(
                "Card object should not be canceled",
                cardIsCanceled
        );
    }
}
