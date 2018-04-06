package main.java.com.soko.controller.model;

import java.awt.*;

public class Box extends CollisionObject implements Movable {
    Box(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.orange);
        graphics.drawRect(getX()-getWidth()/2, getY()-getHeight()/2, getWidth(), getHeight());
        graphics.drawLine(getX()-getWidth()/2, getY()-getHeight()/2, getX()+getWidth()/2, getY()+getHeight()/2);
        graphics.drawLine(getX()-getWidth()/2, getY()+getHeight()/2, getX()+getWidth()/2, getY()-getHeight()/2);
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