package main.java.com.soko.game;

import main.java.com.soko.game.model.Direction;
import main.java.com.soko.game.model.GameObjects;
import main.java.com.soko.game.model.Model;
import main.java.com.soko.game.view.View;

public class Controller implements EventListener {
    private View view;
    private Model model;

    public Controller() {
        this.view = new View(this);
        this.model = new Model();
        this.model.restart();
        this.view.init();
        this.view.setEventListener(this);
        this.model.setEventListener(this);
    }

    public static void main(String[] args) {
        Controller game = new Controller();
    }

    @Override
    public void move(Direction direction) {
        model.move(direction);
        view.update();
    }

    @Override
    public void restart() {
        model.restart();
        view.update();
    }

    @Override
    public void startNextLevel() {
        model.startNextLevel();
        view.update();
    }

    @Override
    public void levelCompleted(int level) {
        view.completed(level);
    }

    @Override
    public void rollback() {
        model.rollback();
        view.update();
    }

    public GameObjects getGameObjects() {
        return model.getGameObjects();
    }
}
