## TicTacToe:

### Overview:
TicTacToe is a game traditionally played between two players on a 3x3 grid. Each player is assigned a symbol (typically X or O), and they alternate turns placing their symbol on the grid, aiming to form a continuous line of their symbol horizontally, vertically, or diagonally.

This project's objective is to create an advanced, more flexible version of TicTacToe, incorporating new features such as supporting more players, different grid sizes, bots as players, undoing moves, and a leaderboard.

### Project Scope:

Our game must:

1. Support any number of players, each of whom will be given a unique symbol.
2. Accommodate both human and bot players. Human players should be assigned unique usernames.
3. Bots can have different difficulty levels, which can be set during game initialization.
4. Only 1 bot can be present in a game.
5. Allow the grid size to be any NxN.
6. Incorporate an undo feature that can retract a move.
7. Implement an O(1) time complexity winner detection algorithm.

### Detailed Requirements:

#### Game Initialization:

- The game should start by taking the number of players, the grid size, and the symbols for each player as input.
- Input format: `StartGame [Number of Players] [User ID and Symbol for each player separated by space] [Board Size]`
- Example: `StartGame 2 u1 X u2 O 3`
- If a player is a bot, their user id will be prefixed with 'C', e.g., 'C1'.

#### Gameplay:

- Players should alternate turns in the order they were entered during game initialization.
- Each turn involves placing their symbol in an empty grid cell.
- A player's turn can be represented as: `Turn [User ID] [Row,Column]`
- Example: `Turn u1 1,1`

#### Undo Feature:

- At any point, any player should be able to undo their move.
- Undo operation: `Undo [User ID] [Number of steps]`
- Example: `Undo u1 1`


#### Game Termination:

- The game should end when a player forms a line of their symbol (horizontally, vertically, or diagonally) or when all cells are filled (a draw).
- The game should output the winner (or a draw), update the leaderboard, and ask if the users want to play again.

**Note:**

- The code should be functionally correct and should adhere to best software engineering practices. The solution should be well-structured, modular, readable, and extensible.
- Developers are expected to write unit tests to validate the functionality.
- A graphical user interface (GUI) is not required for this project.
- The software should be designed to allow for easy addition of future enhancements like time-limited turns, spectator mode, gameplay analytics, tournament mode, and alternative victory conditions.

=======================================================================

**Optional Features for Consideration:**

1. Time-limited turns.
2. Spectator mode.
3. Detailed game analytics tracking.
4. Tournament mode with multiple rounds.
5. Alternative victory conditions.
6. Leaderboard.
7. Pause/resume game.

**1. Time-Limited Turns:**

Each player should be given a specific amount of time to complete their turn. If a player fails to make a move within the time limit, one of two actions can occur: their turn is skipped, or the game is forfeited and their opponent declared the winner.

* Input format: `SetTimer [User ID] [Seconds]`
* Example: `SetTimer u1 60` - This command gives player u1 60 seconds to complete their move.

**2. Spectator Mode:**

Spectator mode allows additional users to watch the game without participating. They should be able to see the game state and moves, but not interact with the game.

* Input format: `AddSpectator [Spectator ID]`
* Example: `AddSpectator s1` - This command adds a spectator with the ID 's1' to the game.

**3. Detailed Game Analytics Tracking:**

Game analytics could track and provide data on a wide range of information, such as:

* Number of games played by each player.
* Average duration of each game.
* Most frequent winning symbol or player.
* Move history of each game.

* Command to view analytics: `ShowAnalytics [User ID/Optional]`
* Example: `ShowAnalytics u1` - This command shows the analytics related to player u1.

**4. Tournament Mode with Multiple Rounds:**

In Tournament mode, multiple rounds of games are played between players. Players could be grouped and scored based on wins, and the player with the highest score at the end of the tournament is declared the winner.

* Command to start a tournament: `StartTournament [Number of Players] [... User ID and Symbol for each player separated by space ...] [Board Size] [Number of Rounds]`
* Example: `StartTournament 2 u1 X u2 O 3 5` - This command starts a tournament with 2 players on a 3x3 grid, to be played over 5 rounds.

**5. Alternative Victory Conditions:**

Rather than the traditional condition of forming a continuous line of symbols, alternative victory conditions could be set. For example, the player with the most symbols on the board when all cells are filled, or a specific pattern is made.

* Command to set alternative victory condition: `SetVictoryCondition [Condition ID]`
* Example: `SetVictoryCondition MostSymbols` - This command sets the victory condition to the player with the most symbols on the board when all cells are filled.

