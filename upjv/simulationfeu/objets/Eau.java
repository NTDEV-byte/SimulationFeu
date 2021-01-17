package upjv.simulationfeu.objets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import upjv.simulationfeu.Entity;
import upjv.simulationfeu.Game;

public class Eau extends Entity{
	
		protected int resistance_au_feu;
		protected int xOrigine,yOrigine;
		protected int portee;

		public Eau(int x,int y,double angle) {
		   this.box = new Rectangle(x,y,7,7);
		   this.xOrigine = x;
		   this.yOrigine = y;
		   this.color = Color.blue;
		   this.vitesse = 3;
		   this.resistance_au_feu = 1;
		   this.portee = 150;
		   this.angle = angle;
	   }
		
	   public Eau(int x,int y) {
		   this.box = new Rectangle(x,y,7,7);
		   this.xOrigine = x;
		   this.yOrigine = y;
		   this.color = Color.blue;
		   this.vitesse = 2;
		   this.resistance_au_feu = 1;
		   this.portee = 150;
	   }

	   public void afficheEau(Graphics g) {
		   g.setColor(color);
		   g.fillOval(this.box.x, this.box.y,this.box.width, this.box.height);
	   }
	   
	   
	   public void updatePosition() {
		   if(temps<7500) temps++;else temps = 0;
			   xDir = (int) (vitesse * Math.cos(this.angle));
			   yDir = (int) (vitesse * Math.sin(this.angle));
			   this.box.x+=xDir;
			   this.box.y+=yDir;
			   if(distanceParcouru()>portee) {
				   remove();
		   }
				 if(box.x < 0 ) box.x = 620;
				 if(box.x > Game.getLargeur()) box.x = 10;
				 if(box.y < 0 ) box.y = Game.getHauteur();
				 if(box.y > Game.getLargeur()) box.y = 10;
		 
		   eauVaporiser();
	   }
	   
	   
	   public double distanceParcouru() {
		    double dx = this.box.x - xOrigine;
		    double dy = this.box.y - yOrigine;
		    return Math.sqrt(dx * dx + dy * dy);
	   }
	   
	 public void diminueResistanceEau(int valeur) {this.resistance_au_feu-=valeur;}
	 public void eauVaporiser() {
		   if(this.resistance_au_feu <= 0) {
			     this.resistance_au_feu = 0;
			     remove();
		   }
	   }
	   

}
