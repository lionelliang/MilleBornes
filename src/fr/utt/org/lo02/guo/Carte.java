/**
 * 
 */
package fr.utt.org.lo02.guo;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 * @author GUO et FOURNIER Classe qui permet la création de cartes.
 */
public abstract class Carte extends JLabel implements MouseListener,
		MouseMotionListener, Serializable {

	private static final long serialVersionUID = 1L;
	/*
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	transient protected Main main;
	protected JoueurMB joueur = null;
	protected boolean face = false;
	protected boolean canclick = false;
	protected boolean piochable = false;
	protected int numCarte;
	protected String musique;
	protected ImageIcon imageFace;
	protected ImageIcon imageSous;
	protected Point position;

	protected Point beginPoint;

	/**
	 * Constructeur de la classe Carte.
	 * 
	 * @param m
	 * @param up
	 *            true si on veut que la carte soit visible, false si on veut
	 *            qu'elle soit retournée.
	 * @param nom
	 *            nom de la carte.
	 */
	public Carte(Main m, boolean up, String nom) {
		this.main = m;
		this.imageFace = new ImageIcon("images/" + nom);
		this.imageSous = new ImageIcon("images/back.gif");
		this.beginPoint = new Point(0, 0);
		this.setSize(80, 96);
		this.cacher();
		this.setVisible(true);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}

	/**
	 * Getter de l'attribut piochable.
	 * 
	 * @return
	 */
	public boolean isPiochable() {
		return piochable;
	}

	/**
	 * Setter de l'attribut piochable.
	 * 
	 * @param piochable
	 */
	public void setPiochable(boolean piochable) {
		this.piochable = piochable;
	}

	/**
	 * Getter de la musique. Permet d'accéder au nom de la que provoque
	 * l'utilisation d'une carte.
	 * 
	 * @return nom de la musique.
	 */
	public String getMusique() {
		return musique;
	}

	/**
	 * Setter de la musique. Permet de modifier le son que fait une carte
	 * lorsqu'elle est jouée.
	 * 
	 * @param musique
	 */
	public void setMusique(String musique) {
		this.musique = musique;
	}

	/**
	 * Getter de l'attribut joueur.
	 * 
	 * @return
	 */
	public JoueurMB getJoueur() {
		return joueur;
	}

	/**
	 * Setter de l'attribut joueur.
	 * 
	 * @param joueur
	 */
	public void setJoueur(JoueurMB joueur) {
		this.joueur = joueur;
	}

	/**
	 * Getter de l'attribut canclick.
	 * 
	 * @return
	 */
	public boolean isCanclick() {
		return canclick;
	}

	/**
	 * Setter de l'attribut canclick.
	 * 
	 * @param canclick
	 */
	public void setCanclick(boolean canclick) {
		this.canclick = canclick;
	}

	/**
	 * Getter de l'attribut position.
	 * 
	 * @return
	 */
	public Point getPosition() {
		return position;
	}

	/**
	 * Setter de l'attribut position.
	 * 
	 * @param position
	 */
	public void setPosition(Point position) {
		this.position = position;
	}

	/**
	 * Getter de l'attribut numCarte.
	 * 
	 * @return
	 */
	public int getNumCarte() {
		return numCarte;
	}

	/**
	 * Setter de l'attribut numCarte.
	 * 
	 * @param numCarte
	 */
	public void setNumCarte(int numCarte) {
		this.numCarte = numCarte;
	}

	/**
	 * Getter de l'attribut face.
	 * 
	 * @return
	 */
	public boolean isFace() {
		return face;
	}

	/**
	 * Setter de l'attribut face.
	 * 
	 * @param face
	 */
	public void setFace(boolean face) {
		this.face = face;
	}

	/**
	 * Procédure qui permet de montrer la face d'une carte.
	 */
	public void montrer() {
		if (this.face)
			this.setIcon(imageFace);
	}

	/**
	 * Méthode qui permet de cacher la face d'une carte.
	 */
	public void cacher() {
		if (!this.face)
			this.setIcon(imageSous);
	}

	@Override
	abstract public void mouseDragged(MouseEvent arg);

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Procédure qui permet de stocker le point de départ lors du déplacement d'une carte.
	 */
	public void mousePressed(MouseEvent arg) {
		// TODO Auto-generated method stub
		if (isCanclick()) {
			beginPoint = SwingUtilities.convertPoint(this, arg.getPoint(),
					this.getParent());
			// beginPoint = arg.getPoint();
		}
	}

	@Override
	/**
	 * Procédure qui permet de faire revenir la carte à sa position de départ lorsque le joueur lache la carte ailleurs que dans une zone jouable.
	 */
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (this.canclick) {
			this.setLocation(position);
			// System.out.println("mousereleased");
		}
	}
}
