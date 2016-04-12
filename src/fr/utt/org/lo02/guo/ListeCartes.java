package fr.utt.org.lo02.guo;

import java.awt.Point;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Iterator;

/**
 * @author GUO et FOURNIER Classe qui permet la création d'un jeu de carte.
 */
public class ListeCartes extends LinkedList<Carte> implements Serializable {

	private static final long serialVersionUID = 1L;

	// LinkedList<Carte> this=new LinkedList();
	/*
	 * (non-javadoc)
	 */
	transient Main main;
	private Point position;
	protected boolean piochable = false;

	/**
	 * Procédure d'ajout des cartes dans le jeu.
	 * 
	 * @param m
	 */
	public void initialListeCartes(Main m) {
		main = m;
		position = new Point(360, 300 - 8);

		for (int i = 1; i <= 6; i++) {
			CarteEtape carteEtape25 = new CarteEtape(25, 11,
					"sounds\\etape.mp3", m, false, "etape_25.gif");
			CarteEtape carteEtape50 = new CarteEtape(50, 12,
					"sounds\\etape.mp3", m, false, "etape_50.gif");
			CarteEtape carteEtape75 = new CarteEtape(75, 13,
					"sounds\\etape.mp3", m, false, "etape_75.gif");
			this.push(carteEtape25);
			this.push(carteEtape50);
			this.push(carteEtape75);
		}

		for (int i = 1; i <= 9; i++) {
			CarteEtape carteEtape100 = new CarteEtape(100, 14,
					"sounds\\etape.mp3", m, false, "etape_100.gif");
			this.push(carteEtape100);
		}

		for (int i = 1; i <= 3; i++) {
			CarteEtape carteEtape200 = new CarteEtape(200, 15,
					"sounds\\etape.mp3", m, false, "etape_200.gif");
			this.push(carteEtape200);
		}

		for (int i = 1; i <= 2; i++) {
			CarteAttaque carteAttaque1 = new CarteAttaque("Accident", 21,
					"sounds\\accident.mp3", m, false, "attaque_accident.gif");
			CarteAttaque carteAttaque2 = new CarteAttaque("Panne escence", 22,
					"sounds\\crevation.mp3", m, false,
					"attaque_panne_essence.gif");
			CarteAttaque carteAttaque3 = new CarteAttaque("Crevaison", 23,
					"sounds\\crevation.mp3", m, false, "attaque_creuvaison.gif");
			CarteAttaque carteLimitation = new CarteAttaque(
					"Limitation de vitesse", 24, "sounds\\feu.mp3", m, false,
					"attaque_limitation_vitesse.gif");
			CarteAttaque carteFeurouge = new CarteAttaque("Feu rouge", 25,
					"sounds\\feu.mp3", m, false, "attaque_feu_rouge.gif");
			this.push(carteAttaque1);
			this.push(carteAttaque2);
			this.push(carteAttaque3);
			this.push(carteLimitation);
			this.push(carteFeurouge);
		}

		for (int i = 1; i <= 4; i++) {
			CarteParade carteParade1 = new CarteParade("Reparation", 31,
					"sounds\\reparation.mp3", m, false,
					"defense_reparations.gif");
			CarteParade carteParade2 = new CarteParade("Essence", 32,
					"sounds\\essence.mp3", m, false,
					"defence_station_essence.gif");
			CarteParade carteParade3 = new CarteParade("Roue de secours", 33,
					"sounds\\etape.mp3", m, false,
					"defence_roue_de_secours.gif");
			CarteParade carteParade4 = new CarteParade("Fin de limitation", 34,
					"sounds\\feu.mp3", m, false,
					"defense_fin_de_limitation.gif");
			this.push(carteParade1);
			this.push(carteParade2);
			this.push(carteParade3);
			this.push(carteParade4);
		}

		for (int i = 1; i <= 8; i++) {
			CarteParade carteFeuvert = new CarteParade("Feu Vert", 35,
					"sounds\\feu.mp3", m, false, "defense_feu_vert.gif");
			this.push(carteFeuvert);
		}

		CarteBotte botteAsduvolant = new CarteBotte("As du volant", 41,
				"sounds\\etape.mp3", m, false, "botte_as_du_volant.gif");
		CarteBotte botteCiterne = new CarteBotte("Camion citerne", 42,
				"sounds\\etape.mp3", m, false, "botte_camion_citerne.gif");
		CarteBotte botteIncrevable = new CarteBotte("Increvable", 43,
				"sounds\\etape.mp3", m, false, "botte_increuvable.gif");
		CarteBotte bottePrioritaire = new CarteBotte("Vehicule prioritaire",
				44, "sounds\\etape.mp3", m, false,
				"botte_vehicule_prioritaire.gif");
		this.push(botteAsduvolant);
		this.push(botteCiterne);
		this.push(botteIncrevable);
		this.push(bottePrioritaire);
		this.melanger();
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
	 * Méthode qui mélange la liste de cartes
	 */
	public void melanger() {
		Collections.shuffle(this);
		/*
		 * Random random=new Random(); Carte c = null; int i; for(int t =
		 * 0;t<this.size();t++){ i = random.nextInt(this.size()-t); c =
		 * this.get(t); this.set(t,this.get(t+i)); this.set(t+i,c); }
		 */
	}

	/**
	 * Metode qui va distribuer les cartes en debut de partie
	 * 
	 * @param listeJoueurs
	 *            Liste des joueurs présents dans la partie
	 */
	public void distribuer(ListeJoueurs listeJoueurs) {
		for (int i = 1; i <= 4; i++) {
			java.util.Iterator<JoueurMB> it = listeJoueurs.iterator();
			while (it.hasNext()) {
				Carte carte = this.pop();
				if (carte == null)
					System.out.println("carte null");
				it.next().recevoir(carte);
			}
		}

	}

	/**
	 * Méthode qui permet au joueur de tirer une carte.
	 * 
	 * @return carte piochée
	 */
	public Carte piocherCarte() {
		Carte c = this.pop();
		Musique.play("sounds\\piocher.mp3");
		return c;
	}

	/**
	 * Procédure qui permet de ne pas montrer les cartes de la pioche.
	 */
	public void montrer() {
		Iterator<Carte> it = this.iterator();
		while (it.hasNext()) {
			Carte c = it.next();
			c.setPosition(getPosition());
			c.setLocation(position);
			c.setFace(false);
			c.cacher();
		}
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
	 * Setter de l'attribut piochable. A chaque tour de jeu, on rend la première
	 * carte de listeCarte piochable.
	 * 
	 * @param piochable
	 * @param joueur
	 */
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