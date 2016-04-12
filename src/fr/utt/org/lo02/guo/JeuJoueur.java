/**
 * 
 */
package fr.utt.org.lo02.guo;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.SwingUtilities;

/**
 * @author GUO et FOURNIER Classe qui permet de gérer les cartes que le joueur a
 *         en main
 */
public class JeuJoueur extends ArrayList<Carte> implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	/*
	 * (non-javadoc)
	 */
	private Point position;
	public final Point pJouer = new Point(180, 590);

	/**
	 * Getter du Point position
	 * 
	 * @return point de l'angle supérieur gauche de la zone où est affiché la
	 *         pile de la main du joueur.
	 */
	public Point getPosition() {
		return position;
	}

	/**
	 * Setter du Point position
	 * 
	 * @param position
	 *            point de l'angle supérieur gauche de la zone où on désire
	 *            afficher la pile de la main du joueur.
	 */
	public void setPosition(Point position) {
		this.position = position;
	}

	/**
	 * procédure qui va afficher le jeu du joueur lorsque c'est à son tour de
	 * jouer.
	 */
	public void montrer(boolean canclick) {
		// Iterator<Carte> it = this.iterator();
		// if(it==null)
		// return;
		Point p = pJouer;
		// while(it.hasNext()){
		for (int i = 0; i < this.size(); i++) {
			// Carte c = it.next();
			Carte c = this.get(i);
			c.setPosition(p);
			// c.setLocation(p);
			if (!canclick)
				Musique.play("sounds\\piocher.mp3");
			Main.mouvoir(
					c,
					SwingUtilities.convertPoint(c, c.getLocation(),
							c.getParent()), p);
			c.setFace(true);
			c.montrer();
			c.setCanclick(canclick);
			p = new Point((int) (p.getX() + 90), (int) (p.getY()));
			// System.out.println(c);
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Procédure qui va cacher le jeu du joueur lorsque ça n'est plus à lui de
	 * jouer.
	 */
	public void cacher() {
		Iterator<Carte> it = this.iterator();
		// Point p = position;
		while (it.hasNext()) {
			Carte c = it.next();
			c.setPosition(position);
			Main.mouvoir(
					c,
					SwingUtilities.convertPoint(c, c.getLocation(),
							c.getParent()), position);
			// Main.mouvoir(c,SwingUtilities.convertPoint(c,c.getLocation(),c.getParent()),p);
			// p = new Point(p.x+10,p.y+10);
			// System.out.println(p);
			c.setFace(false);
			c.setCanclick(false);
			c.cacher();
			c.setLocation(position);
			// System.out.println(c.getNumCarte());
		}
	}
}
