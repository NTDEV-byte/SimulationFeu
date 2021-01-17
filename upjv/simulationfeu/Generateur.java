package upjv.simulationfeu;

import java.util.Random;

import upjv.simulationfeu.objets.Cendre;
import upjv.simulationfeu.objets.Eau;
import upjv.simulationfeu.objets.Feu;
import upjv.simulationfeu.objets.Pluie;
import upjv.simulationfeu.objets.Vapeur;
import upjv.simulationfeu.objets.Vent;
import upjv.simulationfeu.objets.arbres.Acerpalmatum;
import upjv.simulationfeu.objets.arbres.Asiminier;
import upjv.simulationfeu.objets.arbres.Chene;
import upjv.simulationfeu.objets.arbres.Kaktuse;
import upjv.simulationfeu.objets.arbres.Saratoga;
import upjv.simulationfeu.objets.arbres.Savonnier;
import upjv.simulationfeu.objets.personnes.Pompier;

public class Generateur extends Entity{ 
	
		/*
		 * Class Generateur permet de générés les objets et de les ajouter directement 
		 * dans la liste qui correspond a l'objet généré via l'aggrégation Terrain qui est présente
		 * dans Entity 
		 * la méthode qui se trouve dans terrain(ajouteEntity) permet de vérifié l'intégrité du système 
		 * et de l'ajouter a liste qui adéquate
		 * 
		 */
	
	
		/*
		 * définitions des différents types 
		 */
	     public enum Type_Arbre{CHENE,KAKTUSE,SARATOGA,ACER,ASIMINIER,SAVONNIER};
	     public enum Type_Personne{POMPIER,TOURISTE};
	     public enum Type_Feu{FEU};
	     public enum Type_Eau{EAU};
	     public enum Type_VENT{VENT};
	     public enum Type_Cendre{CENDRE};
	     public enum Type_Vapeur{VAPEUR};
	     public enum Type_Pluie{PLUIE};
	     
	     
	     
	     /**constructeur qui permet de générés des arbres en  précisants
	      *nombres d'arbres
	  	  *Zone X,Y
	      *Type 
	      **/

	     public Generateur(int nombre,int xZone,int yZone,Type_Arbre arbre) {
	    	 this.random = new Random();
	    	 for(int i = 0; i < nombre ;i++) {
	    		 int xRand = random.nextInt(xZone);
	    		 int yRand = random.nextInt(yZone);	    			 
	    			 arbre = Type_Arbre.values()[random.nextInt(Type_Arbre.values().length)];
	    			  if(arbre==Type_Arbre.SAVONNIER) {level.ajouteEntity(new Savonnier(xRand,yRand));}
			    	  else if(arbre==Type_Arbre.ASIMINIER) {level.ajouteEntity(new Asiminier(xRand,yRand));}
			    	  else if(arbre==Type_Arbre.ACER) {level.ajouteEntity(new Acerpalmatum(xRand,yRand));}
			    	  else if(arbre==Type_Arbre.SARATOGA) {level.ajouteEntity(new Saratoga(xRand,yRand));}
			    	  else if(arbre==Type_Arbre.KAKTUSE) {level.ajouteEntity(new Kaktuse(xRand,yRand));}
			    	  else {level.ajouteEntity(new Chene(xRand,yRand));}
	    	 }
	     }
	     
