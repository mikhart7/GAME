package com.company;
public  class Bullet extends Ship {
boolean b=true;
    public Bullet(int x, int y,int dx,int dy) {
        super(x, y);
        this.dx=dx;
        this.dy=dy;
    }

    @Override
    public void move() {
        x += dx;
        y+=dy;
    }

}
