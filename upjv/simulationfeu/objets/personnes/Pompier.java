package upjv.simulationfeu.objets.personnes;

import java.awt.Color;

import upjv.simulationfeu.Generateur;
import upjv.simulationfeu.Generateur.Type_Eau;
import upjv.simulationfeu.objets.Feu;
import upjv.simulationfeu.objets.Personne;

public class Pompier extends Personne{
	
		protected int timer,rate;
		protected int reserve_eau;
		protected int vitesse_de_tire = 60;
		protected boolean reserve_vide;
		

		public Pompier(int x,int y,Color c) {
			super(x,y,c);
			this.reserve_eau = 5;
			this.reserve_vide = false;
			this.vitesse=5;
			
		}
		
		public Pompier(int x,int y) {
			super(x,y);
			this.reserve_eau = 5;
			this.reserve_vide = false;
			this.color = new Color(255,255,0);
			this.vitesse=5;
		}
		
		public void updatePosition() {
			if(timer<7500) timer++;else timer = 0;
			if(timer%50==0) {
				this.angle = random.nextInt(360);
				xDir = (int) (vitesse * Math.cos(Math.toRadians(angle)));
				yDir = (int) (vitesse * Math.sin(Math.toRadians(angle)));
				this.box.x+=xDir;
				this.box.y+=yDir;
			}
			 Feu fpp = level.feuPlusProche(this);
			 if(fpp!=null) {
				 if(level.getDistance(this,fpp) <= fpp.getRayonDetection()) { 
					 eteintFeu(fpp);
				 }
				 
			 }
			 verifieMort();
		}

		public void eteintFeu(Feu feu) {
			 int fx = feu.getX();
			 int fy = feu.getY();
			 double dx =  fx - this.box.x;
			 double dy =  fy - this.box.y;
			 double angle = Math.atan2(dy, dx); // calcul de l'angle entre le feu le plus proche et le pompier 
				if(timer%100==0) {
					 this.box.x += (int)(this.vitesse * Math.cos(angle));
					 this.box.y += (int)(this.vitesse * Math.sin(angle));
				 }
				 if(level.getDistance(this,feu)<feu.getRayonDetection() && !reserve_vide) { 
					 new Generateur(this.reserve_eau,this.box.x,this.box.y,angle,Type_Eau.EAU);
					 reserve_vide = true;
				 }
				 if(timer%vitesse_de_tire==0) {
					 reserve_vide = false;
				 }
		 }

		
		
		
		
	
}
