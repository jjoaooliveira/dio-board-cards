package com.jjoaooliveira.dio.cardboard.testapi;

import com.jjoaooliveira.dio.cardboard.domain.*;

public class BoardDomainTestAPI {
    private static final int FIRST_CARD_ID = 1;
    private static final int CANCEL_COLUMN_ORDER = 0;
    private static final int INITIAL_COLUMN_ORDER = 1;
    private static final int PENDING_COLUMN_ORDER = 2;
    private static final int COMPLETE_COLUMN_ORDER = 3;

    public static Board createBoardWithOneCard() {
        String cardTitle = "Title";
        String cardDescription = "Description";
        ColumnData[] columnDataArray = {
                new ColumnData("Cancel Column", 0, "CANCEL"),
                new ColumnData("Initial Column", 1, "INITIAL"),
                new ColumnData("Pending Column", 2, "PENDING"),
                new ColumnData("Complete Column", 3, "FINAL"),
        };
        Board board = BoardFactory.makeBoard("Board Test", columnDataArray);
        board.addCard(cardTitle, cardDescription);

        return board;
    }

    public static Card boardAddCardInInitialColumn() {
        String cardTitle = "Title";
        String cardDescription = "Description";
        ColumnData[] columnDataArray = {
                new ColumnData("Cancel Column", 0, "CANCEL"),
                new ColumnData("Initial Column", 1, "INITIAL"),
                new ColumnData("Pending Column", 2, "PENDING"),
                new ColumnData("Complete Column", 3, "FINAL"),
        };
        Board board = BoardFactory.makeBoard("Board Test", columnDataArray);
        board.addCard(cardTitle, cardDescription);
        return board.getTransitiveCard(INITIAL_COLUMN_ORDER, FIRST_CARD_ID);
    }

    public static Boolean boardBlocksCardAndCardIsBlocked(String blockReason) {
        Board board = createBoardWithOneCard();
        board.blockCard(INITIAL_COLUMN_ORDER, FIRST_CARD_ID, blockReason);
        Card blockedCard = board.getTransitiveCard(INITIAL_COLUMN_ORDER,FIRST_CARD_ID);

        return blockedCard.isBlocked();
    }

    public static boolean boardBlockCardAndCardIsBlocked() {
        String blockReason = "Block Reason Test";
        Board board = createBoardWithOneCard();
        board.blockCard(INITIAL_COLUMN_ORDER, FIRST_CARD_ID, blockReason);
        Card blockedCard = board.getTransitiveCard(INITIAL_COLUMN_ORDER, FIRST_CARD_ID);
        return blockedCard.isBlocked();
    }

    public static Card boardProgressCardAndCardIsMovedToNextColumn() {
        Board board = createBoardWithOneCard();
        board.progressCard(INITIAL_COLUMN_ORDER, FIRST_CARD_ID);
        return board.getTransitiveCard(PENDING_COLUMN_ORDER, FIRST_CARD_ID);
    }

    public static Boolean boardUnblockCardAndCardIsUnblocked() {
        String blockReason = "Block Reason Test";
        String unblockReason = "Unblock Reason Test";
        Board board = createBoardWithOneCard();
        board.blockCard(INITIAL_COLUMN_ORDER, FIRST_CARD_ID, blockReason);
        board.unblockCard(INITIAL_COLUMN_ORDER, FIRST_CARD_ID, unblockReason);
        Card unblockedCard = board.getTransitiveCard(INITIAL_COLUMN_ORDER, FIRST_CARD_ID);
        return unblockedCard.isBlocked();
    }

    public static Card boardCancelCardAndCardIsMovedToCancelColumn() {
        Board board = BoardDomainTestAPI.createBoardWithOneCard();
        board.cancelCard(INITIAL_COLUMN_ORDER, FIRST_CARD_ID);
        return board.getTransitiveCard(CANCEL_COLUMN_ORDER, FIRST_CARD_ID);
    }

    public static boolean boardCancelCardAndCardIsCanceled() {
        Board board = BoardDomainTestAPI.createBoardWithOneCard();
        board.cancelCard(INITIAL_COLUMN_ORDER, FIRST_CARD_ID);
        Card canceledCard = board.getTransitiveCard(CANCEL_COLUMN_ORDER, FIRST_CARD_ID);

        return canceledCard.isCanceled();
    }

    public static void boardAccessUnexistentCardAndThrowsException() {
        Board board = BoardDomainTestAPI.createBoardWithOneCard();
        board.removeCard(INITIAL_COLUMN_ORDER, FIRST_CARD_ID);
        board.getTransitiveCard(INITIAL_COLUMN_ORDER, FIRST_CARD_ID);
    }

    public static Card boardRemovesCardAndReturnRemovedCard() {
        Board board = BoardDomainTestAPI.createBoardWithOneCard();
        return board.removeCard(INITIAL_COLUMN_ORDER, FIRST_CARD_ID);
    }

    public static Card boardRecoverCancelCardAndCardIsMovedToPreviousColumn() {
        Board board = createBoardWithOneCard();
        board.cancelCard(INITIAL_COLUMN_ORDER, FIRST_CARD_ID);
        board.recoverCard(FIRST_CARD_ID);
        return board.getTransitiveCard(INITIAL_COLUMN_ORDER, FIRST_CARD_ID);
    }

    public static boolean boardRecoverCanceledCardAndCardIsNotCanceled() {
        Board board = createBoardWithOneCard();
        board.cancelCard(INITIAL_COLUMN_ORDER, FIRST_CARD_ID);
        board.recoverCard(FIRST_CARD_ID);
        return board.getTransitiveCard(INITIAL_COLUMN_ORDER, FIRST_CARD_ID).isCanceled();
    }
}
