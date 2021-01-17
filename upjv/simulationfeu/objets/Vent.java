package upjv.simulationfeu.objets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import upjv.simulationfeu.Entity;
import upjv.simulationfeu.Game;

public class Vent extends Entity{
	 
	 	protected int longueur_segment = 20;
	    
	 	public Vent(int x,int y,double direction) {
		     this.box = new Rectangle(x,y,80,30);
		     this.angle = direction;
		     this.vitesse = 8;
		     this.color = Color.white; 
	   }

	   public void updatePosition() {
		   if(temps<7500) temps++;else temps=0;
			   xDir =(int) (vitesse * Math.cos(Math.toRadians(angle)));
			   yDir =(int) (vitesse * Math.sin(Math.toRadians(angle)));
			   box.x+=xDir;
			   box.y+=yDir;
			   if(box.x > Game.getLargeur()) remove();
			   if(box.x < 0) remove();
			   if(box.y < 0) remove();
			   if(box.y > Game.getHauteur()) remove();
			   
	   }
	   
	   public void afficheVent(Graphics g) {
		   g.setColor(color);
		   g.drawLine(box.x, box.y, box.x + xDir * longueur_segment,box.y + yDir * longueur_segment);
	   }
	   
	   
	 
	   
}
