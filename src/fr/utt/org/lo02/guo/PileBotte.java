/**
 * 
 */
package fr.utt.org.lo02.guo;

import java.awt.Point;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author GUOF
 * 
 */
public class PileBotte extends LinkedList<Carte> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Point position;

	// public final Point pJouer = new Point(830,500);

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
	 * Procédure permettant de montrer les cartes botte avec un léger décallage
	 * entre chaucune d'elles lorsqu'il y en a plusieurs dans la pile.
	 */
	public void montre() {
		// TODO Auto-generated method stub
		Iterator<Carte> it = this.iterator();
		Point p = position;
		while (it.hasNext()) {
			Carte c = it.next();
			c.setPosition(position);
			c.setLocation(p);
			p = new Point((int) (p.getX() + 20), (int) (p.getY()));
			// c.setFace(true);
			// c.montrer();
		}
	}

}
