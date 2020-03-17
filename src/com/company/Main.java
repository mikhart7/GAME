package com.company;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JPanel implements ActionListener  {
   private Image imgfon = new ImageIcon("FON.png").getImage();
    private Image imgM = new ImageIcon("spaceShips2.png").getImage();
    private Image imgpl = new ImageIcon("spaceShips1.png").getImage();
    private Image f = new ImageIcon("finish-line.png").getImage();
    private Image m = new ImageIcon("menu.jpg").getImage();
    private Image r = new ImageIcon("rus.jpg").getImage();
    Timer timer = new Timer(8, this);
    Player player = new Player();
        Ship[] ships = new Ship[30] ;
        int k = 0;
        Bullet [] bullets = new Bullet[100];
        int c = 0;
        int n=0;
        boolean b = false;

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
        g.setColor(Color.WHITE);
        g.fillRect(800,250,300,100);
        Font f = new Font("Impact", Font.BOLD, 100);
        g.setFont(f);
        g.setColor(Color.MAGENTA);
        g.drawString("PLAY",850,330);

    }
    else {
        for (int i = 0; i < 6; i++) {
            g.drawImage(imgfon, player.getmapX() + i * frame.getWidth(), 0, frame.getWidth(), frame.getHeight(), null);
        }
        g.setColor(Color.red);
        g.fillRect(300, 1000, 300 - 60 * n, 20);
        //g.drawImage(f, player.getmapX() + 4 * frame.getWidth() - 2000, 700, 300, 300, null);

        if (-player.getmapX() % 500 == 0 && -player.getmapX()<=2*frame.getWidth()) {
            k++;
        }
        if(-player.getmapX() % 350 == 0 && -player.getmapX() > 2*frame.getWidth() && -player.getmapX() < 2.5*frame.getWidth()){
            k++;
        }
        if(-player.getmapX() % 250 == 0 && -player.getmapX() > 2.5*frame.getWidth() && -player.getmapX()<=3*frame.getWidth()+300){
            k++;
        }

        if (-player.getmapX() % 100 == 0 && -player.getmapX()<=3.5*frame.getWidth()) {

            c++;
            bullets[c].b=true;
        }


        if (-player.getmapX() % 150 == 0 ) {
            for (int i = 0; i <= k; i++) {
                if (ships[i].b == true) {

                    ships[i].c++;
                    ships[i].shipbulleys[ships[i].c].b=true;
            }

            }
        }


        g.setColor(Color.YELLOW);
        for (int i = 0; i <= k; i++) {
            for (int j = 0; j < ships[i].shipbulleys.length; j++) {
                if (ships[i].shipbulleys[j].x > -100) {
                    if (j <= ships[i].c ) {
                        if(ships[i].shipbulleys[j].b == true) {
                            g.fillOval(ships[i].shipbulleys[j].x, ships[i].shipbulleys[j].y, 10, 10);
                        }

                    }
                    else {
                        ships[i].shipbulleys[j].x = ships[i].x + 10;
                    }
                }

                if (ships[i].b == true) {
                    g.drawImage(imgM, ships[i].x, ships[i].y, 100, 100, null);
                }
            }
        }


        g.setColor(Color.red);
        for (int i = 0; i < bullets.length; i++) {
            if (bullets[i].x < 2000) {
                if (i <= c ) {
                    if(bullets[i].b==true) {
                        g.fillOval(bullets[i].x, bullets[i].y, 10, 10);
                    }
                } else {
                    bullets[i].x = player.getX() + 70;
                    bullets[i].y = player.getY() + 45;
                }
            }

        }

        g.drawImage(r,player.getmapX() + 4 * frame.getWidth()+500, 900, 200, 200,null);
        g.drawImage(imgpl, player.getX(), player.getY(), 100, 100, null);

      }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (b == true) {
            if(player.g % 2 == 0) {
                player.mapmove();
                player.move();
                if (ships[0] == null) {
                    for (int i = 0; i < ships.length; i++) {
                        ships[i] = new Ship(2000, (int) (Math.random() * 1000));
                        for (int j = 0; j < ships[i].shipbulleys.length; j++) {
                            ships[i].shipbulleys[j] = new Bullet(ships[i].x + 10, ships[i].y + 45, -7, 0);
                        }
                    }
                }

                if (bullets[0] == null) {
                    for (int i = 0; i < bullets.length; i++) {
                        bullets[i] = new Bullet(player.getX() + 70, player.getY() + 45, 7, -333);
                    }
                }
                if(c==0) {
                    bullets[c].b = true;
                }
                if (bullets[c].dy == -333) {
                    bullets[c].dy = player.t;
                }

                for (int i = 0; i <= c; i++) {
                    bullets[i].move();
                }
                for (int i = 0; i <= k; i++) {
                    ships[i].move();
                    for (int j = 0; j <= ships[i].c; j++) {
                        ships[i].shipbulleys[j].move();
                    }

                }

                for (int i = 0; i <= k; i++) {
                    for (int j = 0; j <= c; j++) {
                        if (ships[i].distance(bullets[j].x, bullets[j].y) <= 50 && bullets[j].b==true && ships[i].b==true) {
                            bullets[j].b=false;
                            ships[i].b = false;

                        }
                    }



                    if (ships[i].x < -110 && ships[i].b == true) {
                        //   timer.stop();
                    }
                }
                System.out.println(n);
                if (n == 5) {
                   //  player.b = false;
                }

                for (int i = 0; i <= k; i++) {
                    for (int j = 0; j <= ships[i].c; j++) {
                        if (player.distance(ships[i].shipbulleys[j].x, ships[i].shipbulleys[j].y) <= 50 && ships[i].shipbulleys[j].b == true) {
                            ships[i].shipbulleys[j].b = false;
                            n++;
                        }
                    }

                }


                if (player.getX()-50 >= player.getmapX() + 4 * frame.getWidth()+500 || player.b == false) {
                    timer.stop();
                }
            }

            repaint();
        }
    }

}
