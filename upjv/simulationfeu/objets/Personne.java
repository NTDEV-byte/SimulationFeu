package upjv.simulationfeu.objets;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import upjv.simulationfeu.Entity;

public class Personne extends Entity{
	
	protected int resistance;
	protected int vitesse_changement_direction = 100;
	protected int distance_AlAide=50;
	
	public Personne(int x,int y,Color c) {
		this.box = new Rectangle(x,y,6,6);
		this.color = c;
		this.vitesse = 3;
		this.random = new Random();
		this.resistance = 1;
		this.angle = random.nextInt(360);
	}
	
	public Personne(int x,int y) {
		this.box = new Rectangle(x,y,8,8);
		this.color = Color.magenta;
		this.vitesse = 3;
		this.resistance = 1;
		this.random = new Random();
		this.angle = random.nextInt(360);
	}

	
	public void updatePosition() {
			xDir = (int) (vitesse * Math.cos(Math.toRadians(angle)));
			yDir = (int) (vitesse * Math.sin(Math.toRadians(angle)));
			this.box.x+=xDir;
			this.box.y+=yDir;
	}
	
	public void affichePersonne(Graphics g) {
		g.setColor(color);
		g.fillRect(box.x, box.y, box.width, box.height);
		g.setColor(Color.black);
		g.fillRect(box.x+(box.width/2)-2, box.y+(box.height/2), box.width / 2, box.height/2);
		Entity feu = level.feuPlusProche(this);
		if(feu!=null) {
			if(level.getDistance(feu, this) < distance_AlAide) {
				aidezMoi(g);
			}
		}
		
	}
	
	
	public void aidezMoi(Graphics g) {
		g.setColor(color);
		g.setFont(new Font("Verdana",Font.ROMAN_BASELINE,15));
		g.drawString("aidezMoi !!!", box.x, box.y);
		
	}
	
	public void diminueResistance(int valeur) {
		 this.resistance-=valeur;
	}
	
	public void verifieMort() {
		 if(this.resistance<=0) {
			  this.resistance = 0;
			  remove();
			  level.setScore(50);
		 }
	}
	

	public int getResistance() {return this.resistance;}
	public int getVitesseChangementDirection() {return this.vitesse_changement_direction;}
	
	public void setResistance(int valeur) {this.resistance  = valeur;}
	public void setVitesseChangementDirection(int valeur) {this.vitesse_changement_direction = valeur;}
	   
}
