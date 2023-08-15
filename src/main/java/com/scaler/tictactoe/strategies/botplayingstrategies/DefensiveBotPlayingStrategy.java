package com.scaler.tictactoe.strategies.botplayingstrategies;

/*
* To implement the DefensiveBotPlayingStrategy, the bot needs to check if the opponent is about to win in the next move.
* If so, the bot should block that spot.
* If there are no immediate threats, the bot can make a random move as in the RandomBotPlayingStrategy.
* For simplicity, let's assume we have two players X and O, and the bot is playing with O.
*
*
*
* */

import com.scaler.tictactoe.models.Board;
import com.scaler.tictactoe.models.Cell;
import com.scaler.tictactoe.models.CellState;
import com.scaler.tictactoe.models.Move;
import com.scaler.tictactoe.models.Player;

import java.util.ArrayList;
import java.util.List;

public class DefensiveBotPlayingStrategy implements BotPlayingStrategy {

    private RandomBotPlayingStrategy randomBotPlayingStrategy = new RandomBotPlayingStrategy();
    private Player botPlayer;

    public DefensiveBotPlayingStrategy(Player botPlayer) {
        this.botPlayer = botPlayer;
    }

    @Override
    public Move makeMove(Board board) {
        // check if the human player is about to win, if so, block it
        Move move = findBlockingMove(board);
        if (move != null) {
            return move;
        }

        // no immediate threat, make a random move
        return randomBotPlayingStrategy.makeMove(board);
    }

    private Move findBlockingMove(Board board) {
        // Check rows
        for (List<Cell> row : board.getBoard()) {
            if (isBlockingMoveNeeded(row, board.getSize())) {
                return new Move(findCell(row, CellState.EMPTY), null);
            }
        }

        // Check columns
        for (int j = 0; j < board.getSize(); j++) {
            List<Cell> column = new ArrayList<>();
            for (List<Cell> row : board.getBoard()) {
                column.add(row.get(j));
            }
            if (isBlockingMoveNeeded(column, board.getSize())) {
                return new Move(findCell(column, CellState.EMPTY), null);
            }
        }

        // Check diagonals
        List<Cell> diagonal1 = new ArrayList<>();
        List<Cell> diagonal2 = new ArrayList<>();
        for (int i = 0; i < board.getSize(); i++) {
            diagonal1.add(board.getBoard().get(i).get(i));
            diagonal2.add(board.getBoard().get(i).get(board.getSize() - 1 - i));
        }

        if (isBlockingMoveNeeded(diagonal1, board.getSize())) {
            return new Move(findCell(diagonal1, CellState.EMPTY), null);
        }

        if (isBlockingMoveNeeded(diagonal2, board.getSize())) {
            return new Move(findCell(diagonal2, CellState.EMPTY), null);
        }

        return null;
    }

    private boolean isBlockingMoveNeeded(List<Cell> cells, int boardSize) {
        int opponentCount = 0;
        int emptyCount = 0;

        for (Cell cell : cells) {
            if (cell.getCellState() == CellState.EMPTY) {
                emptyCount++;
            } else if (cell.getPlayer().getSymbol() != botPlayer.getSymbol()) { // Check if the cell is not filled by this bot
                opponentCount++;
            }
        }

        return opponentCount == boardSize - 1 && emptyCount == 1;
    }

    private Cell findCell(List<Cell> cells, CellState cellState) {
        for (Cell cell : cells) {
            if (cell.getCellState().equals(cellState)) {
                return cell;
            }
        }
        return null;
    }
}
