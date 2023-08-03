package strategies.botplayingstrategies;

import models.BotDifficultyLevel;

public class BotPlayingStrategyFactory {

    public static BotPlayingStrategy getBotPlayingStrategyForDifficultyLevel(BotDifficultyLevel level) {
        return new EasyBotPlayingStrategy();
    }
}