**6. Leaderboard:**

- The game should keep track of each player's wins and losses, and display a leaderboard.
- The leaderboard can be requested at any point using the command: `ShowLeaderboard`
- The leaderboard should display the player usernames and their win counts, ordered by the number of wins.
- Example output:

    ```
    Leaderboard:
    1. u1 - 10 wins
    2. u2 - 8 wins
    3. C1 - 5 wins
    ```

**7. Pause/Resume Game:**

Players should have the ability to pause the game, during which no moves can be made. The game should resume from the exact state where it was paused.

- Command to pause the game: `PauseGame`
- Command to resume the game: `ResumeGame`

After the game is paused, any commands to make a move, undo a move, or perform other actions should be ignored until the game is resumed. Note that this might require storing the game state when pausing, to be restored when resuming. Also, when the game is resumed, it should continue with the turn of the player who was next to play when the game was paused.

Example of usage:

```
Turn u1 1,1
PauseGame
Turn u2 1,2  # This should be ignored
ResumeGame
Turn u2 1,2  # Now this should be processed
```

To make this feature even more interesting, the game could be resumed after a certain amount of time has passed, or after a specific event has occurred. For example, the game could be resumed after 60 seconds, or after a specific player has made a move.
The interaction of the time-limited turns feature with the pause/resume feature could be designed in a couple of interesting ways:

1. **Non-decremental Pause:** When a game is paused, the timer for the current player's turn is also paused. When the game is resumed, the timer continues from where it left off. This method prioritizes fairness in the case of interruptions.

    - Example:
        ```
        SetTimer u1 60
        Turn u1 1,1
        PauseGame
        # Some time passes...
        ResumeGame
        # u1 still has the remainder of their 60 seconds to complete their turn.
        ```

2. **Decremental Pause:** When a game is paused, the timer for the current player's turn continues to run down. If the timer runs out while the game is paused, then the player loses their turn, or even the game, depending on the rules set. This method could be used in high-stakes games where time management is part of the challenge.

    - Example:
        ```
        SetTimer u1 60
        Turn u1 1,1
        PauseGame
        # 60 seconds pass...
        ResumeGame
        # u1's turn is skipped or lost because their time ran out during the pause.
        ```

These could be set as game options, allowing players to decide how they want the pause feature to interact with the time-limited turns.

You would use a command like `SetPauseType [Non-decremental/Decremental]` to choose the pause type at the beginning of the game.

For example, `SetPauseType Non-decremental` would set the game to the non-decremental pause type.


**8. Advanced: Multiple Game Modes:**

As a bonus, students can add multiple game modes (like tournament mode, quick match, practice mode, etc.). This will push them to create flexible systems that can adapt to different rule sets and modes.


**9. Extend the Game for Multiple Players:**

The traditional TicTacToe game is for two players. Your students are tasked to extend the game to accommodate more than two players. This can be challenging, as they will have to come up with strategies to handle game mechanics like turn management, symbol assignment, and win conditions for multiple players.

=======================================================================

**Tic-Tac-Toe:**

Classes:

1. Design TicTacToe
2. Code TicTacToe 1
3. Code TicTacToe 2
4. Code TicTacToe 3

Shortlisted optional features for assignments:
1. Allow users to play multiple games
2. Leaderboard
3. Detailed Game Analytics Tracking
4. Time-Limited Turns
5. Pause/Resume Game for non-decremental pause type
6. Undo a move
7. Implementing Bot Playing Strategies


Idea for Design TicTacToe Class:
1. MCQ questions
Topics-> Identify design patterns used in this case study


=======================================================================

**Assignment 1: Implementing Bot Playing Strategies**

In the project developed in class, we have created a `BotPlayer` as one of the player types. In this assignment, your task is to implement the bot's playing strategy using the strategy design pattern.

Your objective is to develop at least two concrete strategies for the bot player:

1. **RandomStrategy:** The bot should make its move randomly. It can place its symbol in any empty slot on the board.

2. **DefensiveStrategy:** The bot should prioritize blocking its opponents from completing a line. If an opponent is one move away from winning, the bot should place its symbol to block the victory. If there is no immediate threat, the bot can choose a random empty slot.

You should define an interface, `BotStrategy`, with a method `makeMove()`. Both the `RandomStrategy` and `DefensiveStrategy` classes should implement this interface and provide concrete implementations for `makeMove()`.

```java
interface BotStrategy {
    Move makeMove(GameBoard board);
}
```

