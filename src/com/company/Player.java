package com.company;

import com.sun.javafx.scene.traversal.Direction;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {
    private int x = 50;     // координаты и скорость
    private int y = 500;    //  игрока
    private int speed1 = 5;  //
    int mapX = 0;             //  фона
    private int speedMap = 1;  //
    int t=0;      // показывает направление игрока, чтобы пуля летела по инерции
    int m = 0; // для топлива
    boolean b = true;   // отвечает за существование игрока (сбит или нет)
    int g=0;  // отвечает за количество пуль
    int p=0;  // отвечает за паузу
    private Direction playerDirection=Direction.NEXT; // энум - переменная, которая может иметь несколько значений

    public void keyPressed(KeyEvent e) {          // обработка нажатия клавиш
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_A ) {                // изменение playerDirection в зависимости
            playerDirection = Direction.LEFT;
            m=-1;// от нажатой клавиши
        }
        if(key == KeyEvent.VK_D) {
            playerDirection = Direction.RIGHT;
            m=1;
        }
        if(key == KeyEvent.VK_W ) {
            playerDirection = Direction.UP;
            t=-1;
        }
        if(key == KeyEvent.VK_S ) {
            playerDirection = Direction.DOWN;
            t=1;
        }

        if(key == KeyEvent.VK_SPACE){
            g++;
        }
        if(key == KeyEvent.VK_P){
            p++;
        }


    }


    public void keyReleased(KeyEvent e) {  //  обработка отпускания клавиш(реализовано нажатие)
        playerDirection=Direction.NEXT;
        t=0;
        m=0;
    }



    public void move() {                        // метод движения игрока
        switch(playerDirection) {               // If для ограничения перемещения,
            case UP:                            // чтобы корабль не выходил за пределы экрана
                if(y>2) {
                    y -= speed1;
                }
                break;
            case DOWN:
                if (y < 980) {
                    y += speed1;
                }
                break;
            case LEFT:
                if (x>0) {
                    x -= speed1;
                }
                break;
            case RIGHT:
                if (x<1820) {
                    x += speed1;
                }
                break;
            default:
                break;
        }
    }

    public void mapmove(){
        mapX-=speedMap;
    }     // метод движения фона

    public int getX() {
        return x;
    }         //  геттеры координат
    public int getY() {
        return y;
    }        //  игрока и
    public int getmapX() {
        return mapX;
    }  //  фона
    public int distance(int x,int y){
        return (int)Math.sqrt((this.x+50-x)*(this.x+50-x)+(this.y+50-y)*(this.y+50-y));
    }                 // функция, возвращающая расстояние до игрока
    void replay(){   // "обнуление" всех переменных для новой игры
        mapX=0;
        x=50;
        y=500;
        t=0;
        g=0;
        p=0;
        b=true;
    }
}
