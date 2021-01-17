package upjv.simulationfeu;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;

public abstract class Entity {
	
		/*
		 *Super Classe dont tout les objets de simulationfeu hérite(tout est entité)
		 *
		 * 
		 */
	
		
	
		protected Terrain level = Terrain.instance;
		protected Rectangle box;
		protected int xDir,yDir;
		protected double angle;
		protected int vitesse;
		protected Color color;
		protected boolean removed;
		protected Random random;
		protected int temps;
		
	
		public Terrain getLevel() {return level;}
		public Rectangle getBox() {return box;}
		public int getX() {return box.x;}
		public int getY() {return box.y;}
		public int getXDir() {return xDir;}
		public int getYDir() {return yDir;}
		public double getAngle() {return angle;}
		public int getVitesse() {return vitesse;}
		public Color getColor() {return color;}
		public boolean isRemoved() {return removed;}
		public void remove() {this.removed = true;}
		
		public void setLevel(Terrain level) {this.level = level;}
		public void setX(int x) {this.box.x = x;}
		public void setY(int y) {this.box.y = y;}
		public void setXdir(int xdir) {this.xDir = xdir;}
		public void setYdir(int ydir) {this.yDir = ydir;}
		public void setAngle(double angle) {this.angle = angle;}
		public void setVitesse(int vitesse) {this.vitesse = vitesse;}
		public void setColor(Color color) {this.color = color;}
		public void setRemoveValue(boolean value) {this.removed = value;}
		

}
