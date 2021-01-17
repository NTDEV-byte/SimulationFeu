package upjv.simulationfeu.objets.arbres;

import java.awt.Color;

import upjv.simulationfeu.objets.Arbre;

public class Kaktuse extends Arbre{

	public Kaktuse(int x, int y) {
		super(x, y,new Color(76,186,32));
		this.inflammabilite = 2;
		this.resistance = 5;
	}
	

	public Kaktuse(int x, int y,int w,int h) {
		super(x, y,w,h,new Color(76,186,32));
		this.inflammabilite = 2;
		this.resistance = 5;
	}

}
