package fr.utt.org.lo02.guo;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * 
 * @author Guillaume Fournier Classe qui permet la création de nouveaux joueurs.
 */
public class JoueurMB implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	/*
	 * (non-javadoc)
	 */
	protected Main f;
	protected JLabel nomJoueurLabel;
	protected String nomJoueur;
	protected boolean visibilite;
	protected boolean joueurVirtuel = false;
	protected boolean afficher = false;

	protected ListeCartes listeCartes;
	protected ListeDefausse listeDefausse;
	protected ListeJoueurs listeJoueurs;

	protected Avancement avancement = null;
	protected PileBotte pileBotte = null;
	protected PileBataille pileBataille = null;
	protected JeuJoueur jeuJoueur = null;
	protected PileVitesse pileVitesse = null;
	protected Feu feu = null;
	protected Point positionJoueur;
	protected Rectangle region;
	protected JLabel nbrAvancement;

	/**
	 * Constructeur de la classe JoueurMB
	 * 
	 * @param f
	 *            fenêtre du jeu.
	 * 
	 * @param nomJoueur
	 *            nom donné au joueur en début de partie
	 * @param listeCartes
	 *            jeu de cartes
	 * @param listeDefausse
	 *            Liste de défausse de la partie
	 */
	public JoueurMB(Main f, String nomJoueur, ListeCartes listeCartes,
			ListeDefausse listeDefausse, ListeJoueurs listeJoueurs) {
		super();
		this.f = f;
		this.nomJoueur = nomJoueur;
		this.visibilite = false;
		this.listeCartes = listeCartes;
		this.listeDefausse = listeDefausse;
		this.listeJoueurs = listeJoueurs;

		this.avancement = new Avancement();
		this.pileBotte = new PileBotte();
		this.pileBataille = new PileBataille();
		this.jeuJoueur = new JeuJoueur();
		this.pileVitesse = new PileVitesse();
		this.feu = new Feu();
	}

	/**
	 * Getter de l'attribut afficher
	 * 
	 * @return état de l'attribut
	 */
	public boolean isAfficher() {
		return afficher;
	}

	/**
	 * Setter de l'attribut afficher
	 * 
	 * @param afficher
	 *            true ou false
	 */
	public void setAfficher(boolean afficher) {
		this.afficher = afficher;
	}

	/**
	 * Accesseur de la liste des joueurs de la partie.
	 * 
	 * @return Liste des joueurs de la partie.
	 */
	public ListeJoueurs getListeJoueurs() {
		return listeJoueurs;
	}

	/**
	 * Setter de la liste des joueurs de la partie.
	 * 
	 * @param listeJoueurs
	 */
	public void setListeJoueurs(ListeJoueurs listeJoueurs) {
		this.listeJoueurs = listeJoueurs;
	}

	/**
	 * Getter de l'attribut joueurVirtuel
	 * 
	 * @return true ou false selon si le joueur est un joueur virtuel ou non.
	 */
	public boolean isJoueurVirtuel() {
		return joueurVirtuel;
	}

	/**
	 * Setter de l'attribut joueurVirtuel
	 * 
	 * @param true ou false
	 */
	public void setJoueurVirtuel(boolean joueurVirtuel) {
		this.joueurVirtuel = joueurVirtuel;
	}

	/**
	 * getter de l'attribut PileBotte
	 * 
	 * @return pileBotte
	 */
	public PileBotte getPileBotte() {
		return pileBotte;
	}

	/**
	 * Setter sur l'attribut pileBotte
	 * 
	 * @param pileBotte
	 */
	public void setPileBotte(PileBotte pileBotte) {
		this.pileBotte = pileBotte;
	}

	/**
	 * Getter de l'attribut NomJoueurLabel
	 * 
	 * @return nomJoueurLabel
	 */
	public JLabel getNomJoueurLabel() {
		return nomJoueurLabel;
	}

	/**
	 * Setter de l'attribut nomJoueurLabel
	 * 
	 * @param nomJoueurLabel
	 */
	public void setNomJoueurLabel(JLabel nomJoueurLabel) {
		this.nomJoueurLabel = nomJoueurLabel;
	}

	public JLabel getNbrAvancement() {
		return nbrAvancement;
	}

	public void setNbrAvancement(JLabel nbrAvancement) {
		this.nbrAvancement = nbrAvancement;
		this.metterAJour();
	}

	/**
	 * Procédure permettant la mise à jour du score du joueur.
	 */
	public void metterAJour() {
		this.nbrAvancement.setText(Integer.toString(avancement
				.getAvancementTotal()));
	}

	public boolean isVisibilite() {
		return visibilite;
	}

	/**
	 * Methode qui permet de modifier la visibilité d'une carte
	 * 
	 * @param visibilite
	 */
	public void setVisibilite(boolean visibilite) {
		this.visibilite = visibilite;
	}

	/**
	 * Setter permettant de voir si l
	 * 
	 * @return
	 */
	public boolean getVisibilite() {
		return visibilite;
	}

	/**
	 * getter de l'attribut pileBataille
	 * 
	 * @return
	 */
	public PileBataille getPileBataille() {
		return pileBataille;
	}

	/**
	 * setter de l'attribut pileBataiile
	 * 
	 * @param pileBataille
	 */
	public void setPileBataille(PileBataille pileBataille) {
		this.pileBataille = pileBataille;
	}

	public String getNomJoueur() {
		return nomJoueur;
	}

	/**
	 * Setter du nom du joueur
	 * 
	 * @param nomJoueur
	 */
	public void setNomJoueur(String nomJoueur) {
		this.nomJoueur = nomJoueur;
	}

	/**
	 * Getter sur le nombre de km parcourus par le joueur
	 * 
	 * @return nombre de km parcourus par le joueur
	 */
	public Avancement getAvancement() {
		return avancement;
	}

	/**
	 * Setter sur le nombre de km parcourus par le joueur.
	 * 
	 * @param avancement
	 */
	public void setAvancement(Avancement avancement) {
		this.avancement = avancement;
	}

	/**
	 * Getter sur le jeu du joueur
	 * 
	 * @return jeu du joueur
	 */
	public JeuJoueur getJeuJoueur() {
		return jeuJoueur;
	}

	/**
	 * Setter sur le jeu du joueur.
	 * 
	 * @param jeuJoueur
	 */
	public void setJeuJoueur(JeuJoueur jeuJoueur) {
		this.jeuJoueur = jeuJoueur;
	}

	/**
	 * Accesseur à la pile de limitation de vitesse
	 * 
	 * @return pile de limitation de vitesse
	 */
	public PileVitesse getPileVitesse() {
		return pileVitesse;
	}

	/**
	 * Setter de la pile de limitation de vitesse
	 * 
	 * @param pileVitesse
	 */
	public void setPileVitesse(PileVitesse pileVitesse) {
		this.pileVitesse = pileVitesse;
	}

	/**
	 * Accesseur à la pile Feu Vert
	 * 
	 * @return pile Feu Vert
	 */
	public Feu getFeu() {
		return feu;
	}

	/**
	 * Setter sur la pile Feu
	 * 
	 * @param feu
	 */
	public void setFeu(Feu feu) {
		this.feu = feu;
	}

	/**
	 * Getter sur le point de la position de la zone du joueur.
	 * 
	 * @return point de la position de la zone du joueur
	 */
	public Point getPositionJoueur() {
		return positionJoueur;
	}

	/**
	 * Setter sur le point de la position de la zone du joueur.
	 * 
	 * @param positionJoueur
	 */
	public void setPositionJoueur(Point positionJoueur) {
		this.positionJoueur = positionJoueur;
	}

	public JFrame getF() {
		return f;
	}

	public void setF(Main f) {
		this.f = f;
	}

	/**
	 * Accesseur sur le jeu de cartes (pioche).
	 * 
	 * @return jeu de cartes
	 */
	public ListeCartes getListeCartes() {
		return listeCartes;
	}

	/**
	 * Setter sur la pioche
	 * 
	 * @param listeCartes
	 */
	public void setListeCartes(ListeCartes listeCartes) {
		this.listeCartes = listeCartes;
	}

	/**
	 * Getter sur la pile de défausse.
	 * 
	 * @return pile de défausse
	 */
	public ListeDefausse getListeDefausse() {
		return listeDefausse;
	}

	/**
	 * Setter sur la pile de défausse.
	 * 
	 * @param listeDefausse
	 */
	public void setListeDefausse(ListeDefausse listeDefausse) {
		this.listeDefausse = listeDefausse;
	}

	public Rectangle getRegion() {
		return region;
	}

	public void setRegion(Rectangle region) {
		this.region = region;
	}

	/**
	 * Procédure qui permet de montrer les cartes du joueur et d'en tirer une
	 * dans la pioche.
	 */
	public void commencer() {// qui permet de fair tirer et montrer tous les
								// carte au début
		// this.visibilite = true;
		if (!this.isJoueurVirtuel()) {
			this.listeCartes.setPiochable(true, this);
			this.listeDefausse.setPiochable(true, this);
		}
		if (this.joueurVirtuel)
			this.tirerCarte(null);
		jeuJoueur.montrer(false);
		this.afficher = true;
		// avancement.montrer();
		// pileBataille.montrer();
		// pileVitesse.montrer();
		// feu.montrer();
	}

	/**
	 * Procédure qui permet de cacher les cartes du joueur.
	 * 
	 * @param prochain
	 */
	public void finir(boolean prochain) {// qui permet de cacher les carte
		this.visibilite = false;
		jeuJoueur.cacher();
		this.afficher = false;
		if (prochain) {
			for (int i = 0; i < f.getListeJoueurs().size(); i++) {
				if (f.getListeJoueurs().get(i) == this) {
					f.getListeJoueurs()
							.get((i + 1) % f.getListeJoueurs().size())
							.setVisibilite(true);
					break;
				}
			}

		}
		// avancement.cacher();
		// pileBataille.cacher();
		// pileVitesse.cacher();
		// feu.cacher();
	}

	/**
	 * Methode qui rajoute une carte passée en parametre dans le jeu du joueur
	 * 
	 * @param c
	 */
	public void recevoir(Carte c) {// qui permet de distribuer les cartes à ce
									// joueur
		jeuJoueur.add(c);
		c.setJoueur(this);
		// Musique.play("sounds\\piocher.mp3");
		Main.mouvoir(c,
				SwingUtilities.convertPoint(c, c.getLocation(), c.getParent()),
				jeuJoueur.getPosition());
		// jeuJoueur.cacher();
	}

	/**
	 * Methode appelée lorsque le joueur a atteint les 700 kms requis
	 * 
	 * @return
	 */
	public void gagner() {// fair quelque chose pour gagner
		Musique.play("sounds\\gagner.mp3");
		JOptionPane.showMessageDialog(null, this.nomJoueur + " a gagné",
				"Felicitation", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Methode qui va retirer une carte de la pioche pour la rajouter dans le
	 * jeu du joueur
	 * 
	 * @return
	 */
	public Carte tirerCarte(Carte carte) {// qui permet de tirer carte de chaque
											// tour
		Musique.play("sounds\\piocher.mp3");
		if (this.joueurVirtuel == true) {
			// tirer automatiquement
			if (!listeCartes.isEmpty()) {
				carte = listeCartes.piocherCarte();
				jeuJoueur.add(carte);
				carte.setJoueur(this);// pour connaitre le joueur
				carte.setPosition(jeuJoueur.getPosition());
				carte.setLocation(jeuJoueur.getPosition());
				carte.montrer();
			} else if (!listeDefausse.isEmpty()) {// si liste de cartes est vide
													// liste de défausse devient
													// liste de cartes
				carte = listeDefausse.pop();
				if (!listeDefausse.isEmpty())
					listeDefausse.getLast().setVisible(true);
				jeuJoueur.add(carte);
				carte.setJoueur(this);
				jeuJoueur.cacher();
			} else {
				carte = null;
			}
		} else {
			if (!this.listeCartes.isEmpty()
					&& (listeCartes.getFirst() == carte)) {
				listeCartes.pop();
				jeuJoueur.add(carte);
				carte.setJoueur(this);// pour connaitre le joueur
				jeuJoueur.montrer(true);
				carte.setPiochable(false);
				listeCartes.setPiochable(false, this);
				listeDefausse.setPiochable(false, this);
				if (!listeDefausse.isEmpty())
					listeDefausse.getFirst().setPiochable(false);
			} else if (!this.listeDefausse.isEmpty()
					&& (this.listeDefausse.getFirst() == carte)) {
				listeDefausse.pop();
				jeuJoueur.add(carte);
				carte.setJoueur(this);// pour connaitre le joueur
				jeuJoueur.montrer(true);
				carte.setPiochable(false);
				listeCartes.setPiochable(false, this);
				listeDefausse.setPiochable(false, this);
				if (!listeCartes.isEmpty())
					listeCartes.getFirst().setPiochable(false);
			}
		}
		return carte;
	}

	/**
	 * Methode qui va retirer une carte du jeu du joueur pour la rajouter dans
	 * la liste de défausse
	 * 
	 * @param carte
	 */
	public boolean jeterCarte(Carte carte) {
		if (carte != null) {
			Musique.play("sounds\\jeter.mp3");
			jeuJoueur.remove(carte);
			listeDefausse.push(carte);
			carte.setJoueur(null);
			listeDefausse.montrer();
			carte.setCanclick(false);
			this.finir(true);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Methode qui permet au joueur de jouer une carte etape
	 * 
	 * @param carteEtape
	 *            carte que le joueur désire jouer
	 */
	public boolean jouerEtape(CarteEtape carteEtape) {
		if (carteEtape.possibiliteJouer(this)) {
			if (avancement.getAvancementTotal() + carteEtape.getValeur() < 700) {// si
																					// pas
																					// trop
				Musique.play(carteEtape.getMusique());
				jeuJoueur.remove(carteEtape);
				avancement.add(carteEtape);
				carteEtape.setVisible(false);
				// avancement.montrer();
				carteEtape.setCanclick(false);
				avancement.setAvancementTotal(carteEtape.getValeur());
				this.metterAJour();
				this.finir(true);
				return true;
			} else if (avancement.getAvancementTotal() + carteEtape.getValeur() > 700) {// si
																						// trop
				JOptionPane.showMessageDialog(f, "superieur a 700!");
				System.out.println("superieur");
				return false;
			} else if (avancement.getAvancementTotal() + carteEtape.getValeur() == 700) {// si
																							// gagner
				jeuJoueur.remove(carteEtape);
				avancement.add(carteEtape);
				avancement.montrer();
				carteEtape.setCanclick(false);
				avancement.setAvancementTotal(carteEtape.getValeur());
				this.metterAJour();
				avancement.setAvancementTotal(carteEtape.getValeur());
				JOptionPane.showMessageDialog(f, this.nomJoueur + " a gagné!");
				System.out.println("gagné");
				this.finir(false);
				gagner();
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * Methode qui permet au joueur de jouer une carte attaque
	 * 
	 * @param carteAttaque
	 *            carte attaque que le joueur désire jouer
	 * @param joueur
	 *            joueur auquel la carte attaque va etre ajoutée dans sa pile de
	 *            bataille
	 */
	public boolean jouerAttaque(CarteAttaque carteAttaque, JoueurMB joueur) {
		if (carteAttaque.possibiliteJouerAttaque(this, joueur)) {
			Musique.play(carteAttaque.getMusique());
			jeuJoueur.remove(carteAttaque);
			if (carteAttaque.getNomAttaque() == "Limitation de vitesse") {
				joueur.pileVitesse.add(carteAttaque);
				joueur.getPileVitesse().montrer();
			} else if (carteAttaque.getNomAttaque() == "Feu rouge") {
				joueur.feu.add(carteAttaque);
				if (!pileBataille.isEmpty())
					pileBataille.getLast().setVisible(false);
				feu.montrer();
				// joueur.pileBataille.add(carteAttaque);
				// joueur.pileBataille.montrer();
			} else {
				joueur.getPileBataille().add(carteAttaque);
				if (!feu.isEmpty())
					feu.getLast().setVisible(false);
				joueur.getPileBataille().montrer();
			}
			carteAttaque.setJoueur(joueur);
			carteAttaque.setCanclick(false);
			Carte c = this.correspondre(carteAttaque, joueur);
			if (c != null) {
				if (joueur.getAvancement().getAvancementTotal() + 300 < 700) {// si
																				// pas
																				// trop
					joueur.jouerCoupFourre((CarteBotte) c);
					this.finir(false);
				} else if (joueur.getAvancement().getAvancementTotal() + 300 > 700) {// si
																						// trop
				} else if (joueur.getAvancement().getAvancementTotal() + 300 == 700) {
					joueur.getAvancement().setAvancementTotal(300);
					joueur.metterAJour();
					joueur.gagner();
				}
			} else
				this.finir(true);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Methode qui va permettre au joueur de jouer une carte parade
	 * 
	 * @param carteParade
	 *            carte parade que le joueur désire jouer
	 */
	public boolean jouerParade(CarteParade carteParade) {
		if (carteParade.possibiliteJouerParade(this)) {
			Musique.play(carteParade.getMusique());
			jeuJoueur.remove(carteParade);
			if (carteParade.getNomParade() == "Fin de limitation") {
				pileVitesse.add(carteParade);
				pileVitesse.montrer();
			} else if (carteParade.getNomParade() == "Feu Vert") {
				System.out.println("jouer feu vert");
				feu.add(carteParade);
				if (!pileBataille.isEmpty())
					pileBataille.getLast().setVisible(false);
				feu.montrer();
				// pileBataille.add(carteParade);
				// pileBataille.montrer();
			} else {
				pileBataille.add(carteParade);
				if (!feu.isEmpty())
					feu.getLast().setVisible(false);
				pileBataille.montrer();
			}
			carteParade.setCanclick(false);
			this.finir(true);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Méthode permettant au joueur de jouer un carte Botte
	 * 
	 * @param carteBotte
	 *            carte Botte que le joueur désire jouer.
	 * @return
	 */
	public boolean jouerBotte(CarteBotte carteBotte) {
		if (carteBotte.possibiliteJouerBotte(this)) {
			Musique.play(carteBotte.getMusique());
			this.correspondre(carteBotte);
			this.jeuJoueur.remove(carteBotte);
			this.pileBotte.add(carteBotte);
			carteBotte.setCanclick(false);
			pileBotte.montre();
			if (avancement.getAvancementTotal() + 100 < 700) {// si pas trop
				avancement.setAvancementTotal(100);
				this.metterAJour();
			} else if (avancement.getAvancementTotal() + 100 > 700) {// si trop
			} else if (avancement.getAvancementTotal() + 100 == 700) {
				avancement.setAvancementTotal(100);
				this.metterAJour();
				this.gagner();
			}
			this.finir(true);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Méthode qui réalise un coup fourré et les modifications qui y sont liées
	 * 
	 * @param carteBotte
	 * @return
	 */
	public boolean jouerCoupFourre(CarteBotte carteBotte) {
		Musique.play(carteBotte.getMusique());
		this.jeuJoueur.remove(carteBotte);
		this.tirerCarte(null);
		this.pileBotte.add(carteBotte);
		carteBotte.setCanclick(false);
		carteBotte.montrer();
		pileBotte.montre();
		avancement.setAvancementTotal(300);
		this.metterAJour();
		this.setVisibilite(true);
		// this.tirerCarte();
		// this.commencer();
		return true;
	}

	/**
	 * Méthode qui vérifie que le joueur a déposé son premier feu vert
	 * 
	 * @return
	 */
	public boolean verifierFeuVert() {
		Iterator<Carte> it = pileBotte.iterator();
		while (it.hasNext()) {
			if (it.next().getNumCarte() == 44) {
				return true;
			}
		}
		// if(pileBataille.isEmpty()){
		if (feu.isEmpty()) {
			return false;
		} else {
			return (feu.getLast().getNumCarte() == 35);
			// return (pileBataille.getLast().getNumCarte()==35);
		}
	}

	/**
	 * Méthode qui vérifie si le joueur peut jouer une carte feu vert ou non.
	 * 
	 * @return
	 */
	public boolean verifierJouerFeuVert() {
		// if(feu.isEmpty() && !this.accident()){
		Iterator<Carte> it = pileBotte.iterator();
		while (it.hasNext()) {
			if (it.next().getNumCarte() == 44) {
				return false;
			}
		}
		if (feu.isEmpty() && !this.accident()) {
			return true;
		} else if (feu.getLast().getNumCarte() == 25 && !this.accident()) {
			// }else if(pileBataille.getLast().getNumCarte()==25 &&
			// !this.accident()){
			return true;
		} else if (!this.accident() && !this.verifierFeuVert()) {
			return true;
		} else
			return false;
	}

	/**
	 * Méthode qui vérifie si la vitesse du joueur est limitée * @return
	 */
	public boolean limiteVitesse() {
		if (pileVitesse.isEmpty()) {
			return false;
		} else {
			return (pileVitesse.getLast().getNumCarte() == 24);
		}
	}

	/**
	 * Methode qui vérifie si le joueur est accidenté
	 * 
	 * @return
	 */
	public boolean accident() {
		if (pileBataille.isEmpty()) {
			return false;
		} else if (CarteAttaque.correspondre(pileBataille.getFirst(), this) != null) {
			return false;
		} else
			return ((Carte) pileBataille.getLast()).getNumCarte() < 26;
	}

	/**
	 * Méthode qui vérifie si des cartes correspondent aux cartes botte jouées
	 * dans les différentes piles afin de pouvoir les enlever, le cas échéant.
	 * 
	 * @param carte
	 * @return
	 */
	public boolean correspondre(Carte carte) {
		if (this.limiteVitesse() && (carte.getNumCarte() == 44)) {
			Carte c = this.pileVitesse.pop();
			c.setVisible(false);
			this.pileVitesse.montrer();
			return true;
		}
		if (!this.feu.isEmpty())
			if (!this.verifierFeuVert() && (carte.getNumCarte() == 44)) {
				Carte c = this.feu.pop();
				c.setVisible(false);
				this.feu.montrer();
				return true;
			}
		if (this.accident()
				&& (carte.getNumCarte() == this.pileBataille.getLast()
						.getNumCarte() + 20)) {
			Carte c = this.pileBataille.pop();
			c.setVisible(false);
			this.pileBataille.montrer();
			return true;
		}
		return false;
	}

	/**
	 * Méthode qui va vérifier si le joueur attaqué possède une carte botte
	 * correspondant à l'attaque dans son jeu, en vue du coup fourré.
	 * 
	 * @param carte
	 *            Carte attaque jouée.
	 * @param joueur
	 * @return La carte botte si elle correspond à la carte attaque reçue.
	 */
	public Carte correspondre(Carte carte, JoueurMB joueur) {
		if (joueur.getJeuJoueur().isEmpty()) {
			return null;
		} else {
			Iterator<Carte> it = joueur.getJeuJoueur().iterator();
			while (it.hasNext()) {
				Carte c = it.next();
				if (carte.getNumCarte() == c.getNumCarte() - 20) {
					return c;
				} else if (carte.getNumCarte() == 25 && c.getNumCarte() == 44) {
					return c;
				} else
					return null;
			}
			return null;
		}
	}

	/**
	 * Méthode qui va chercher le joueur suivant.
	 * 
	 * @return joueur suivant celui qui joue.
	 */
	public JoueurMB suivant() {
		JoueurMB joueur;
		for (int i = 0; i < listeJoueurs.size(); i++) {
			joueur = listeJoueurs.get(i);
			if (joueur == this) {
				return listeJoueurs.get((i + 1) % listeJoueurs.size());
			}
		}
		return null;
	}
}