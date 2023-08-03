package strategies.winningstrategies;

import models.Board;
import models.Move;

public interface WinningStrategy {

    boolean checkWinner(Board board, Move move);

    void handleUndo(Board board, Move move);
}
