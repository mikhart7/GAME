package com.company;

import javax.swing.*;


public class Game {

    public static void main(String[] args) {
        JFrame frame = new JFrame("JustGame");   // создаём  рамку, которая  будет отображаться при запуске нашей игры
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // устанавливаем макс размеры рамки
            frame.setUndecorated(true);  // убираем декорации
            frame.add(new Main(frame));   //вставляем панель(Main) в рамку(frame)
            frame.setVisible(true);  // делаем видимой
        }
    }

