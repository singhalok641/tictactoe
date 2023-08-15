package com.scaler.tictactoe.strategies.botplayingstrategies;

import com.scaler.tictactoe.models.BotDifficultyLevel;
import com.scaler.tictactoe.models.Player;

public class BotPlayingStrategyFactory {

    public static BotPlayingStrategy getBotPlayingStrategyForDifficultyLevel(BotDifficultyLevel botDifficultyLevel,
                                                                             Player botPlayer) {
        switch (botDifficultyLevel) {
            case HARD:
                return new DefensiveBotPlayingStrategy(botPlayer);
            // ... other cases
            default:
                return new RandomBotPlayingStrategy();
        }
    }
}
