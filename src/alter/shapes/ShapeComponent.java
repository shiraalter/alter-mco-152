package alter.shapes;

import javax.swing.*;
import java.awt.*;

public class ShapeComponent extends JComponent {
  //draw calls only happen in painComponent and with Graphics obj
  private String shape = "";
  private int degrees  = 0;


  public ShapeComponent() {
    Thread thread = new Thread(){
      @Override
      public void run(){
        super.run();
        while(true){
          repaint();
          try {
            Thread.sleep(20);
          }
          catch(InterruptedException e){
            e.printStackTrace();
          }
        }
      }
    };
    thread.start();
  }
////////

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    int width = getWidth();
    int height = getHeight();
    int centerX = width / 2;
    int centerY = height / 2;
    g.translate(centerX, centerY);


    Graphics2D image = (Graphics2D)g;
    image.rotate(Math.toRadians(degrees));
    degrees++;

    switch(shape) {
      case("Hamburger"):
        drawHamburger(g);
        break;
      case("Cookie"):
        drawCookie(g);
        break;
      case("Juice"):
        drawJuice(g);
        break;
      case("HotDog"):
        drawHotDog(g);
        break;

    }

  }

  private void drawHotDog(Graphics g) {
    g.setColor(new Color (73,42, 28));
    g.fillRoundRect(-60,-105,20,150,30,30); //hot dog

    g.setColor(new Color(157, 146, 86));
    g.fillRoundRect(-40,-90,30,120,25,25);  //buns
    g.fillRoundRect(-90,-90,30,120,25,25);

    g.setColor(Color.RED);
    g.drawLine(-57, -80, -53,0);
    g.setColor(Color.YELLOW);
    g.drawLine(-45,-80,-47,0);




  }

  private void drawJuice(Graphics g) {
    g.setColor(new Color(51, 116, 200));
    g.fillRoundRect(-70,-80,100,150,10,10);
    g.setColor(new Color(162, 41, 120));
    g.fillRect(0,-125,10,45);
    g.fillRect(5,-125, 20,10);  //how would i angle this?

    g.setColor(new Color(10, 10, 34));
    g.drawString("Apple Juice", -50, -30 );
  }

  private void drawCookie(Graphics g) {
    g.setColor(new Color(0xB37D4E));
    g.fillOval(-100,-85,150,150);

    g.setColor(new Color(43, 21, 12));
    g.fillRoundRect(-50,-30,12,10,10,10);
    g.fillRoundRect(-70,-10,12,10,10,10);
    g.fillRoundRect(-67,10,12,10,10,10);
    g.fillRoundRect(-30,40,10,10,10,10);
    g.fillRoundRect(-15,-49,12,10,10,10);
    g.fillRoundRect(-20,10,12,10,10,10);
    g.fillRoundRect(-57,-45,12,10,10,10);
    g.fillRoundRect(-5,-20,12,10,10,10);
    g.fillRoundRect(10,-15,15,8,5,5);
    g.fillRoundRect(-27,-10,15,8,5,5);
    g.fillRoundRect(2,30,15,8,5,5);
    g.fillRoundRect(-45,25,15,8,5,5);
    g.fillRoundRect(-45,-55,15,8,5,5);

  }

  private void drawHamburger(Graphics g) {
    g.setColor(new Color(229, 172, 90)); //top bun
    g.fillArc(-40,-45,90,70,0,180);

    g.setColor(new Color(0xC82217));
    g.fillOval(-34, -11,80,10); //tomato

    g.setColor((new Color(77, 55, 31))); //burger
    g.fillRoundRect(-34,0,80,10,10,10);

    g.setColor(new Color(0x47C815));
    g.fillOval(-34, 10,80,10); //lettuce

    g.setColor(new Color(229, 172, 90)); //bottom bun
    g.fillArc(-40,-14,90,70,0,-180);
  }


  public void setShape(String shape){
      this.shape = shape;
      repaint();
    }



  }



