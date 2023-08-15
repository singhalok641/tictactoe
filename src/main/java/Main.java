import com.scaler.tictactoe.models.*;
import com.scaler.tictactoe.controllers.GameController;
import com.scaler.tictactoe.exceptions.DuplicateSymbolException;
import com.scaler.tictactoe.exceptions.MoreThanOneBotException;
import com.scaler.tictactoe.exceptions.PlayersCountDimensionMismatchException;
import com.scaler.tictactoe.strategies.winningstrategies.ColWinningStrategy;
import com.scaler.tictactoe.strategies.winningstrategies.DiagonalWinningStrategy;
import com.scaler.tictactoe.strategies.winningstrategies.RowWinningStrategy;
import com.scaler.tictactoe.strategies.winningstrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws DuplicateSymbolException, PlayersCountDimensionMismatchException, MoreThanOneBotException {
        GameController gameController = new GameController();
        Scanner scanner = new Scanner(System.in);

        try {
            int dimensionOfGame = 3;

            List<Player> players = new ArrayList<>();

            // The players can be taken as user input from the console
            players.add(
                    new Player(1L, "Alok", new Symbol('X'), PlayerType.HUMAN)
            );

            players.add(
                    new Bot(2L, "GPT", new Symbol('O'), BotDifficultyLevel.HARD)
            );

            List<WinningStrategy> winningStrategies = List.of(
                    new RowWinningStrategy(),
                    new ColWinningStrategy(),
                    new DiagonalWinningStrategy()
            );

            // Check for time limited turns
            System.out.println("Do we want to have time limited turns? (y/n)");
            String timeLimitedTurnsAnswer = scanner.next();
            Integer turnTimeLimitInSeconds = null;

            if(timeLimitedTurnsAnswer.equalsIgnoreCase("y")){
                // If yes, then ask for the time limit
                System.out.println("Enter the time limit for each turn in seconds");
                turnTimeLimitInSeconds = scanner.nextInt();
            }

            Game game = null;

            if(turnTimeLimitInSeconds != null){
                game = gameController.startGame(
                        dimensionOfGame,
                        players,
                        winningStrategies,
                        turnTimeLimitInSeconds
                );
            }
            else{
                game = gameController.startGame(
                        dimensionOfGame,
                        players,
                        winningStrategies
                );
            }

            while(gameController.checkState(game).equals(GameState.IN_PROGRESS)) {
                // 1. printBoard
                // 2. x's turn
                // 3. ask to makeMove

                gameController.printBoard(game);

                System.out.println("Does anyone want to undo? (y/n)");
                String undoAnswer = scanner.next();

                if (undoAnswer.equalsIgnoreCase("y")) {
                    gameController.undo(game);
                    continue;
                }

                gameController.makeMove(game);
            }

            System.out.println("Game is finished");
            GameState state = gameController.checkState(game);

            if (state.equals(GameState.DRAW)) {
                System.out.println("It is a draw");
            } else {
                System.out.println("Winner is " + gameController.getWinner(game).getName());
            }

        } catch (Exception e) {
            throw e;
        }
    }
}