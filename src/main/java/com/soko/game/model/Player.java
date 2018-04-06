package main.java.com.soko.game.model;

import java.awt.*;

public class Player extends CollisionObject implements Movable {
    Player(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.yellow);
        graphics.fillOval(getX()-getWidth()/2, getY()-getHeight()/2, getWidth(), getHeight());
    }

    @Override
    public void move(Direction direction) {
        switch (direction) {
            case UP:
                setY(getY() - Model.FIELD_CELL_SIZE);
                break;
            case DOWN:
                setY(getY() + Model.FIELD_CELL_SIZE);
                break;
            case RIGHT:
                setX(getX() + Model.FIELD_CELL_SIZE);
                break;
            case LEFT:
                setX(getX() - Model.FIELD_CELL_SIZE);
                break;
        }
    }
}
