package upjv.simulationfeu.objets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import upjv.simulationfeu.Entity;

public class Arbre extends Entity{
	
	
	 /**(r  ,g , b) | degré d'inflammabilité Résistance |type d'arbres
	  * 235, 217, 23| 		   6 		   | 	 1		|Savonnier
	  * 194,33,143  | 		   5 		   | 	 2		|Asiminier
	  * 186, 32, 70 | 		   4 		   | 	 3		|Acerpalmatum
	  * 32, 186, 91 | 		   3 		   | 	 4		|Saratoga
	  * 76, 186, 32 | 		   2 		   | 	 5      |Kaktuse
	  * 23, 235, 193| 		   1 		   | 	 6		|chêne 
		*/

		protected int inflammabilite;
		protected int resistance;
		protected boolean bruler;
		
		public Arbre(int x,int y,Color c) {
			this.box = new Rectangle(x,y,8,8);
			this.color = c;
			this.resistance = 1;
			this.inflammabilite = 1;
			bruler = false;
		}
		
		public Arbre(int x,int y,int w,int h,Color c) {
			this.box = new Rectangle(x,y,w,h);
			this.color = c;
			this.resistance = 1;
			this.inflammabilite = 1;
			bruler = false;
		}
		
		public void afficheArbre(Graphics g) {
			if(removed) {
				g.setColor(Color.DARK_GRAY);
			    if(!bruler) {
			    	this.box.width+=5;
					this.box.height+=5;
					bruler = true;
			    }
			    g.fillOval(box.x,box.y,box.width,box.height);
					}	
			
			else {
				g.setColor(color);
				g.fillOval(box.x, box.y,box.width, box.height);
			}
		}
		
		public void diminueResistanceArbre(int valeur) {
			 this.resistance-=valeur; 
		}
		
		public void removeArbre() {
			if(this.resistance<=0) {
				this.resistance = 0;
				 remove();
			}
		}
		
		public int getInflammabilite() {return inflammabilite;}
		public int getResistance() {return resistance;}
		public boolean estBruler() {return bruler;}
	   
		
		
		
		
}
