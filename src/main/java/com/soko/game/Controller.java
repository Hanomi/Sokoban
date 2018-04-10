package main.java.com.soko.game;

import main.java.com.soko.game.model.Direction;
import main.java.com.soko.game.model.GameObjects;
import main.java.com.soko.game.model.Model;
import main.java.com.soko.game.view.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Controller implements EventListener, ActionListener, ItemListener {
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

    String newline = "\n";

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "New game":
                model.restart();
                view.update();
                break;
            case "Select level":
                String result = JOptionPane.showInputDialog(view, "Enter level number");
                try {
                    model.changeLevel(Integer.parseInt(result));
                    view.update();
                } catch (NumberFormatException e1) {
                    // delegate to View
                    System.err.println("Level incorrect " + e1.getMessage());
                    JOptionPane.showMessageDialog(view, "Level number incorrect", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "About":
                view.showAbout();
                break;
            case "Control":
                view.showControlHelp();
                break;
        }

        // тестовый блок
        JMenuItem source = (JMenuItem)(e.getSource());
        String s = "Action event detected."
                + newline
                + "    Event source: " + source.getText()
                + " (an instance of " + getClassName(source) + ")";
        System.out.println(s + newline + e.getActionCommand() + newline);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        JMenuItem source = (JMenuItem)(e.getSource());
        String s = "Item event detected."
                + newline
                + "    Event source: " + source.getText()
                + " (an instance of " + getClassName(source) + ")"
                + newline
                + "    New state: "
                + ((e.getStateChange() == ItemEvent.SELECTED) ?
                "selected":"unselected");
        System.out.println(s + newline + e.getItem() + newline);
    }

    // Returns just the class name -- no package info.
    protected String getClassName(Object o) {
        String classString = o.getClass().getName();
        int dotIndex = classString.lastIndexOf(".");
        return classString.substring(dotIndex+1);
    }
}
