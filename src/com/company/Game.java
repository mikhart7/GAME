package com.company;

import javax.swing.*;


public class Game {

    public static void main(String[] args) {

        JFrame frame = new JFrame("JustGame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.add(new Main(frame));
        frame.setVisible(true);

    }
}

