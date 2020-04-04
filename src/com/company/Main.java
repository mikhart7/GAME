package com.company;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JPanel implements ActionListener  {   // наследования от JPanel и реализация ActionListener
   private Image imgfon = new ImageIcon("FON.png").getImage();               //
    private Image imgM1 = new ImageIcon("spaceShips2.png").getImage();       //
    private Image imgM2 = new ImageIcon("spaceShips2.2.png").getImage();     // необходимые картинки
    private Image imgpl = new ImageIcon("spaceShips1.png").getImage();       //  для игры
    private Image m = new ImageIcon("menu.jpg").getImage();                  //
    private Image r = new ImageIcon("rus.jpg").getImage();                   //
    private Image how = new ImageIcon("how.png").getImage();                 //
    Timer timer = new Timer(8, this);       // время
    private void replay (){
        player.replay();
        for (int i =0;i<bullets.length;i++){
            bullets[i].replay();
            bullets[0]=null;
        }

        for(int i = 0;i<ships.length;i++){
            ships[i].replay();
            for(int j =0;j<ships[i].shipbullets.length;j++) {
                ships[i].shipbullets[j].replay();
            }
            ships[0]=null;
        }
        S=3;
        n=0;
        c=0;
        k=0 ;
        b=false;
        timer.restart(); // перезапускаем время
    }    // "обнуление"  для новой игры


    Player player = new Player();      // создаём игрока
        Ship[] ships = new Ship[30] ; // массив кораблей
        int k = 0;            // количество кораблей, появившихся на экране
        Bullet [] bullets = new Bullet[36]; // массив пуль игрока
        int c = 0;            // количество пуль игрока, появившихся на экране
        int n=0;             //
        boolean b = false;  // игра или меню?
        int S = 3;
        int h =0;        // отвечает за измениние цвета надписи "PLAY" при перемещение курсора мыши


    MouseMotionListener mouse2 = new MouseMotionListener() {
    @Override
    public void mouseDragged(MouseEvent e) {
    } // "обещаем" реализовать все методы MouseMotionListener

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if(x>=800 && x<=1100 && y>=300 && y<=400) {
            h=1;
        }
        else{
            h=0;
        }
    }// реализация измениния цвета надписи "PLAY"

};

