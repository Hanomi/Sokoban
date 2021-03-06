package main.java.com.soko.game;

import main.java.com.soko.game.model.Direction;

public interface EventListener {
    void move(Direction direction);

    void restart();

    void startNextLevel();

    void levelCompleted(int level);

    void rollback();
}
