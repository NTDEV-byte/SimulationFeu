package upjv.simulationfeu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import upjv.simulationfeu.Generateur.Type_Arbre;
import upjv.simulationfeu.Generateur.Type_Cendre;
import upjv.simulationfeu.Generateur.Type_Feu;
import upjv.simulationfeu.Generateur.Type_Personne;
import upjv.simulationfeu.Generateur.Type_Pluie;
import upjv.simulationfeu.Generateur.Type_VENT;
import upjv.simulationfeu.Generateur.Type_Vapeur;
import upjv.simulationfeu.objets.Arbre;
import upjv.simulationfeu.objets.Cendre;
import upjv.simulationfeu.objets.Eau;
import upjv.simulationfeu.objets.Feu;
import upjv.simulationfeu.objets.Personne;
import upjv.simulationfeu.objets.Pluie;
import upjv.simulationfeu.objets.Vapeur;
import upjv.simulationfeu.objets.Vent;

public class Terrain{
	
	  public static Terrain instance;
	  private List<Arbre> arbres = new ArrayList<Arbre>();
	  private List<Feu> feux = new ArrayList<Feu>();
	  private List<Eau> eaux = new ArrayList<Eau>();
	  private List<Vapeur> vapeurs = new ArrayList<Vapeur>();
	  private List<Cendre> cendres = new ArrayList<Cendre>();
	  private List<Vent> vents = new ArrayList<Vent>();
	  private List<Personne> personnes = new ArrayList<Personne>();
	  private List<Pluie> pluies = new ArrayList<Pluie>();
	  private Random random;
	  private int score;
	  private int timer = 0;
	     
	     public Terrain(int arbres,int feux) {
	    	 Terrain.instance = this;
	    	 this.random = new Random();
	    	 new Generateur(15,150,150,Type_Pluie.PLUIE);
	     	 new Generateur(arbres,Game.getLargeur(),Game.getHauteur(),15,Type_Arbre.values()[random.nextInt(Type_Arbre.values().length)]);
	    	 new Generateur(feux,Game.getLargeur()/2+random.nextInt(150),Game.getHauteur()/2+random.nextInt(150),Type_Feu.FEU);
	     }
	     
	     
	     /**
	     *cette méthode permet au générateur d'ajouter dynamiquement les entités
	   	 * générés dans la liste correspendante en vérifions a l'aide de instance of
	     */
	     public void ajouteEntity(Entity e) {
	    	   if(e instanceof Arbre){ arbres.add((Arbre) e);}
	    	   else if(e instanceof Feu){if(feux.size()<3000)feux.add((Feu) e);}
	    	   else if(e instanceof Eau) {eaux.add((Eau) e);}
	    	   else if(e instanceof Vent){vents.add((Vent) e);}
	    	   else if(e instanceof Vapeur){vapeurs.add((Vapeur)e);}
	    	   else if(e instanceof Cendre){cendres.add((Cendre)e);}
	    	   else if(e instanceof Pluie){pluies.add((Pluie)e);}
	    	   else {if(personnes.size()<200)personnes.add((Personne) e);}
	     }
	     

	     /**méthodes qui permet l'affichage des entités*/
	     public void afficheTerrain(Graphics g) {
	    	 for(Arbre a:arbres) {a.afficheArbre(g);}
	    	 for(Feu f:feux) {f.afficheFeu(g);}
	    	 for(Personne p:personnes) {p.affichePersonne(g);}
	    	 for(Eau e:eaux) {e.afficheEau(g);}
	    	 for(Vent v:vents) {v.afficheVent(g);}
	    	 for(Vapeur v:vapeurs) {v.afficheVapeur(g);}
	    	 for(Cendre c : cendres) {c.afficheCendre(g);}
	    	 for(Pluie p : pluies) {p.affichePluie(g);}
	    	 afficheScore(g);
	    	 afficheAustralieSauver(g);
	     }
	     

