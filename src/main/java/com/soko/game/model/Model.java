package main.java.com.soko.game.model;

import main.java.com.soko.game.EventListener;

import java.io.File;
import java.util.*;

public class Model {
    public final static int FIELD_CELL_SIZE = 20;
    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private LevelLoader levelLoader;
    private Stack<GameObjects> previousStates = new Stack<>();

    {
        ClassLoader classLoader = getClass().getClassLoader();
        String path = classLoader.getResource("levels/levels.txt").getPath();
        levelLoader = new LevelLoader(new File(path));
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        restartLevel(currentLevel);
        previousStates.clear();
    }

    public void startNextLevel() {
        currentLevel++;
        restart();
    }

    public void changeLevel(int i) {
        currentLevel = i;
        restart();
    }

    public void move(Direction direction) {
        Player player = gameObjects.getPlayer();
        if (checkWallCollision(player, direction) || checkBoxAndMoveAvailable(direction)) return;
        saveState();
        player.move(direction);
        checkCompletion();
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        return gameObjects.getWalls().stream()
                .anyMatch(x -> gameObject.isCollision(x, direction));
    }

    public boolean checkBoxAndMoveAvailable(Direction direction) {
        Player player = gameObjects.getPlayer();

        Optional<Box> box = gameObjects.getBoxes().stream()
                .filter(x -> player.isCollision(x, direction))
                .findFirst();

        if (box.isPresent()) {
            if (checkWallCollision(box.get(), direction)) return true;
            if (gameObjects.getBoxes().stream()
                    .anyMatch(anotherBox -> box.get().isCollision(anotherBox, direction))) return true;
            box.get().move(direction);
        }
        return false;
    }

    public void checkCompletion() {
        Set<Box> boxes = gameObjects.getBoxes();
        Set<Home> homes = gameObjects.getHomes();
        int size = boxes.size();
        for (Box box : boxes) {
            for (Home home : homes) {
                if (box.getX() == home.getX() && box.getY() == home.getY()) size--;
            }
        }
        if (size == 0) eventListener.levelCompleted(currentLevel);
    }

    public void rollback() {
        // cancel move, restore from stack
        if (!previousStates.empty()) {
            gameObjects = previousStates.pop();
        }
    }

    private void saveState() {
        // save player and boxes copy in stack
        Set<Box> boxes = new HashSet<>();
        Player player = new Player(gameObjects.getPlayer().getX(), gameObjects.getPlayer().getY());
        gameObjects.getBoxes().forEach(f -> boxes.add(new Box(f.getX(), f.getY())));
        GameObjects gameObjectsCopy = new GameObjects(gameObjects.getWalls(), boxes, gameObjects.getHomes(), player);
        previousStates.push(gameObjectsCopy);
    }
}
