package com.scaler.tictactoe.models;

import java.util.concurrent.*;

/*
This class will manage the turn time limit.
It will be responsible for starting the timer when a player's turn begins,
and for notifying the game when the time is up.
*/

public class TimeLimitedTurnManager {
    private final int turnTimeLimit;
    private Future<?> future;

    public int getTurnTimeLimit() {
        return turnTimeLimit;
    }

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public TimeLimitedTurnManager(int turnTimeLimitInSeconds) {
        this.turnTimeLimit = turnTimeLimitInSeconds;
    }

    public void startTurnTimer(Runnable onTimeExceeded) {
        future = executor.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(turnTimeLimit);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return; // interrupted means the turn completed before the timeout
            }
            onTimeExceeded.run(); // Execute the provided action (e.g. skipping the turn)
        });
    }

    public void stopTurnTimer() {
        if (future != null) {
            future.cancel(true); // Interrupts the sleeping thread
        }
    }

    public void shutdown() {
        executor.shutdownNow();
    }
}
