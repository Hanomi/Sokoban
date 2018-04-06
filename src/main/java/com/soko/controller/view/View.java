package main.java.com.soko.controller.view;

import main.java.com.soko.controller.Controller;
import main.java.com.soko.controller.EventListener;
import main.java.com.soko.controller.model.GameObjects;

import javax.swing.*;

public class View extends JFrame {
    private Controller controller;
    private Field field;

    public View(Controller controller) {
        this.controller = controller;
    }

    public void setEventListener(EventListener eventListener) {
        field.setEventListener(eventListener);
    }

    public void init() {
        field = new Field(this);
        add(field);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setTitle("Сокобан");
        setVisible(true);
    }

    public void update() {
        field.repaint();
    }

    public GameObjects getGameObjects() {
        return controller.getGameObjects();
    }

    public void completed(int level) {
        this.update();
        JOptionPane.showMessageDialog(this, "Уровень " + level + " пройден", "Уровень пройден", JOptionPane.PLAIN_MESSAGE);
        controller.startNextLevel();
    }
}