MouseListener mouse1 = new MouseListener() {
    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if(x>=800 && x<=1100 && y>=300 && y<=400) {
            b = true;
        }

    }  // при нажатие запускается игра из меню

    @Override
    public void mousePressed(MouseEvent e) {

    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    @Override
    public void mouseExited(MouseEvent e) {

    }// "обещаем" реализовать все методы MouseListener

};


    JFrame frame;
    public Main(JFrame frame) {    // конструктор Main от рамки класса Game
    addMouseListener(mouse1);                         // добавляем обработку нажатия
    addMouseMotionListener(mouse2);                   // мыши
        this.frame = frame;
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                player.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e){
                player.keyReleased(e);
            }
        });   // добавляем обработку нажатия и отпускания клавиш от игрока
        timer.start();     // запускаем время
    }

    public void paint(Graphics g) {  // отрисовка
    if(b==false){  // меню
        g.drawImage(m,0,0,1920,1080,null);  // фон меню
        g.setColor(Color.darkGray);
        g.fillRect(800,300,300,100);   // прямоугольник для надаиси
        Font f = new Font("Impact", Font.BOLD, 100);
        g.setFont(f);
        g.setColor(Color.lightGray);        //
        if(h==1){                           // изменение цфета надписи от координат курсора мыши
            g.setColor(Color.WHITE);        //
        }
        g.drawString("PLAY",850,385); // надпись
        g.setColor(Color.cyan);
        g.drawString("SPACE SHOOTER",600,150);
        g.drawImage(how,0,0,300,300,null);  // пояснения в управлении

    }
    else { // игры
        for (int i = 0; i <= 8; i++) {
            g.drawImage(imgfon, player.getmapX() + i * frame.getWidth(), 0, frame.getWidth(), frame.getHeight(), null);
        }  // рисуем фон от 0 до 8*frame.getWidth() по х
        g.setColor(Color.red);
        g.fillRect(300, 1020, 300 - 60 * n, 15);   //  полоска уровня жизни
        Font f1 = new Font("Impact", Font.BOLD, 50);
        g.setFont(f1);
        g.drawString(""+(bullets.length-1-c),100,1025);   // количество оставшихся пуль
        g.drawString(""+S,80,80);  // количество кораблей, которые могут пролететь за экран влево

        g.setColor(Color.YELLOW);
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < ships[i].shipbullets.length; j++) {
                if (ships[i].shipbullets[j].x > -90 ) {  // удаление с карты пуль корабля за экраном
                    if (j < ships[i].c && ships[i].shipbullets[j].b == true ) {
                            if (i>18) {   // отрисовка пуль корабля в зависимости от его номера
                                g.setColor(Color.green);
                                g.fillRect(ships[i].x, ships[i].y+45, ships[i].shipbullets[j].x-ships[i].x-5, 5);
                            }
                            else {
                                g.fillOval(ships[i].shipbullets[j].x, ships[i].shipbullets[j].y, 12, 12);
                            }
                        }

                    else {      // координаты невыпущенных пуль корабля равны коррдинатам "носа" корбля
                        ships[i].shipbullets[j].x = ships[i].x + 5;
                        ships[i].shipbullets[j].y = ships[i].y + 45;
                    }
                }
            }

                if (ships[i].b == true) {
                    if(i>18){      // отрисовка корябля в зависимости от его номера
                       g.drawImage(imgM2, ships[i].x, ships[i].y, 100, 100, null);
                    }
                    else {
                        g.drawImage(imgM1, ships[i].x, ships[i].y, 100, 100, null);
                    }
                }
            }



          g.setColor(Color.red);
        for (int i = 0; i < bullets.length; i++) {
            if(bullets[i].x<2000){  // удаление с карты пуль игрока за экраном
            if (i < c) {
                if (bullets[i].b == true) {   // отрисока пуль игрока
                    g.fillRect(bullets[i].x, bullets[i].y, 25, 5);
                }
            } else {      // координаты невыпущенных пуль корабля равны коррдинатам "носа" игрока
                bullets[i].x = player.getX() + 70;
                bullets[i].y = player.getY() + 45;
            }
        }

        }


        g.drawImage(r,player.getmapX() + 14000, 900, 200, 200,null);  // отрисовка флага
        g.drawImage(imgpl, player.getX(), player.getY(), 100, 100, null);    // отрисовка игрока

      }
    }

    @Override
    public void actionPerformed(ActionEvent e) {  // метод, который выполнятся каждые 8 милисекунды(delay(задержка) таймера)
            if(b == true && player.p % 2 == 0) { // если игра(не меню) и не пауза

                if (player.getX() - 50 >= player.getmapX() + 14000 || player.b == false || S == 0 ) {

                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException ex) { // замедление перед появлением меню посде конца игры
                        ex.printStackTrace();
                    }
                    replay(); // "перезапуск"

                }   // условия конца игры (координаты игрока рядом с финишем(флагом), игрок сбит, 3 несбитых корабля залетели за экран влево, закончились пули)

                if (ships[0] == null) {
                    for (int i = 0; i < ships.length; i++) {
                        ships[i] = new Ship(2000, (int) (Math.random() * 1000));
                        for (int j = 0; j < ships[i].shipbullets.length; j++) {
                            ships[i].shipbullets[j] = new Bullet(ships[i].x + 5, ships[i].y + 45, -8, 0);
                               if (i>18) {
                                 ships[i].shipbullets[j].dx=-16;
                            }
                        }
                    }
                }   // создание кораблей и пуль кораблей

                if (bullets[0] == null) {
                    for (int i = 0; i < bullets.length; i++) {
                        bullets[i] = new Bullet(player.getX() + 70, player.getY() + 50, 8, -333);
                        bullets[i].b=true;
                    }
                }   // создание пуль игрока
                if(bullets.length-1-c>0) {
                    c = player.g;   // количество выпущенных пуль игрока
                }
                if( -player.getmapX()<=7500) {
                    if(-player.getmapX() % 450 == 0) {
                        k++;
                    }
                }  // увеличение количества кораблей, появившихся на экране в зависимости от координат фона
                else {
                    if (-player.getmapX() <= 9800) {
                        if (-player.getmapX() % 350 == 0) {
                            k++;
                        }
                    } else {
                        if (-player.getmapX() <= 11700) {
                            if (-player.getmapX() % 325 == 0) {
                                k++;
                            }
                        }
                    }
                }     //  (при приближении к финишу это количество растёт быстрее)

                    if (-player.getmapX() % 200 == 0 ) {
                    for (int i = 0; i < k; i++) {
                        if (ships[i].b == true && i<=18) {
                            ships[i].shipbullets[ships[i].c].b=true;
                            ships[i].c++;
                        }
                    }
                } // количество выпущенных пуль корабля
                if (-player.getmapX() % 350 == 0 ) {
                    for (int i = 0; i < k; i++) {
                        if (ships[i].b == true && i>18) {
                            ships[i].shipbullets[ships[i].c].b=true;
                            ships[i].c++;
                        }
                    }
                }   // у некоторых кораблей оно изменяется медленнее

                player.mapmove();   // вызов перемещения фона
                player.move();      // вызов перемещения игрока

                if (c >0 && bullets[c-1].dy == -333) {
                    bullets[c-1].dy = player.t;   // изменение скорости пуль игрока в зависимости от направления игрока по вертикали
                }

                for (int i  = 0; i < c; i++) {
                    bullets[i].move();    //вызов перемещения пуль игрока
                }

                for (int i = 0; i < k; i++) {
                    for(int I = 0;I<c;I++ ){
                        if(ships[i].x-bullets[I].x<150 && Math.abs(ships[i].y+50 - bullets[I].y)<=70 && (i+1)%5==0 && i!=0){ // каждый 5 корабль будет уходть от пуль вверх или вниз
                          if(ships[i].y+50 - bullets[I].y>=0) {                          // в зависимости от разности координат корабля и пули игрока по вертикали
                              ships[i].dy = 3;
                          }
                          else{
                              ships[i].dy = -3;
                          }
                        }
                        else{
                            ships[i].dy=0;
                        }
                    }
                    ships[i].move();     //вызов перемещения корабля
                    for (int j = 0; j < ships[i].c; j++) {
                        ships[i].shipbullets[j].move();   //вызов перемещения пуль корабля
                    }
                }

                for (int i = 0; i < k; i++) { // сбивания корабля
                    for (int j = 0; j < c; j++) {
                        if (ships[i].distance(bullets[j].x, bullets[j].y) <= 60 && bullets[j].b == true && ships[i].b == true) {
                            bullets[j].b = false;
                            ships[i].b = false;
                            if (i>18){
                                for(int g = 0;g<ships[i].shipbullets.length;g++){
                                    ships[i].shipbullets[g].b=false;
                                }
                            }
                        }
                    }

                    if (ships[i].x < -110 ) { // обработка несбитых кораблей, которые залетели за экран влево
                        if(ships[i].b==true) {
                        S--;
                    }
                        ships[i].b = false;
                        for(int g = 0;g<ships[i].shipbullets.length;g++){
                            ships[i].shipbullets[g].b=false;
                        }

                    }
                }

                for (int i = 0; i < k; i++) {   // обработка попаданий в игрока
                    for (int j = 0; j < ships[i].c; j++) {
                        if (player.distance(ships[i].shipbullets[j].x, ships[i].shipbullets[j].y) <= 60 && ships[i].shipbullets[j].b == true ) {
                            ships[i].shipbullets[j].b = false;
                            n++;
                            System.out.println(i);
                        }
                    }
                }
                if (n == 25) {    // при 5-ом попадание игрок сбит
                    player.b = false;
                }
            }

            repaint();  // перерисовка
    }

}
