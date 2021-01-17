package upjv.simulationfeu.objets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import upjv.simulationfeu.Entity;
import upjv.simulationfeu.Game;

public class Pluie extends Entity {
	
	
	  private int taille_goute = 3;
	  private int resistance;
	
	  public Pluie(int x,int y) {
		  this.box = new Rectangle(x,y,10,10);
		  this.color = Color.blue;
		  this.random = new Random();
		  this.angle = 90+random.nextInt(20) - 20 ;
		  this.vitesse = 10;
		  this.resistance = 1;
	  }
	
	  
	   public void updatePosition() {
		   if(temps<7500) temps++;else temps=0;
			   xDir =(int) (vitesse * Math.cos(Math.toRadians(angle)));
			   yDir =(int) (vitesse * Math.sin(Math.toRadians(angle)));
			   box.x+=xDir;
			   box.y+=yDir;
			   if(box.y > Game.getHauteur() - 20) remove();
			   if(resistance <= 0) {resistance=0;remove();}
	   }
	   
	   
	   public void affichePluie(Graphics g) {
		   g.setColor(color);
		   g.drawLine(box.x, box.y, box.x + xDir * taille_goute,box.y + yDir * taille_goute);
	   }
	   
	   
	   public void afficheVentBoxe(Graphics g) {
		  g.setColor(color);
		  g.drawRect(box.x, box.y, box.width, box.height);
	   }
	   
	   
	   
	   public void diminueResistancePluie(int valeur) {this.resistance-=valeur;}
	   public void setResistance(int valeur) {
		   this.resistance=valeur;
	   }
	
}
