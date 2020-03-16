package com.company;
public class Ship {
    public int x, y;
    public int dx= 4;
    int dy;
    boolean b = true;
    Bullet kk [] = new Bullet[30];
    int  c = 0;


    public Ship(int x, int y) {
        this.x = x;
        this.y = y;

    }

    public void move() {
        x -= dx;
    }

    public int distance(int x,int y){
        return (int)Math.sqrt((this.x+50-x)*(this.x+50-x)+(this.y+50-y)*(this.y+50-y));
    }
}
