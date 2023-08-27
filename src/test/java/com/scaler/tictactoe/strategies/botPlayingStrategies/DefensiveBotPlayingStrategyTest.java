package com.scaler.tictactoe.strategies.botPlayingStrategies;

import com.scaler.tictactoe.models.*;
import com.scaler.tictactoe.strategies.botplayingstrategies.DefensiveBotPlayingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.lang.reflect.Field;
import static org.junit.jupiter.api.Assertions.*;

class DefensiveBotPlayingStrategyTest {

    private Board board;
    private Player botPlayer;
    private Player humanPlayer;
    private DefensiveBotPlayingStrategy defensiveBotStrategy;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        botPlayer = new Bot(2L, "GPT", new Symbol('O'), BotDifficultyLevel.HARD);
        humanPlayer = new Player(1L, "Alok", new Symbol('X'), PlayerType.HUMAN);
        board = new Board(3);
        defensiveBotStrategy = new DefensiveBotPlayingStrategy(botPlayer);

        // Access the board field using reflection
        Field boardField = Board.class.getDeclaredField("board");
        boardField.setAccessible(true);
        List<List<Cell>> cellBoard = (List<List<Cell>>) boardField.get(board);

        // Set initial states for cells here if required, for example:
        // cellBoard.get(0).get(0).setPlayer(humanPlayer);
    }

    @Test
    void testBotBlocksOpponentWinningMove() throws NoSuchFieldException, IllegalAccessException {
        // Access the board field using reflection
        Field boardField = Board.class.getDeclaredField("board");
        boardField.setAccessible(true);
        List<List<Cell>> cellBoard = (List<List<Cell>>) boardField.get(board);

        // Setting up a scenario where the opponent is about to win
        cellBoard.get(0).get(0).setPlayer(humanPlayer);
        cellBoard.get(0).get(0).setCellState(CellState.FILLED);
        cellBoard.get(0).get(1).setPlayer(humanPlayer);
        cellBoard.get(0).get(1).setCellState(CellState.FILLED);

        // The bot should block the opponent by placing its symbol in (0, 2)
        Move expectedMove = new Move(cellBoard.get(0).get(2), null);

        Move actualMove = defensiveBotStrategy.makeMove(board);

        assertEquals(expectedMove.getCell(), actualMove.getCell());
    }

    @Test
    void testBotMakesRandomMoveWhenNoImmediateThreat() throws NoSuchFieldException, IllegalAccessException {
        // Access the board field using reflection
        Field boardField = Board.class.getDeclaredField("board");
        boardField.setAccessible(true);
        List<List<Cell>> cellBoard = (List<List<Cell>>) boardField.get(board);

        // Setting up a scenario where no player is about to win
        cellBoard.get(0).get(0).setPlayer(humanPlayer);
        cellBoard.get(0).get(0).setCellState(CellState.FILLED);
        cellBoard.get(1).get(0).setPlayer(humanPlayer);
        cellBoard.get(1).get(0).setCellState(CellState.FILLED);

        // The bot should make a random move as there are no immediate threats
        Move actualMove = defensiveBotStrategy.makeMove(board);
        assertNotNull(actualMove);
        assertNotNull(actualMove.getCell());
        assertEquals(CellState.EMPTY, actualMove.getCell().getCellState());
    }

    @Test
    void testBotMakesMoveOnEmptyBoard() {
        // The board is completely empty
        Move actualMove = defensiveBotStrategy.makeMove(board);
        assertNotNull(actualMove);
        assertNotNull(actualMove.getCell());
        assertEquals(CellState.EMPTY, actualMove.getCell().getCellState());
    }

    @Test
    void testBotReturnsNullOnFullBoard() throws NoSuchFieldException, IllegalAccessException {
        // Access the board field using reflection
        Field boardField = Board.class.getDeclaredField("board");
        boardField.setAccessible(true);
        List<List<Cell>> cellBoard = (List<List<Cell>>) boardField.get(board);

        // Setting up a full board scenario
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cellBoard.get(i).get(j).setPlayer(humanPlayer);
                cellBoard.get(i).get(j).setCellState(CellState.FILLED);
            }
        }

        Move actualMove = defensiveBotStrategy.makeMove(board);
        assertNull(actualMove);
    }
}