	     /**constructeur qui permet de générés des arbres en  précisants
	      *nombres d'arbres
	  	  *Zone X,Y
	  	  *taille
	      *Type 
	      **/
	     public Generateur(int nombre,int xZone,int yZone,int size,Type_Arbre arbre) {
	    	 this.random = new Random();
	    	 for(int i = 0; i < nombre ;i++) {
	    		 int xRand = random.nextInt(xZone);
	    		 int yRand = random.nextInt(yZone);
	    		 int taille_arbre = random.nextInt(size);

	    		      arbre = Type_Arbre.values()[random.nextInt(Type_Arbre.values().length)];
	    			  if(arbre==Type_Arbre.SAVONNIER) {level.ajouteEntity(new Savonnier(xRand,yRand,taille_arbre,taille_arbre));}
			    	  else if(arbre==Type_Arbre.ASIMINIER) {level.ajouteEntity(new Asiminier(xRand,yRand,taille_arbre,taille_arbre));}
			    	  else if(arbre==Type_Arbre.ACER) {level.ajouteEntity(new Acerpalmatum(xRand,yRand,taille_arbre,taille_arbre));}
			    	  else if(arbre==Type_Arbre.SARATOGA) {level.ajouteEntity(new Saratoga(xRand,yRand,taille_arbre,taille_arbre));}
			    	  else if(arbre==Type_Arbre.KAKTUSE) {level.ajouteEntity(new Kaktuse(xRand,yRand,taille_arbre,taille_arbre));}
			    	  else {level.ajouteEntity(new Chene(xRand,yRand,taille_arbre,taille_arbre));}
	    		 
	    	 }
	     }
	     
	     
	     /**constructeur qui permet de générés des personnes en  précisants
	      *nombres de personnes
	  	  *Zone X,Y
	      *Type Personne
	      **/
	     public Generateur(int nombre,int xZone,int yZone,Type_Personne personne) {
	    	 this.random = new Random();
	    	 for(int i = 0; i < nombre ; i++) {
	    		 if(personne==Type_Personne.POMPIER) {
	    			 level.ajouteEntity(new Pompier(xZone,yZone));
	    		 }else {
	    		 }
	    	 }
	     }

	     
	     /**constructeur qui permet de générés des arbres en  précisants
	      *nombres de feux
	  	  *Zone X,Y
	  	  *taille
	      *Type Feu
	      **/
	     public Generateur(int nombre,int xZone,int yZone,Type_Feu feu) {
	    	 this.random = new Random();
	    	 for(int i = 0;i< nombre;i++) {
	    		 int xRand = random.nextInt(10);
	    		 int yRand = random.nextInt(10);
	    		 level.ajouteEntity(new Feu(xZone+xRand,yZone+yRand));
	    	 }
	     }
	     
	     /**constructeur qui permet de générés de l'eau en  précisants
	      *nombres d'arbres
	  	  *Zone X,Y
	  	  *la direction
	      *Type
	      **/
	     
	     public Generateur(int nombre,int xZone,int yZone,double angle,Type_Eau eau) {
	    	 for(int i = 0; i< nombre;i++) {
	    		level.ajouteEntity(new Eau(xZone,yZone,angle));
	    	 }
	     }
	     public Generateur(int nombre,int xZone,int yZone,double angle,Type_Eau eau,boolean bomb) {
	    	 random = new Random();
	    	 for(int i = 0; i< nombre;i++) {
	    		level.ajouteEntity(new Eau(random.nextInt(xZone),random.nextInt(yZone),angle));
	    	 }
	     }
	     
	     /**constructeur qui permet de générés du vent en  précisants
	      *nombres de vents
	  	  *Zone X,Y
	  	  *la direction que le vent va suivre
	      *Type 
	      **/
	     
	     public Generateur(int nombre,int xZone,int yZone,double angle,Type_VENT vent) {
	    	 this.random = new Random();
	    	 for(int i = 0; i< nombre;i++) {
	    		level.ajouteEntity(new Vent(random.nextInt(xZone+30),random.nextInt(yZone+10),angle));
	    	 }
	     }
	     
	     /**constructeur qui permet de générés de la pluie en  précisants
	      *le nombres de gouttes
	  	  *Zone X,Y
	      *Type 
	      **/
	     
	     
	     public Generateur(int nombre,int xZone,int yZone,Type_Pluie pluie) {
	    	 this.random = new Random();
	    	 for(int i = 0; i< nombre;i++) {
	    	 level.ajouteEntity(new Pluie(random.nextInt(xZone+30),random.nextInt(yZone)));
	    	 }
	     }
	     
	     
	     
	     /**constructeur qui permet de générés de la pluie en  précisants
	      *le nombres
	  	  *Zone X,Y
	      *Type 
	      **/
	     
	     public Generateur(int nombre,int xZone,int yZone,Type_Vapeur vapeur) {
	    	 this.random = new Random();
	    	 for(int i = 0; i< nombre;i++) {
	    		  level.ajouteEntity(new Vapeur(xZone,yZone));
	    	 }
	    	 
	     }
	     /**constructeur qui permet de générés de la pluie en  précisants
	      *le nombres
	  	  *Zone X,Y
	      *Type 
	      **/
	     public Generateur(int nombre,int xZone,int yZone,Type_Cendre cendre) {
	    	 this.random = new Random();
	    	 for(int i = 0; i< nombre;i++) {
	    		  level.ajouteEntity(new Cendre(xZone,yZone));
	    	 }
	    	 
	     }
	     	
}
