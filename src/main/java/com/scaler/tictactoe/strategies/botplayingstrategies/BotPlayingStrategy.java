package com.scaler.tictactoe.strategies.botplayingstrategies;

import com.scaler.tictactoe.models.Board;
import com.scaler.tictactoe.models.Move;

public interface BotPlayingStrategy {

    Move makeMove(Board board);
}
