package upjv.simulationfeu.objets.arbres;

import java.awt.Color;

import upjv.simulationfeu.objets.Arbre;

public class Chene extends Arbre{

	public Chene(int x, int y) {
		super(x, y,new Color(23,235,193));
		this.inflammabilite = 1;
		this.resistance = 6;
	}
	
	
	public Chene(int x, int y,int w,int h) {
		super(x, y,w,h,new Color(194,33,143));
		this.inflammabilite = 5;
		this.resistance = 6;
	}


}
