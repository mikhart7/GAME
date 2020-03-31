package com.company;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JPanel implements ActionListener  {
   private Image imgfon = new ImageIcon("FON.png").getImage();
    private Image imgM1 = new ImageIcon("spaceShips2.png").getImage();
    private Image imgM2 = new ImageIcon("spaceShips2.2.png").getImage();
    private Image imgpl = new ImageIcon("spaceShips1.png").getImage();
    private Image m = new ImageIcon("menu.jpg").getImage();
    private Image r = new ImageIcon("rus.jpg").getImage();
    private Image how = new ImageIcon("how.png").getImage();
    Timer timer = new Timer(8, this);
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
        timer.restart();
    }


    Player player = new Player();
        Ship[] ships = new Ship[30] ;
        int k = 0;
        Bullet [] bullets = new Bullet[36];
        int c = 0;
        int n=0;
        boolean b = false;
        int S = 3;
        int h =0;


    MouseMotionListener mouse2 = new MouseMotionListener() {
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if(x>=800 && x<=1100 && y>=250 && y<=350) {
            h=1;
        }
        else{
            h=0;
        }
    }

};

MouseListener mouse = new MouseListener() {
    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if(x>=800 && x<=1100 && y>=250 && y<=350) {
            b = true;

        }

    }

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

    }

};


    JFrame frame;
    public Main(JFrame frame) {
    addMouseListener(mouse);
    addMouseMotionListener(mouse2);
        this.frame = frame;
        timer.start();
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                player.keyPressed(e);

            }

            @Override
            public void keyReleased(KeyEvent e){
                player.keyReleased(e);
            }
        });

    }

    public void paint(Graphics g) {
    if(b==false){
        g.drawImage(m,0,0,1920,1080,null);
        g.setColor(Color.darkGray);
        g.fillRect(800,250,300,100);
        Font f = new Font("Impact", Font.BOLD, 100);
        g.setFont(f);
        g.setColor(Color.lightGray);
        if(h==1){
            g.setColor(Color.WHITE);
        }
        g.drawString("PLAY",850,335);
        g.drawImage(how,0,0,300,300,null);

    }
    else {
        for (int i = 0; i <= 8; i++) {
            g.drawImage(imgfon, player.getmapX() + i * frame.getWidth(), 0, frame.getWidth(), frame.getHeight(), null);
        }
        g.setColor(Color.red);
        g.fillRect(300, 1020, 300 - 60 * n, 15);

        Font f1 = new Font("Impact", Font.BOLD, 50);
        g.setFont(f1);
        g.drawString(""+(bullets.length-1-player.g),100,1025);
        g.drawString(""+S,80,80);


        g.setColor(Color.YELLOW);
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < ships[i].shipbullets.length; j++) {
                if (ships[i].shipbullets[j].x > -100) {
                    if (j < ships[i].c) {
                        if (ships[i].shipbullets[j].b == true) {
                            if (i>18) {
                                g.setColor(Color.green);
                                g.fillRect(ships[i].x, ships[i].y+50, ships[i].shipbullets[j].x-ships[i].x-5, 5);
                            }
                            else {
                                g.fillOval(ships[i].shipbullets[j].x, ships[i].shipbullets[j].y, 12, 12);
                            }
                        }
                    }
                    else {
                        ships[i].shipbullets[j].x = ships[i].x + 5;
                        ships[i].shipbullets[j].y = ships[i].y + 50;
                    }
                }
            }

                if (ships[i].b == true) {
                    if(i>18){
                       g.drawImage(imgM2, ships[i].x, ships[i].y, 100, 100, null);
                    }
                    else {
                            g.drawImage(imgM1, ships[i].x, ships[i].y, 100, 100, null);
                    }
                }
            }



          g.setColor(Color.red);
        for (int i = 0; i < bullets.length; i++) {
                if (i < c) {
                    if (bullets[i].b == true) {
                        g.fillRect(bullets[i].x, bullets[i].y, 20, 10);
                    }
                } else {
                    bullets[i].x = player.getX() + 80;
                    bullets[i].y = player.getY() + 45;
                }


        };


        g.drawImage(r,player.getmapX() + 14000, 900, 200, 200,null);
        g.drawImage(imgpl, player.getX(), player.getY(), 100, 100, null);

      }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            if(b == true && player.p % 2 == 0) {

                if (player.getX() - 50 >= player.getmapX() + 14000 || player.b == false || S == 0 || bullets.length-1-c==0) {

                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    replay();

                }

                if (ships[0] == null) {
                    for (int i = 0; i < ships.length; i++) {
                        ships[i] = new Ship(2000, (int) (Math.random() * 1000));
                        for (int j = 0; j < ships[i].shipbullets.length; j++) {
                            ships[i].shipbullets[j] = new Bullet(ships[i].x + 5, ships[i].y + 50, -8, 0);
                               if (i>18) {
                                 ships[i].shipbullets[j].dx=-10;
                            }
                        }
                    }
                }

                if (bullets[0] == null) {
                    for (int i = 0; i < bullets.length; i++) {
                        bullets[i] = new Bullet(player.getX() + 80, player.getY() + 50, 8, -333);
                        bullets[i].b=true;
                    }
                }

                c = player.g;

                if( -player.getmapX()<=7500) {
                    if(-player.getmapX() % 450 == 0) {
                        k++;
                    }
                }
                else
                      if(-player.getmapX()<=9800){
                         if(-player.getmapX() % 350 == 0){
                             k++;
                         }
                       }
                      else {
                          if (-player.getmapX() <= 11700) {
                              if (-player.getmapX() % 325 == 0) {
                                  k++;
                              }
                          }
                      }

                    if (-player.getmapX() % 150 == 0 ) {
                    for (int i = 0; i < k; i++) {
                        if (ships[i].b == true ) {
                            ships[i].shipbullets[ships[i].c].b=true;
                            ships[i].c++;

                        }
                    }
                }
                if (-player.getmapX() % 300 == 0 ) {
                    for (int i = 0; i < k; i++) {
                        if (ships[i].b == true && i>18) {
                            ships[i].shipbullets[ships[i].c].b=true;
                            ships[i].c++;
                        }
                    }
                }
                player.mapmove();
                player.move();

                if (c >0 && bullets[c-1].dy == -333) {
                    bullets[c-1].dy = player.t;
                }

                for (int i  = 0; i < c; i++) {
                    bullets[i].move();
                }

                for (int i = 0; i < k; i++) {
                    for(int I = 0;I<c;I++ ){
                        if(ships[i].x-bullets[I].x<200 && Math.abs(ships[i].y+50 - bullets[I].y)<=70 && i%2==0){
                          if(ships[i].y+50 - bullets[I].y>=0) {
                              ships[i].dy = 4;
                          }
                          else{
                              ships[i].dy = -4;
                          }
                        }
                        else{
                            ships[i].dy=0;
                        }
                    }
                    ships[i].move();
                    for (int j = 0; j < ships[i].c; j++) {
                        ships[i].shipbullets[j].move();
                    }
                }

                for (int i = 0; i < k; i++) {
                    for (int j = 0; j < c; j++) {
                        if (ships[i].distance(bullets[j].x, bullets[j].y) <= 60 && bullets[j].b == true && ships[i].b == true) {
                            bullets[j].b = false;
                            ships[i].b = false;
                        }
                    }


                    if (ships[i].x < -110 && ships[i].b == true) {
                        ships[i].b = false;
                        for(int g = 0;g<ships[i].shipbullets.length;g++){
                            ships[i].shipbullets[g].b=false;
                        }
                        S--;
                    }
                }

                for (int i = 0; i < k; i++) {
                    for (int j = 0; j < ships[i].c; j++) {
                        if (player.distance(ships[i].shipbullets[j].x, ships[i].shipbullets[j].y) <= 60 && ships[i].shipbullets[j].b == true ) {
                            ships[i].shipbullets[j].b = false;
                            n++;
                        }
                    }
                }
                if (n == 5) {
                    player.b = false;
                }
            }

            repaint();
    }

}