	     /**méthode qui permet la mise a jour des positions des entités de vérifiés les collisions*/
	     public void metAjourTerrain() {
	    	 if(timer<7500) timer++; else timer = 0;
	    	 for(Feu f:feux) {f.updatePosition();}
	    	 for(Personne p:personnes) {p.updatePosition();}
	    	 for(Eau e:eaux) {e.updatePosition();}
	    	 for(Vent v:vents) {v.updatePosition();}
	    	 for(Vapeur v:vapeurs) {v.updatePosition();}
	    	 for(Cendre c:cendres) {c.updatePosition();}
	    	 for(Pluie p:pluies) {p.updatePosition();}
	    	 collisionFeuArbre();
	    	 collisionFeuEau();
	    	 collisionFeuPersonne();
	    	 collisionVentFeu();
	    	 collisionePluieFeu();
	    	 supprimeFeuEteint();
	    	 supprimeEauVaporiser();
	    	 supprimePersonneMortes();
	    	 supprimeVent();
	    	 supprimeVapeur();
	    	 supprimeCendre();
	    	 supprimePluie();
	    	 creationFeuSouris();
	    	 creationPompierSouris();
	    	 if(timer%351==0) new Generateur(5,random.nextInt(Game.getLargeur()+5),random.nextInt(Game.getHauteur()+5),random.nextInt(360),Type_VENT.VENT);
	     }
	     
	     
	     public void creationFeuSouris() {
	    	  if(Mouse.getMouseB()==1) {
	    		   new Generateur(5,Mouse.getMouseX(),Mouse.getMouseY(),Type_Feu.FEU);
	    	  }
	     }
	     
	     public void creationPompierSouris() {
	    	  if(Mouse.getMouseB()==3) {
	    		   new Generateur(1,Mouse.getMouseX(),Mouse.getMouseY(),Type_Personne.POMPIER);
	    	  }
	     }

	     public Feu feuPlusProche(Entity e) {
	    	  int max_distance = 99999;
	    	  Feu fpp = null;
	    	  for(int i = 0; i < feux.size();i++) {
	    		  int distance = getDistance(e,feux.get(i));
	    		  if(distance<max_distance) {
	    			   fpp = feux.get(i);
	    			   max_distance = distance;
	    		  }
	    	  }
	     return fpp;
	     }
	     
	     public int getDistance(Entity e1,Entity e2) {
	    	 int dx = e1.getX() - e2.getX();
	    	 int dy = e1.getY() - e2.getY();
	    	 return (int)Math.sqrt(dx * dx + dy*dy);
	     }
	     
	     public void afficheScore(Graphics g) {
	     	 g.setColor(Color.blue);
	    	 g.setFont(new Font("Verdana",Font.BOLD,20));
	    	 g.drawString("Score: "+score,5,20);
	     }
	     
	     public void afficheAustralieSauver(Graphics g) {
	    	 if(this.feux.size()==0) {
	    		 g.setColor(Color.red);
		    	 g.setFont(new Font("Verdana",Font.BOLD,50));
		    	 g.drawString("Feu Eteint ! ",Game.getLargeur()/2-150,Game.getHauteur()/2);
		    	 new Generateur(5,Game.getLargeur()/2,Game.getHauteur()/2,Type_Vapeur.VAPEUR);
		    	
	    	 }
	    	
	     }
	     
	     public void afficheStatLists(Graphics g) {
	    	 g.setFont(new Font("Verdana",Font.BOLD,15));
	       	 g.setColor(Color.green);
	    	 g.drawString("Arbre: "+arbres.size(),Game.getLargeur()-200,200);
	    	 g.setColor(Color.red);
	    	 g.drawString("Feux: "+feux.size(),Game.getLargeur()-200, 250);
	    	 g.setColor(Color.blue);
	    	 g.drawString("Eaux: "+eaux.size(),Game.getLargeur()-200 ,300);
	    	 g.setColor(Color.white);
	    	 g.drawString("Vents: "+vents.size(),Game.getLargeur()-200,350);
	    	 g.setColor(Color.MAGENTA);
	    	 g.drawString("Personne: "+personnes.size(),Game.getLargeur()-200,400);
	     }
	     
	     
	     public void collisionFeuArbre() {
	    	 for(int i = 0;i<feux.size();i++) {
	    		  for(int j=0;j<arbres.size();j++) {
	    		    	 if(feux.get(i).getBox().intersects(arbres.get(j).getBox())) {
	    		    		  	arbres.get(j).diminueResistanceArbre(1);
	    		    		    arbres.get(j).removeArbre();
	    		    		    if(!arbres.get(j).isRemoved() && arbres.get(j).getColor()!=Color.DARK_GRAY) {
	    		    		    	new Generateur(arbres.get(j).getInflammabilite(),feux.get(i).getX(),feux.get(i).getY(),Type_Feu.FEU);
	    		    		    	new Generateur(10,feux.get(i).getX(),feux.get(i).getY(),Type_Cendre.CENDRE);
	    		    		    	score+=1;
	    		    		    }
	    		    	  }
	    		    }
	    	  }
	     }
	     
	     
	    public void collisionFeuEau() {
	    	  for(int i = 0;i<feux.size();i++) {
	    		   for(int j=0;j<eaux.size();j++) {
	    			    if(feux.get(i).getBox().intersects(eaux.get(j).getBox())) {
	    			    	     feux.get(i).diminueResistanceFeu(1);	
	    			    	     eaux.get(j).diminueResistanceEau(1);
	    			    	     new Generateur(10,feux.get(i).getX(),feux.get(i).getY(),Type_Vapeur.VAPEUR);
	    			    }
	    		   }
	    	  }
	    	  
	     }
	     
