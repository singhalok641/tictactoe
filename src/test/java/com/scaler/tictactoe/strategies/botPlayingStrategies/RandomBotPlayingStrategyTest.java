package com.scaler.tictactoe.strategies.botPlayingStrategies;

import com.scaler.tictactoe.models.Board;
import com.scaler.tictactoe.models.Cell;
import com.scaler.tictactoe.models.CellState;
import com.scaler.tictactoe.models.Move;
import com.scaler.tictactoe.strategies.botplayingstrategies.RandomBotPlayingStrategy;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RandomBotPlayingStrategyTest {

    @Test
    void testMakeMoveWhenEmptyCellExists() {
        // Create an instance of the RandomBotPlayingStrategy
        RandomBotPlayingStrategy strategy = new RandomBotPlayingStrategy();

        // Create an instance of the board with dimension 3 (3x3 board)
        Board board = new Board(3);

        // Call the makeMove method
        Move move = strategy.makeMove(board);

        // Check that a Move object is returned
        assertNotNull(move, "Move should not be null when there are empty cells on the board");

        // Check that the move involves placing a symbol in the empty cell
        assertEquals(CellState.EMPTY, move.getCell().getCellState(), "The move should be made in an empty cell");
    }

    @Test
    void testMakeMoveWhenBoardIsFull() {
        // Create an instance of the RandomBotPlayingStrategy
        RandomBotPlayingStrategy strategy = new RandomBotPlayingStrategy();

        // Create an instance of a completely occupied board
        Board board = new Board(3);

        // Get the 2D list of Cells from the Board
        List<List<Cell>> cells = board.getBoard();

        // Set the CellState of each Cell to FILLED
        for (List<Cell> row : cells) {
            for (Cell cell : row) {
                cell.setCellState(CellState.FILLED);
            }
        }

        // Call the makeMove method
        Move move = strategy.makeMove(board);

        // Check that the method returns null as there are no empty cells
        assertNull(move, "Move should be null when no empty cells are on the board");
    }
}


