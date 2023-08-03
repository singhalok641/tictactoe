package strategies.botplayingstrategies;

import models.Board;
import models.Move;

public interface BotPlayingStrategy {

    Move makeMove(Board board);
}
