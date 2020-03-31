package com.company;

import com.sun.javafx.scene.traversal.Direction;

import java.awt.event.KeyEvent;

public class Player {
    private int x = 50;     // координаты и скорость
    private int y = 500;    //  игрока
    private int speed1 = 5;  //
    int mapX = 0;             //  фона
    private int speedMap = 1;  //
    int t=0;
    boolean b = true;   // отвечает за существование игрока (сбит или нет)
    int g=0;  // отвечает за количество пуль
    int p=0;  // отвечает за паузу
    private Direction playerDirection=Direction.NEXT; // энум - переменная, которая может иметь несколько значений

    public void keyPressed(KeyEvent e) {          // обработка нажатия клавиш
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_A ) {
            playerDirection = Direction.LEFT;
        }
        if(key == KeyEvent.VK_D) {
            playerDirection = Direction.RIGHT;
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


    public void keyReleased(KeyEvent e) {  //
        playerDirection=Direction.NEXT;
        t=0;

    }



    public void move() {
        switch(playerDirection) {
            case UP:
                if(y>2) {
                    y -= speed1;
                }
                break;
            case DOWN:
                if (y < 973) {
                    y += speed1;
                }
                break;
            case LEFT:
                if (x>0) {
                    x -= speed1;
                }
                break;
            case RIGHT:
                if (x<1811) {
                    x += speed1;
                }
                break;
            default:
                break;
        }
    }

    public void mapmove(){     // метод движения фона
        mapX-=speedMap;
    }


    public int getX() {
        return x;
    }         //  геттеры координат
    public int getY() {
        return y;
    }        //  игрока и
    public int getmapX() {
        return mapX;
    }  //  фона
    public int distance(int x,int y){   // функция, возвращающая расстояние до игрока
        return (int)Math.sqrt((this.x+50-x)*(this.x+50-x)+(this.y+50-y)*(this.y+50-y));
    }
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
