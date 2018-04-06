package main.java.com.soko.controller.model;

import java.awt.*;

public class Wall extends CollisionObject {
    private Color brown = new Color(139,69,19);

    Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(brown);
        graphics.fillRect(getX()-getWidth()/2, getY()-getHeight()/2, getWidth(), getHeight());
    }
}