In the `BotPlayer` class, you should include a reference to the `BotStrategy` and a method to execute the strategy:

```java
class BotPlayer extends Player {
    private BotStrategy strategy;

    public BotPlayer(BotStrategy strategy) {
        this.strategy = strategy;
    }

    public Move makeMove() {
        return strategy.makeMove(this.gameBoard);
    }
}
```

The `BotPlayer` should use the strategy set in its constructor to determine its move in each turn.

**Bonus Task:** For an additional challenge, implement an `AggressiveStrategy`. In this strategy, the bot prioritizes completing its own line. If it is one move away from winning, it should place its symbol to achieve victory. If it's not one move away from winning, it should block the opponent from winning (similar to the defensive strategy). If there is no imminent victory or threat, the bot can place its symbol randomly.

To test your implementation, you should be able to switch the strategies for the bot player and observe changes in its behavior. You can test this by creating a game with bot players only and noting how they respond to different situations based on their strategy.

=======================================================================


**Assignment 2: Implementing Time-Limited Turns**

One of the enhancements to make our TicTacToe game more dynamic and challenging is to introduce the concept of time-limited turns. In this assignment, your task is to incorporate this feature into the existing game framework.

Your objective is to design and implement a system where each player is allotted a fixed amount of time to make their move. The time limit can be specified at the start of the game or can be changed during the game.

Here's how the feature should work:

1. **Setting Time Limit:** At the start of the game, you should be able to set a time limit for each turn. The command to set the time limit can look something like this: `SetTimeLimit [seconds]`. For example, `SetTimeLimit 60` would mean each player has 60 seconds to make their move.

2. **Countdown:** During each player's turn, a countdown should start. If the player makes their move within the time limit, the game continues as normal. The countdown resets for the next player's turn.

3. **Exceeding Time Limit:** If a player fails to make a move within the time limit, one of two actions should occur. You can choose one of the following options or provide a mechanism to choose the action at the start of the game:

    - **Skip Turn:** The player's turn is skipped, and the game proceeds to the next player.
    - **Forfeit Game:** The player automatically loses, and the game ends.

4. **Pause/Resume Feature:** Implement a feature to pause the countdown. The command could be `Pause` to pause the game and `Resume` to resume the game. The countdown should resume from where it was paused.

5. **Changing Time Limit:** Provide a mechanism to change the time limit in the middle of the game. The command can be similar to the one used to set the time limit. Changing the time limit should not affect the countdown of the current turn.

Please ensure to handle edge cases and provide informative messages to the players, especially when time is about to run out, or when a turn is skipped or a game is forfeited due to exceeding the time limit.

This feature will require working with real-time elements and multithreading in Java. It's a good chance to familiarize yourself with Java's concurrency tools. As always, make sure your code is clean, readable, and well-documented. Test your implementation thoroughly to ensure it behaves as expected in different scenarios.

Multithreading in Java would be a great way to implement this feature. Here's how you could approach it:

1. **Timer Thread:** When a player's turn starts, you could spawn a new thread (let's call this the "Timer Thread"). This thread would begin a countdown from the set time limit to zero.

2. **Monitoring Player Moves:** The main game thread, in the meantime, would be waiting for the player's input (the move). If the player makes a move before the Timer Thread reaches zero, the move is made on the board, the Timer Thread is interrupted and stopped, and it becomes the next player's turn.

3. **Player Exceeds Time Limit:** If the Timer Thread reaches zero before the player makes a move, it means the player has exceeded their time limit. In this case, the Timer Thread should notify the main game thread (this can be done using a shared variable, callback, or a condition), which can then handle this situation as per the game rules (either skip the player's turn or declare the game forfeit for this player, as the case may be).

4. **Pause/Resume Feature:** If a pause command is received, you can suspend the Timer Thread (do not confuse this with the deprecated `Thread.suspend()` method). Resuming would then simply restart the Timer Thread from where it left off.

Remember, you'll need to take care of thread-safety when shared resources are being accessed or modified by multiple threads. Also, it's important to properly handle the lifecycle of your threads, making sure to interrupt and stop any that are not needed, to avoid potential memory leaks.

Java's concurrency utilities like `ExecutorService`, `Future`, and `CountDownLatch` could be quite useful here. Or for more advanced control, you could consider using the `java.util.concurrent.locks` package.

Please note that multithreading can make a program more complex and harder to debug, so it should be used judiciously. Be sure to thoroughly test all edge cases to ensure your implementation is robust.