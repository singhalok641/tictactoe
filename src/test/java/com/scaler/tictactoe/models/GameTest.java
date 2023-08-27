package com.scaler.tictactoe.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.scaler.tictactoe.exceptions.DuplicateSymbolException;
import com.scaler.tictactoe.exceptions.MoreThanOneBotException;
import com.scaler.tictactoe.exceptions.PlayersCountDimensionMismatchException;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GameTest {

        @Test
        void testGameBuilder() throws DuplicateSymbolException, PlayersCountDimensionMismatchException, MoreThanOneBotException {
            // Create a Game using the Game.Builder
            Game game = Game.getBuilder()
                    .setDimension(3)
                    .setTurnTimeLimitInSeconds(10)
                    .setPlayers(List.of(
                            new Player(1L, "Alok", new Symbol('X'), PlayerType.HUMAN),
                            new Bot(2L, "GPT", new Symbol('O'), BotDifficultyLevel.HARD)
                    ))
                    .build();

            // Check that the game has been created with the correct dimension
            assertEquals(3, game.getBoard().getSize(), "The board should have the correct dimension");

            // Check that the game has been created with the correct number of players
            assertEquals(2, game.getPlayers().size(), "The game should have the correct number of players");

            // Check that the game has been created with the correct turn time limit
//            assertEquals(10, game.getTurnManager().getTurnTimeLimitInSeconds(), "The game should have the correct turn time limit");
        }

        @Test
        void testGameBuilderWithInvalidDimension() {
            // Check that the Game.Builder throws an exception when the dimension is invalid
            assertThrows(IllegalArgumentException.class, () -> {
                Game.getBuilder()
                        .setDimension(2)
                        .setTurnTimeLimitInSeconds(10)
                        .setPlayers(List.of(
                                new Player(1L, "Alok", new Symbol('X'), PlayerType.HUMAN),
                                new Bot(2L, "GPT", new Symbol('O'), BotDifficultyLevel.HARD)
                        ))
                        .build();
            });
        }

        @Test
        void testGameBuilderWithInvalidPlayersCount() {
            // Check that the Game.Builder throws an exception when the number of players is invalid
            assertThrows(PlayersCountDimensionMismatchException.class, () -> {
                Game.getBuilder()
                        .setDimension(3)
                        .setTurnTimeLimitInSeconds(10)
                        .setPlayers(List.of(
                                new Player(1L, "Alok", new Symbol('X'), PlayerType.HUMAN)
                        ))
                        .build();
            });
        }

        @Test
        void testGameBuilderWithMoreThanOneBot() {
            // Check that the Game.Builder throws an exception when there is more than one bot
            assertThrows(MoreThanOneBotException.class, () -> {
                Game.getBuilder()
                        .setDimension(3)
                        .setTurnTimeLimitInSeconds(10)
                        .setPlayers(List.of(
                                new Bot(1L, "GPT", new Symbol('O'), BotDifficultyLevel.HARD),
                                new Bot(2L, "GPT", new Symbol('O'), BotDifficultyLevel.HARD)
                        ))
                        .build();


            });
        }

        // Write more tests here

}
