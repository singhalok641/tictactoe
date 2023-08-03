package controllers;

import exceptions.DuplicateSymbolException;
import exceptions.MoreThanOneBotException;
import exceptions.PlayersCountDimensionMismatchException;
import models.Game;
import models.GameState;
import models.Player;
import strategies.winningstrategies.WinningStrategy;

import java.util.List;

public class GameController {

    public Game startGame(int dimensionOfBoard,
                   List<Player> players,
                   List<WinningStrategy> winningStrategies) throws DuplicateSymbolException, PlayersCountDimensionMismatchException, MoreThanOneBotException {
//        Game.Builder.setPlayers(....)
//        .setWinningStrategies(...)
//        .build();
//
//        Game.Builder.addPlayer(..).addPlayer(..)
//        .addWinningStrategy().build();

        return Game.getBuilder()
                .setPlayers(players)
                .setWinningStrategies(winningStrategies)
                .setDimension(dimensionOfBoard)
                .build();
    }

    public void makeMove(Game game) {
        game.makeMove();
    }

    public GameState checkState(Game game) {
        return game.getGameState();
    }

    public Player getWinner(Game game) {
        return game.getWinner();
    }

    public void printBoard(Game game) {
        game.printBoard();
    }

    public void undo(Game game) {
        game.undo();
    }
}
