package main.java.com.soko.controller;

import main.java.com.soko.controller.model.Direction;

public interface EventListener {
    void move(Direction direction);

    void restart();

    void startNextLevel();

    void levelCompleted(int level);

    void rollback();
}
