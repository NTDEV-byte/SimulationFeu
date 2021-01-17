package upjv.simulationfeu.objets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import upjv.simulationfeu.Entity;
import upjv.simulationfeu.Game;

public class Feu extends Entity{
	
		/*
		 * 
		 * 
		 */
		protected int rayon_detection = 200;
		protected int vitesse_propagation = 50;
		protected int resistance_eau;
		protected int periode_cycle = 400;
		protected int cycle;
		protected int cycle_max = 4 ;
	
	    
		public Feu(int x,int y) {
		   this.box = new Rectangle(x,y,4,4);
		   this.color = Color.red;
		   this.vitesse = 2;
           this.random = new Random();
           this.angle = random.nextInt(360);
           this.resistance_eau = 10;
           this.cycle = 0;
	   }
	   
	   public Feu(int x,int y,int w,int h) {
		   this.box = new Rectangle(x,y,w,h);
		   this.color = Color.red;
		   this.vitesse = 2;
           this.random = new Random();
           this.angle =random.nextInt(360);
           this.resistance_eau = 10;
           this.cycle = 0;
	   }
	   
	   public void afficheFeu(Graphics g) {
		   g.setColor(color);
		   g.fillOval(box.x, box.y, box.width, box.height);
	   }
	   
	   public void augmenteTailleFeuEtResistance(int value) {
		   if(cycle < cycle_max) {
			   this.box.width+=this.box.getWidth()+value;
			   this.box.height+=this.box.getHeight()+value;
			   this.resistance_eau+=10;
			   cycle++;
		   }
	   }
	   
	   public void updatePosition() {
		 if(temps<=periode_cycle){temps++;}
		 else {temps = 0; augmenteTailleFeuEtResistance(1);}
		 if(temps%vitesse_propagation==0) {
		    	xDir = (int)(this.vitesse * Math.cos(Math.toRadians(angle)));
		    	yDir = (int)(this.vitesse * Math.sin(Math.toRadians(angle)));
		    	this.box.x+=xDir;
			    this.box.y+=yDir;
			   // this.vitesse = 2;
		 }
		 if(box.x <= 0 )box.x=box.width;
		 if(box.x >= Game.getLargeur())box.x=box.width-10;
		 if(box.y <= 0 ) box.y=box.height;
		 if(box.y >= Game.getLargeur()) box.y=box.height-10;
		    verifieSifeuEteint();
	   }
	   
	   public void verifieSifeuEteint() {
		    if(this.resistance_eau<=0) {
		    	  this.resistance_eau = 0;
		    	  remove();
		    }
	   }
	   
	    public void diminueResistanceFeu(int valeur) {this.resistance_eau-=valeur;
	    if(this.box.width > 4 && this.box.height > 4) {
	        this.box.width-=1;this.box.height-=1;
	    }
	    }
	    
	    public int getResistanceEau() {return this.resistance_eau;}
		public int getRayonDetection() {return this.rayon_detection;}
		public void setResistanceEau(int valeur) {this.resistance_eau = valeur;}
		public void setRayonDetection(int valeur) {this.rayon_detection = valeur;}
}
