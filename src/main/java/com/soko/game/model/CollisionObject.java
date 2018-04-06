package main.java.com.soko.game.model;

import static main.java.com.soko.game.model.Model.FIELD_CELL_SIZE;

abstract class CollisionObject extends GameObject {
    CollisionObject(int x, int y) {
        super(x, y);
    }

    boolean isCollision(GameObject gameObject, Direction direction) {
        switch (direction) {
            case UP:
                return getY() - FIELD_CELL_SIZE == gameObject.getY() && getX() == gameObject.getX();
            case DOWN:
                return getY() + FIELD_CELL_SIZE == gameObject.getY() && getX() == gameObject.getX();
            case LEFT:
                return getX() - FIELD_CELL_SIZE == gameObject.getX() && getY() == gameObject.getY();
            case RIGHT:
                return getX() + FIELD_CELL_SIZE == gameObject.getX() && getY() == gameObject.getY();
            default:
                return false;
        }
    }
}
