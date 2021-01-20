package com.planegame;

import javax.swing.*;

public class GameScreen extends JFrame {

    public GameScreen(String game_screen) { }

    public static void main(String[] args){

        GameScreen screen = new GameScreen("Game Screen");

        screen.setResizable(false);
        screen.setFocusable(false);


        screen.setSize(800,600);



        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Game game = new Game();

        game.requestFocus();


        game.addMouseListener(game);

        game.setFocusable(true);
        game.setFocusTraversalKeysEnabled(false);

        screen.add(game);

        screen.setVisible(true);

    }
}
