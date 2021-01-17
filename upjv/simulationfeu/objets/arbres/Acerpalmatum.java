package upjv.simulationfeu.objets.arbres;

import java.awt.Color;

import upjv.simulationfeu.objets.Arbre;

public class Acerpalmatum extends Arbre{

	public Acerpalmatum(int x, int y) {
		super(x, y,new Color(186,32,70));
		this.inflammabilite = 4;
		this.resistance = 3;
	}

	
	public Acerpalmatum(int x, int y,int w,int h) {
		super(x, y,w,h,new Color(186,32,70));
		this.inflammabilite = 4;
		this.resistance = 3;
	}

	

}
