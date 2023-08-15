package strategies.botplayingstrategies;

/*
* To implement the DefensiveBotPlayingStrategy, the bot needs to check if the opponent is about to win in the next move.
* If so, the bot should block that spot.
* If there are no immediate threats, the bot can make a random move as in the RandomBotPlayingStrategy.
* For simplicity, let's assume we have two players X and O, and the bot is playing with O.
*
*
*
* */

import models.Board;
import models.Cell;
import models.CellState;
import models.Move;

import java.util.ArrayList;
import java.util.List;

public class DefensiveBotPlayingStrategy implements BotPlayingStrategy {

    private RandomBotPlayingStrategy randomBotPlayingStrategy = new RandomBotPlayingStrategy();

    @Override
    public Move makeMove(Board board) {
        // check rows
        for (List<Cell> row : board.getBoard()) {
//            if (countCellState(row, CellState.X) == board.getSize() - 1
//                    && countCellState(row, CellState.EMPTY) == 1) {
//                return new Move(
//                        findCell(row, CellState.EMPTY),
//                        null
//                );
//            }
        }

        // check columns
        for (int j = 0; j < board.getSize(); j++) {
            List<Cell> column = new ArrayList<>();
            for (List<Cell> row : board.getBoard()) {
                column.add(row.get(j));
            }
//            if (countCellState(column, CellState.X) == board.getSize() - 1
//                    && countCellState(column, CellState.EMPTY) == 1) {
//                return new Move(
//                        findCell(column, CellState.EMPTY),
//                        null
//                );
//            }
        }

        // check diagonals
        List<Cell> diagonal1 = new ArrayList<>();
        List<Cell> diagonal2 = new ArrayList<>();
        for (int i = 0; i < board.getSize(); i++) {
            diagonal1.add(board.getBoard().get(i).get(i));
            diagonal2.add(board.getBoard().get(i).get(board.getSize() - 1 - i));
        }
//        if (countCellState(diagonal1, CellState.X) == board.getSize() - 1
//                && countCellState(diagonal1, CellState.EMPTY) == 1) {
//            return new Move(
//                    findCell(diagonal1, CellState.EMPTY),
//                    null
//            );
//        }
//        if (countCellState(diagonal2, CellState.X) == board.getSize() - 1
//                && countCellState(diagonal2, CellState.EMPTY) == 1) {
//            return new Move(
//                    findCell(diagonal2, CellState.EMPTY),
//                    null
//            );
//        }

        // no immediate threat, make a random move
        return randomBotPlayingStrategy.makeMove(board);
    }

    private int countCellState(List<Cell> cells, CellState cellState) {
        int count = 0;
        for (Cell cell : cells) {
            if (cell.getCellState().equals(cellState)) {
                count++;
            }
        }
        return count;
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

