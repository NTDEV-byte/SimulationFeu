package upjv.simulationfeu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import upjv.simulationfeu.Generateur.Type_Pluie;
import upjv.simulationfeu.Generateur.Type_VENT;

public class  Game extends JPanel implements KeyListener{

	/*
	 * Classe Principale 
	 * 
	 */
	
	
		private static final long serialVersionUID = 1L;
		/**Largeur de la fenetre */
		private static final int LARGEUR = 800;
		/**Hauteur de la fenetre*/
		private static final int HAUTEUR = 640;
	    private boolean affiche_stats;
		/**aggr�gations utilis�s: frame,level,mouse,random */
		private JFrame frame;
		private Terrain level;
		private Mouse mouse;
		private Random random;
		private boolean launched = false;

		 public Game() {
		    level = new Terrain(500,120);
		    mouse = new Mouse();
		    random = new Random();
			creationVue();		
		 }
		 
		 /*
		  * Methode qui permet la cr�ation de la fenetre avec les propri�t�s suivantes:
		  * visibiliti�
		  * taille non modifiable
		  * position
		  * fermable
		  * dimensions
		  * ajout du JPanel(Game),KeyListener,MouseListener 
		  * pack pour que la fenetre JFrame s'ajuste automatiquement a la taille du JPanel 
		  */
		 
		 private void creationVue() {
			 frame = new JFrame("Help The FireMan");
			 frame.setVisible(true);
			 frame.setResizable(false);
			 frame.setLocationRelativeTo(null);
			 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 setPreferredSize(new Dimension(LARGEUR,HAUTEUR));
 			 frame.add(this);
			 frame.addKeyListener(this);
			 frame.addMouseMotionListener(mouse);
			 frame.addMouseListener(mouse);
			 frame.pack();
		 }
		 
		 /**Boucle principale du jeu
		  * on paint le fond avec (r,g,b) 11 217 66
		  * on met a jours les entit�s
		  * on affiche 
		  * */
		 public void paint(Graphics g) {

		 	if(!launched){
		 		menuAide(g);
		   
		 	}else{
		    g.setColor(new Color(11, 217, 66));
			g.fillRect(0, 0, getWidth(), getHeight());
			level.afficheTerrain(g);
			level.metAjourTerrain();	
            if(affiche_stats) level.afficheStatLists(g);
		 	}
		 	repaint();
			
		 }
		 
		 public static void main(String[] args) {
			 javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new Game();
				}
			 });
			 
		 }
		 
		 /*
		  * d�tection des touches appuyers par l'utilisateur
		  * V pour g�n�r� un vent
		  * S pour afficher le menu qui pr�sente le nombres d'entit�s dans le jeu de feux pompiers eaux ...
		  * C pour fermer le menu 
		  * P pour faire pleuvoir de la pluie
		  */

		public void menuAide(Graphics g){
			g.setColor(new Color(11, 217, 66));
			g.fillRect(0,0,getWidth(),getHeight());
			g.setColor(new Color(8, 99, 3));
			g.setFont(new Font("Verdana",Font.BOLD,35));
			g.drawString("Tips",485,170);
			g.drawRect(460,185,330,250);
			g.setColor(Color.white);
			/*tips*/
			g.setFont(new Font("Verdana",Font.BOLD,14));
			g.drawString("1-Maintenez le Clique Gauche de la souris",460,220);
			g.drawString("2-Attendez quelques secondes",460,270);
			g.drawString("3-Clique droit pour d�ploy� les pompiers",460,320);
			g.drawString("4-Touche P pour ralentir le feu",460,370);
			/*comment y jouer*/
		    g.setFont(new Font("Verdana",Font.BOLD,20));
		    g.setColor(new Color(8, 99, 3));
			g.drawString("Fonctionnement",LARGEUR / 2 - 120,50);
			g.setFont(new Font("Verdana",Font.BOLD,15));
			g.setColor(Color.white);
			g.drawString("Clique Gauche Souris -> Permet de g�n�r� du feu",5,200);
			g.drawString("Clique Droit Souris -> Permet de g�n�r� des pompiers",5,250);
			g.drawString("Touche V -> Permet de g�n�r� du Vent",5,300);
			g.drawString("Touche P -> Permet de g�n�r� de la pluie",5,350);
			g.drawString("Touche S -> Menu qui affiche l'�tat du jeu",5,400);
			g.drawString("Touche C -> Permet de cach� le menu",5,450);
			g.setFont(new Font("Verdana",Font.BOLD,25));
				g.drawString("Appuyer la Touche Espace pour continuer ",5,550);
		}


 /*
		  * d�tection des touches appuyers par l'utilisateur
		  * V pour g�n�r� un vent
		  * S pour afficher le menu qui pr�sente le nombres d'entit�s dans le jeu de feux pompiers eaux ...
		  * C pour fermer le menu 
		  * P pour faire pleuvoir de la pluie
		  */
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_V) {
				new Generateur(5,random.nextInt(LARGEUR)+1,random.nextInt(HAUTEUR)+1,random.nextInt(360)+1,Type_VENT.VENT);
			}
			if(e.getKeyCode()==KeyEvent.VK_P) {
				new Generateur(150,LARGEUR,300,Type_Pluie.PLUIE);
			}
			if(e.getKeyCode()==KeyEvent.VK_S) {
				affiche_stats = true;
			}
			if(e.getKeyCode()==KeyEvent.VK_C) {
				affiche_stats = false;
			}
			if(e.getKeyCode()==KeyEvent.VK_SPACE){
				launched = true;
			}
			
			
		}
		public void keyReleased(KeyEvent e) {}
		public void keyTyped(KeyEvent e) {}
		public static int getLargeur() {return LARGEUR;}
		public static int getHauteur() {return HAUTEUR;}
		public Terrain getTerrain() {return level;}
		public JFrame getFrame() {return frame;}
		public Mouse getMouse() {return mouse;}
		public Random getRandom() {return random;}
		public void setTerrain(Terrain lvl) {this.level=lvl;}
}
