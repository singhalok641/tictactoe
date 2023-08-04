package strategies.botplayingstrategies;

/*
RandomStrategy: The bot should make its move randomly.
It can place its symbol in any empty slot on the board.
*/

import models.Board;
import models.Cell;
import models.CellState;
import models.Move;

import java.util.List;

public class RandomBotPlayingStrategy implements BotPlayingStrategy{
    @Override
    public Move makeMove(Board board) {
        for (List<Cell> row: board.getBoard()) {
            for (Cell cell: row) {
                if (cell.getCellState().equals(CellState.EMPTY)) {
                    return new Move(
                            cell,
                            null
                    );
                }
            }
        }
        return null;
    }
}
