/**
 * 
 */
package fr.utt.org.lo02.guo;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author GUO et FOURNIER Classe permettant la création de la liste de
 *         défausse.
 */
public class ListeDefausse extends LinkedList<Carte> implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	/*
	 * (non-javadoc)
	 */
	private Point position;
	private Rectangle region;
	protected boolean piochable = false;

	/**
	 * Constructeur de la liste de défausse.
	 */
	public ListeDefausse() {
		super();
		// TODO Auto-generated constructor stub
		position = new Point(500, 300 - 8);
		region = new Rectangle(500, 300 - 8, 100, 116);
	}

	public Rectangle getRegion() {
		return region;
	}

	public void setRegion(Rectangle region) {
		this.region = region;
	}

	/**
	 * Getter sur le point supérieur gauche de la pile de cartes sur
	 * l'interface.
	 * 
	 * @return point supérieur gauche de la pile de cartes sur l'interface.
	 */
	public Point getPosition() {
		return position;
	}

	/**
	 * Setter sur le point supérieur gauche de la pile de cartes sur
	 * l'interface.
	 * 
	 * @param position
	 */
	public void setPosition(Point position) {
		this.position = position;
	}

	/**
	 * Procédure qui va montrer la carte au dessus de la pile.
	 */
	public void montrer() {
		if (this.isEmpty()) {
			return;
		}
		this.getFirst().setLocation(position);
		this.getFirst().setPosition(getPosition());
		this.getFirst().setLocation(position);
		Iterator<Carte> it = this.iterator();
		while (it.hasNext()) {
			Carte c = it.next();
			if (c != this.getFirst()) {
				c.setVisible(false);
				// c.setPosition(getPosition());
				// c.setLocation(position);
				// c.setFace(true);
				// c.montrer();
			}
		}
	}

	public boolean isPiochable() {
		return piochable;
	}

	public void setPiochable(boolean piochable, JoueurMB joueur) {
		this.piochable = piochable;
		if (this.isEmpty())
			return;
		if (piochable == true) {
			this.getFirst().setPiochable(true);
			this.getFirst().setCanclick(true);
			this.getFirst().setJoueur(joueur);
		} else {
			this.getFirst().setPiochable(false);
			this.getFirst().setCanclick(false);
			this.getFirst().setVisible(true);
		}
	}
}