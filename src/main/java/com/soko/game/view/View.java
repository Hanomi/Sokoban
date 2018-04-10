package main.java.com.soko.game.view;

import main.java.com.soko.game.Controller;
import main.java.com.soko.game.EventListener;
import main.java.com.soko.game.model.GameObjects;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

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

        JMenuBar menuBar = initMenuBar();
        setJMenuBar(menuBar);

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

    private JMenuBar initMenuBar() {
        JMenuBar menuBar;
        JMenu menu;
        JMenuItem menuItem;

        //Create the menu bar.
        menuBar = new JMenuBar();

        //Build the first menu.
        menu = new JMenu("Game");
        menu.setMnemonic(KeyEvent.VK_G);
        menuBar.add(menu);

        menuItem = new JMenuItem("New game", KeyEvent.VK_N);
        menuItem.addActionListener(controller);
        menu.add(menuItem);

        menuItem = new JMenuItem("Select level", KeyEvent.VK_L);
        menuItem.addActionListener(controller);
        menu.add(menuItem);

        //Build second menu in the menu bar.
        menu = new JMenu("Help");
        menu.setMnemonic(KeyEvent.VK_H);
        menuBar.add(menu);

        menuItem = new JMenuItem("Control", KeyEvent.VK_C);
        menuItem.addActionListener(controller);
        menu.add(menuItem);

        menuItem = new JMenuItem("About", KeyEvent.VK_A);
        menuItem.addActionListener(controller);
        menu.add(menuItem);

        return menuBar;
    }

    public void showAbout() {
        JOptionPane.showMessageDialog(this,
                new String[] {
                        "Sokoban:\n",
                        "created by\n",
                        "Naera Meir"},
                "About",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void showControlHelp() {
        JOptionPane.showMessageDialog(this,
                new String[] {
                    "'R' - restart\n",
                    "'Z' - cancel move\n",
                    "arrow keys for moving"},
                "Control",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
