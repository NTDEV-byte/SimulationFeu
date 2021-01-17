package upjv.simulationfeu.objets.arbres;

import java.awt.Color;

import upjv.simulationfeu.objets.Arbre;

public class Savonnier extends Arbre{

	public Savonnier(int x, int y) {
		super(x, y,new Color(235,217,23));
		this.inflammabilite = 6;
		this.resistance = 1;
	}
	
	public Savonnier(int x, int y,int w,int h) {
		super(x, y,w,h,new Color(235,217,23));
		this.inflammabilite = 6;
		this.resistance = 1;
	}
	
}
