package upjv.simulationfeu.objets.arbres;

import java.awt.Color;

import upjv.simulationfeu.objets.Arbre;

public class Asiminier extends Arbre{

	public Asiminier(int x, int y) {
		super(x, y,new Color(194,33,143));
		this.inflammabilite = 5;
		this.resistance = 2;
	}

	public Asiminier(int x, int y,int w,int h) {
		super(x, y,w,h,new Color(194,33,143));
		this.inflammabilite = 5;
		this.resistance = 2;
	}

	
	
}