	     public void collisionFeuPersonne() {
	    	 for(int i = 0;i<feux.size();i++) {
	    		  for(int j=0;j<personnes.size();j++) {
	    			  if(feux.get(i).getBox().intersects(personnes.get(j).getBox())) {
	    				   personnes.get(j).diminueResistance(1);
	    			  }
	    		  }
	    	 }
	    	 
	     }
	     
	     public void collisionVentFeu() {
	    	 for(int i = 0;i<vents.size();i++) {
	    		  for(int j=0;j<feux.size();j++) {
	    			   if(vents.get(i).getBox().intersects(feux.get(j).getBox())) {
	    				   feux.get(j).setAngle(vents.get(i).getAngle());
	    				   feux.get(j).setVitesse(15);
	    			   }
	    		  }
	    	 }
	     }
	     
	     public void collisionePluieFeu() {
	    	 for(int i = 0;i<pluies.size();i++) {
	    		  for(int j=0;j<feux.size();j++) {
	    			   if(pluies.get(i).getBox().intersects(feux.get(j).getBox())) {
	    				   feux.get(j).diminueResistanceFeu(1);
	    				   pluies.get(i).diminueResistancePluie(1);
	    				   new Generateur(5,pluies.get(i).getX(),pluies.get(i).getY(),Type_Vapeur.VAPEUR);
	    			   }
	    		  }
	    	 }
	     }
	     
	   
	     public void supprimeEauVaporiser() {
	    	  for(int i = 0;i<eaux.size();i++) {
	    		   if(eaux.get(i).isRemoved()) {
	    			     eaux.remove(i);
	    		   }
	    	  }
	     }
	     
	     public void supprimeFeuEteint() {
	    	 for(int i = 0;i<feux.size();i++) {
	    		  if(feux.get(i).isRemoved()) {
	    			   feux.remove(i);
	    		  }
	    	 }
	     }
	     
	     public void supprimePersonneMortes() {
	    	 for(int i = 0;i<personnes.size();i++) {
	    		  if(personnes.get(i).isRemoved()) {
	    			   personnes.remove(i);
	    		  }
	    	 }
	     }
	     
	     public void supprimeVent() {
	    	 for(int i = 0;i<vents.size();i++) {
	    		 if(vents.get(i).isRemoved()) {
	    			 vents.remove(i);
	    		 }
	    	 }
	     }
	     
	     public void supprimeVapeur() {
	    	 for(int i = 0; i < vapeurs.size();i++) {
	    		  if(vapeurs.get(i).isRemoved()) {
	    			   vapeurs.remove(i);
	    		  }
	    	 }
	     }
	
	     public void supprimeCendre() {
	    	 for(int i = 0;i<cendres.size();i++) {
	    		  if(cendres.get(i).isRemoved()) {
	    			  cendres.remove(i);
	    		  }
	    	 }
	     }
	     
	     public void supprimePluie() {
	    	 for(int i = 0;i<pluies.size();i++) {
	    		  if(pluies.get(i).isRemoved()) {
	    			  pluies.remove(i);
	    		  }
	    	 }
	     }
	    
	     	public int getScore() {return this.score;}
	     	public void setScore(int valeur) {this.score+=valeur;}

			
}
