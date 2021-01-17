package upjv.simulationfeu.objets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import upjv.simulationfeu.Entity;

public class Cendre extends Entity{

	

	protected int durreVie;
	protected int vitesse_disparition = 5;

     public Cendre(int x,int y,int dureeVie) {
    	 this.box = new Rectangle(x,y,4,4);
    	 this.durreVie = dureeVie;
    	 this.vitesse = 2;
    	 this.color = Color.DARK_GRAY;
    	 this.angle = random.nextInt(360);
     }

     public Cendre(int x,int y) {
    	 this.box = new Rectangle(x,y,4,4);
    	 this.durreVie = 5;
    	 this.vitesse = 2;
    	 this.random = new Random();
    	 this.color = new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
    	 this.angle = random.nextInt(360);
     }
     
     public void updatePosition() {
    	 if(temps < 7500) temps++; else temps = 0;
    	 xDir  =(int) (this.vitesse * Math.cos(Math.toRadians(angle)));
    	 yDir = (int) (this.vitesse * Math.sin(Math.toRadians(angle)));
    	 this.box.x +=xDir;
    	 this.box.y +=yDir;
    	 if(temps%vitesse_disparition==0) {
    		  this.durreVie--;
    		  if(durreVie<=0) {
    			   durreVie = 0;
    			  remove();
    		  }
    	 }
     }
     
     public void afficheCendre(Graphics g) {
    	 g.setColor(color);
    	 g.fillRect(box.x, box.y, box.width, box.height);
     }
     
}
