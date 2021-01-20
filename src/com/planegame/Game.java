package com.planegame;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game extends JPanel implements  MouseListener, ActionListener {

    private int timePassed = 0;
    private int planeNumber = 0;
    private int totalScore = 0;
    private int missedPlane = 0;

    private BufferedImage imageWeapon;
    private BufferedImage imagePlane;

    Timer timer = new Timer(15,this);

    private ArrayList<Fire> fires = new ArrayList<>();
    private ArrayList<Plane> planes = new ArrayList<>();

    public Game() {

        try{
            imagePlane = ImageIO.read(new FileImageInputStream(new File("plane.png")));
            imageWeapon = ImageIO.read(new FileImageInputStream(new File("patriot.png")));
        }catch (IOException ex){
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }

        setBackground(Color.cyan);

        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        timePassed += 15;

        for(Plane plane : planes) {     // check plane missed
            if(plane.getX() >= 760) {
                planes.remove(plane);
                missedPlane += 1;
                planeNumber -= 1;
            }
        }

        for(Plane plane: planes){
            g.drawImage(imagePlane,plane.getX(),plane.getY(),imagePlane.getWidth()/5,imagePlane.getHeight()/5,this);
        }

        g.drawImage(imageWeapon,360,500,imageWeapon.getWidth(),imageWeapon.getHeight(),this);

        for(Fire fire : fires){         // check fire leave the screen
            if(fire.getY() < 5 || fire.getX() < 0 || fire.getX() > 795){
                fires.remove(fire);
            }
        }

        g.setColor(Color.BLACK);

        for(Fire fire : fires){
            g.fillOval((int)(fire.getX()),(int)(fire.getY()),10,10);
        }

        checkShot();        // check planes are shoted

        g.drawString("Score: " + totalScore , 700 ,550);

        if(missedPlane >3){         // check are there 3 or more missed plane
            timer.stop();
            String message = "GAME OVER" +
                             "\nSCORE: " + totalScore;
            JOptionPane.showMessageDialog(this,message);
        }
    }

    @Override
    public void repaint() {
        super.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) { // Every 15 msec this function used cauesd of Timer

        for(Fire fire : fires){                     // move the fires
            fire.setY((int)(fire.getY() - fire.getyIncrease()));
            fire.setX((int)(fire.getX() + fire.getxIncrease()));
        }

        if(planeNumber < 11){           // check are there 10 ore more plane unless send new plane every 0,9 second
            if(timePassed % 900 == 0){
                planes.add(new Plane(0,randomHeight()));
            }
        }

        for(Plane plane : planes) {
            plane.setX(plane.getX() + 2);
        }

        repaint();
    }


    public void checkShot() {               //checking score
        for(Fire fire : fires){
            for(Plane plane : planes){
                if(new Rectangle((int)(fire.getX()),(int)(fire.getY()),10,10).intersects(plane.getX(),plane.getY(),95,20)){
                    totalScore += 1;
                    planeNumber -= 1;
                    planes.remove(plane);
                    fires.remove(fire);
                }
            }
        }
    }

    public int randomHeight() {         // to identify height of plane that will be sent randomly
        Random rand = new Random();
        int random = rand.nextInt(201);

        return random;
    }


    @Override
    public void mousePressed(MouseEvent e) {        // to determine the fires direction
        double x = e.getX();
        double y = e.getY();

        double xInc = 0;
        double yInc = 10;

        if(x==400){
            xInc = 0;
            yInc = 8;
        }
        else if(x<400){
            x = 400 -x;
            y = 500 - y;
            double hip = Math.sqrt((x*2) + (y*y));
            double sin = y/hip;
            double cos = x/hip;
            xInc = -  (cos*8);
            yInc =  (sin*8);
        }
        else if(x>400){
            x= x - 400;
            y = 500-y;
            double hip = Math.sqrt((x*x) + (y*y));
            double sin = y/hip;
            double cos = x/hip;
            xInc =  (cos*8);
            yInc =  (sin*8);
        }
        fires.add(new Fire(400,500, xInc, yInc));

    }

    @Override
    public void mouseClicked(MouseEvent e) {

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
}
