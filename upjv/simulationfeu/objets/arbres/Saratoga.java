package upjv.simulationfeu.objets.arbres;

import java.awt.Color;

import upjv.simulationfeu.objets.Arbre;

public class Saratoga extends Arbre{

	public Saratoga(int x, int y) {
		super(x, y,new Color(32,186,91));
		this.inflammabilite = 3;
		this.resistance = 4;
	}


	public Saratoga(int x, int y,int w,int h) {
		super(x, y,w,h,new Color(32,186,91));
		this.inflammabilite = 3;
		this.resistance = 4;
	}

}
