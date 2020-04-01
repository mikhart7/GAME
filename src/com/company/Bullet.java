package com.company;
public  class Bullet extends Ship {  // наследование от корабля
boolean b=false;    // отвечает за существование пули
    public Bullet(int x, int y,int dx,int dy) {    //
        super(x, y);                               // конструктор
        this.dx=dx;                                //  пули
        this.dy=dy;                                //
    }

    @Override
    public void move() {   // метод движения пули
        x += dx;
        y+=dy;
    }
    void replay(){
        b=false;
    }   // "обнуление" для новой игры
}
