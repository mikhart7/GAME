package com.company;
public class Ship {
    public int x, y;  // координаты корабля
    public int dx= 5; // изменение координаты по x
    int dy = 0;
    boolean b = true; // отвечает за существование корабля (сбит или нет)
    Bullet shipbullets [] = new Bullet[25]; // у каждого корабля свой массив пуль
    int  c = 0; // количество выпущенных пуль

    public Ship(int x, int y) {    //
        this.x = x;                // конструктор корабля
        this.y = y;                //

    }

    public void move() {   // метод движения корабля
        x -= dx;
        y+=dy;

    }

    public int distance(int x,int y){  // функция, возвращающая расстояние до корабля
        return (int)Math.sqrt((this.x+50-x)*(this.x+50-x)+(this.y+50-y)*(this.y+50-y));
    }
    void replay(){ // "обнуление"  для новой игры
        dy=0;
        c=0;
        b=true;
    }
}
