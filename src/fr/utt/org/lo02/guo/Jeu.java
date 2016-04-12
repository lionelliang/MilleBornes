package fr.utt.org.lo02.guo;

import javax.swing.JLabel;

/**
 * .
 * 
 * @author Guillaume Fournier Classe qui permet de gérer une partie (Moteur du jeu).
 */
public class Jeu extends Thread {

	private Main m;
	private ListeJoueurs listeJoueurs;
	private Compteur compteur;

	/**
	 * Constructeur de la classe Jeu.
	 * @param main Fenêtre du jeu.
	 */
	public Jeu(Main main) {
		m = main;
		this.listeJoueurs = main.getListeJoueurs();
		this.compteur = new Compteur();
		m.setCompteur(compteur);
		m.setCompteurLabel(new JLabel());
		this.compteur.compter();
	}

	@SuppressWarnings("deprecation")
	@Override
	/**
	 * Procédure de contrôle des tours des joueurs
	 */
	public void run() {
		// TODO Auto-generated method stub
		
		for (int i = 0; true; i = (i + 1) % listeJoueurs.size()) {
			JoueurMB joueur = listeJoueurs.get(i);
			if (joueur.getVisibilite() == true) {
				m.getCompteurLabel().setVisible(true);
				compteur.clear();
				m.deplier(i);
				if (joueur.isJoueurVirtuel()) {
					JoueurRobot joueurR = (JoueurRobot) joueur;
					joueurR.jouer();
				}
			}
			while (joueur.getVisibilite() == true) {
				if ((joueur.isAfficher() == false)
						&& (joueur.isVisibilite() == true)) {
					m.deplier(i);
				}
				if (compteur.getValeur() >= 300) {
					joueur.tirerCarte(null);
					joueur.jeterCarte(joueur.getJeuJoueur().get(0));
				}
			}
			m.getCompteurLabel().setVisible(false);
			if (joueur.getAvancement().getAvancementTotal() == 1000
					|| m == null) {
				Thread.currentThread().suspend();
				break;
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
