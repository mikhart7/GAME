package com.company;

import java.awt.event.KeyEvent;

public class Player {
    private int x = 50;
    private int y = 500;
    private int speed1 = 5;
    int mapX = 0;
    private int speedMap = 1;
    int t=0;
    boolean b = true;
    int g=0;
    private Direction playerDirection = Direction.LEFT;
    private KeyEvent e;

    public void keyPressed(KeyEvent e) {
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

    }


    public void keyReleased(KeyEvent e) {
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
                if (x<1711) {
                    x += speed1;
                }
                break;
            default:
                break;
        }
    }

    public void mapmove(){

        mapX-=speedMap;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public int getmapX() {
        return mapX;
    }
    public int distance(int x,int y){
        return (int)Math.sqrt((this.x+50-x)*(this.x+50-x)+(this.y+50-y)*(this.y+50-y));
    }
}
