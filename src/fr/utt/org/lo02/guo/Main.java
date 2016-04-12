/**
 * 
 */
package fr.utt.org.lo02.guo;

import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Container;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author GUO et FOURNIER
 * 
 */
public class Main extends JFrame implements ActionListener, Runnable, Observer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	private ListeCartes listeCartes;
	private ListeDefausse listeDefausse;
	private ListeJoueurs listeJoueurs;
	private Compteur compteur;

	private Jeu jeu;

	private JLabel background, arriereplan, compteurLabel;
	private JPanel arriereplanPanel;
	private Container container = null;

	JMenuItem commencer, sauvegarderPartie, reprendrePartie, echap, apropos;
	
	int y1 = 8;
	int y2 = 1;
	int y3 = 4;

	public Main() throws HeadlessException {
		super();
		// TODO Auto-generated constructor stub
		init();
		setMenu();
		this.setVisible(true);
		listeJoueurs = new ListeJoueurs();
		listeCartes = new ListeCartes();
		listeCartes.initialListeCartes(this);
		listeDefausse = new ListeDefausse();
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
	 * Accesseur sur le jeu de cartes (pioche).
	 * 
	 * @return jeu de cartes
	 */
	public ListeCartes getListeCartes() {
		return listeCartes;
	}

	/**
	 * Getter sur la fenetre de jeu
	 * 
	 * @return
	 */
	public Main getFenetre() {
		return this;
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
	 * Getter sur le conteneur du jeu.
	 * 
	 * @return
	 */
	public Container getContainer() {
		return this.container;
	}

	/**
	 * Getter sur le compteur.
	 * 
	 * @return
	 */
	public Compteur getCompteur() {
		return compteur;
	}

	/**
	 * Setter sur le compteur.
	 * 
	 * @param compteur
	 */
	public void setCompteur(Compteur compteur) {
		this.compteur = compteur;
		this.compteur.addObserver(this);
	}
	/**
	 * Getter de l'attribut compteurLabel.
	 * @return
	 */
	public JLabel getCompteurLabel() {
		return compteurLabel;
	}

	public void setCompteurLabel(JLabel compteurLabel) {
		this.compteurLabel = compteurLabel;
		this.compteurLabel.setBounds(423, 540, 80, 20);
		// this.compteurLabel.setBackground(color);
		this.container.add(this.compteurLabel);
	}

	/**
	 * Procédure de création du menu.
	 */
	private void setMenu() {
		// TODO Auto-generated method stub
		JMenuBar jMenuBar = new JMenuBar();
		JMenu jeu = new JMenu("jeu");
		JMenu aide = new JMenu("aide");
		jMenuBar.add(jeu);
		jMenuBar.add(aide);

		commencer = new JMenuItem("Commencer");
		sauvegarderPartie = new JMenuItem("sauveGarder partie");
		reprendrePartie = new JMenuItem("reprendre partie");
		echap = new JMenuItem("echap");
		apropos = new JMenuItem("a propos");

		commencer.addActionListener(this);
		sauvegarderPartie.addActionListener(this);
		reprendrePartie.addActionListener(this);
		echap.addActionListener(this);
		apropos.addActionListener(this);

		jeu.add(commencer);
		jeu.add(sauvegarderPartie);
		jeu.add(reprendrePartie);
		jeu.add(echap);
		aide.add(apropos);

		this.setJMenuBar(jMenuBar);

	}

	@SuppressWarnings("deprecation")
	@Override
	/**
	 * Procédure du comportement du menu.
	 */
	public void actionPerformed(ActionEvent arg) {
		// TODO Auto-generated method stub
		if (arg.getSource() == commencer) {
			final JDialog choixDialog = new JDialog(this, "choix de Joueur");
			final JTextField nbrJoueur = new JTextField("2");
			final JTextField nbrRobot = new JTextField("0");
			JLabel nbrJoueurLabel = new JLabel("Joueur");
			JLabel nbrRobotLabel = new JLabel("Robot");
			final JButton confirmer = new JButton("confirmer");

			ActionListener confirmerActionListener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String str1 = nbrJoueur.getText();
					String str2 = nbrRobot.getText();
					int joueur1 = Integer.parseInt(str1);
					int joueur2 = Integer.parseInt(str2);
					System.out.println(joueur1);
					System.out.println(joueur2);
					if (joueur1 + joueur2 > 4) {
						nbrJoueur.setText("trop");
						nbrRobot.setText("trop");
						JOptionPane
								.showMessageDialog(getFenetre(),
										"Il faut que le nombre de joueurs total soit inferieur à 4");
					} else {
						choixDialog.dispose();
						final JDialog commencerDialog = new JDialog(
								getFenetre(), "nom de Joueur");
						final JLabel commencerLabel = new JLabel("commencer?");
						JButton commencerButton = new JButton("confirmer");
						commencerDialog.setBounds(450, 50, 220, 150);
						commencerDialog.setLayout(null);
						commencerLabel.setBounds(80, 10, 90, 22);
						commencerButton.setBounds(50, 70, 90, 22);

						commencerDialog.add(commencerLabel);
						commencerDialog.add(commencerButton);
						commencerDialog.show();
						commencerButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								background.setVisible(false);
								getFenetre().initialiserListeCartesLocation();
								for (int i = 0; i < listeJoueurs.size(); i++) {
									// System.out.println(i);
									getFenetre().initialiserJoueur(i);
								}
								getFenetre().getListeCartes().distribuer(
										listeJoueurs);

								listeJoueurs.get(0).setVisibilite(true);
								jeu = new Jeu(getFenetre());
								jeu.start();
								commencerDialog.dispose();
							}
						});

						for (int i = 0; i < joueur1; i++) {
							final JDialog nomDialog = new JDialog(getFenetre(),
									"nom de Joueur");
							final JTextField nomJoueur = new JTextField(
									"Joueur" + (i + 1));
							JButton confirmerButton = new JButton("confirmer");
							nomDialog.setBounds(450, 50, 220, 150);
							nomDialog.setLayout(null);
							nomJoueur.setBounds(80, 10, 90, 22);
							confirmerButton.setBounds(50, 70, 90, 22);

							nomDialog.add(nomJoueur);
							nomDialog.add(confirmerButton);
							nomDialog.show();
							confirmerButton
									.addActionListener(new ActionListener() {
										public void actionPerformed(
												ActionEvent e) {
											// TODO Auto-generated method stub
											listeJoueurs.add(new JoueurMB(
													getFenetre(), nomJoueur
															.getText(),
													getListeCartes(),
													getListeDefausse(),
													getListeJoueurs()));
											// System.out.println(listeJoueurs.getLast().getNomJoueur());
											nomDialog.dispose();
										}
									});
							// confirmerButton.addActionListener(confirmerAction);
						}
						for (int i = 0; i < joueur2; i++) {
							JoueurRobot joueurRobot = new JoueurRobot(
									getFenetre(), "Robot " + i,
									getListeCartes(), getListeDefausse(),
									getListeJoueurs());
							listeJoueurs.add(joueurRobot);
							joueurRobot.setJoueurVirtuel(true);

						}
						// System.out.println(listeJoueurs.getFirst().getNomJoueur());
					}
				}
			};
			choixDialog.setBounds(450, 50, 220, 150);
			choixDialog.setLayout(null);
			nbrJoueurLabel.setBounds(25, 10, 40, 22);
			nbrJoueur.setBounds(80, 10, 90, 22);
			nbrRobotLabel.setBounds(25, 40, 40, 22);
			nbrRobot.setBounds(80, 40, 90, 22);
			confirmer.setBounds(50, 70, 90, 22);

			choixDialog.add(nbrJoueurLabel);
			choixDialog.add(nbrRobotLabel);
			choixDialog.add(nbrJoueur);
			choixDialog.add(nbrRobot);
			choixDialog.add(confirmer);
			choixDialog.show();
			confirmer.addActionListener(confirmerActionListener);
		}
		if (arg.getSource() == sauvegarderPartie) {
			// sauvegarderPartie
			FileOutputStream fos;
			try {
				fos = new FileOutputStream("partie/partie.millebornes");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				System.out.println(this.listeCartes);
				oos.writeObject(this.listeCartes);
				oos.writeObject(this.listeDefausse);
				oos.writeObject(this.listeJoueurs);
				oos.close();
				fos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (arg.getSource() == reprendrePartie) {
			// reprendrePartie
			try {
				FileInputStream fis = new FileInputStream(
						"partie/partie.millebornes");
				ObjectInputStream ois = new ObjectInputStream(fis);
				this.listeCartes = (ListeCartes) ois.readObject();
				this.listeDefausse = (ListeDefausse) ois.readObject();
				this.listeJoueurs = (ListeJoueurs) ois.readObject();
				ois.close();
				fis.close();
				background.setVisible(false);
				if (jeu == null) {
					jeu = new Jeu(this);
					jeu.start();
				} else {
					listeCartes.montrer();
					listeDefausse.montrer();
					Iterator<JoueurMB> it = listeJoueurs.iterator();
					while (it.hasNext()) {
						JoueurMB joueur = it.next();
						joueur.metterAJour();
						joueur.getPileBataille().montrer();
						joueur.getPileBotte().montre();
						joueur.getPileVitesse().montrer();
						if (joueur.getVisibilite() == true) {
							this.getCompteurLabel().setVisible(true);
							this.compteur.clear();
							joueur.commencer();
						}
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (arg.getSource() == echap) {
			this.dispose();
		}
		if (arg.getSource() == apropos) {
			JOptionPane.showMessageDialog(this,
					"Developpé par Guillaume FOURNIER et GUO Fuliang");
		}
		// if(arg.getSource() == )
	}

	/**
	 * Procédure d'initialisation de l'interface.
	 */
	private void init() {
		// TODO Auto-generated method stub
		this.setTitle("Mille Bornes,Par GUO Fuliang et Guillaume FOURNIER");
		// this.setSize(940,741);
		this.setBounds(213, 0, 940, 741);
		setResizable(false);
		container = this.getContentPane();
		container.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// container.setBackground(color);
		// this.setIconImage(new ImageIcon("images/start.gif"));
		arriereplan = new JLabel(new ImageIcon("images/arriereplan.gif"));
		arriereplan.setBounds(0, 0, this.getWidth(), this.getHeight());

		arriereplanPanel = (JPanel) this.getContentPane();
		arriereplanPanel.setOpaque(false);
		// arriereplanPanel.setLayout(new FlowLayout());
		this.getLayeredPane().setLayout(null);
		this.getLayeredPane().add(arriereplan, new Integer(Integer.MIN_VALUE));
		background = new JLabel();
		background.setIcon(new ImageIcon("images/start_background.gif"));
		background.setLocation(0, 0);
		background.setSize(this.getWidth(), this.getHeight());
		container.add(background);
		background.setVisible(true);
	}

	/**
	 * Procédure de placement des cartes.
	 */
	public void initialiserListeCartesLocation() {
		Iterator<Carte> it = listeCartes.iterator();
		// Point p = new Point(0,0);
		while (it.hasNext()) {
			Carte c = it.next();
			// System.out.println(c);
			// c.setCanclick(true);
			c.setPosition(listeCartes.getPosition());
			c.setLocation(listeCartes.getPosition());
			// c.setLocation(p);
			// p=new Point(p.x+10,p.y+5);
			c.setVisible(true);
			container.add(c);
			// c.setFace(true);
			// c.montrer();
			// c.cacher();
			// Musique.play(c.getMusique());
		}
	}

	/**
	 * Procédure d'initialisation des joueurs.
	 * 
	 * @param i
	 */
	public void initialiserJoueur(int i) {
		switch (i) {
		case (0): {
			listeJoueurs.get(0).setPositionJoueur(new Point(750, 190 - y1));
			JLabel nom = new JLabel(listeJoueurs.get(0).getNomJoueur());
			JLabel avancement = new JLabel();
			listeJoueurs.get(0).setNomJoueurLabel(nom);
			nom.setBounds(850, 200 - y1, 40 * 2, 22);
			listeJoueurs.get(0).setNbrAvancement(avancement);
			listeJoueurs.get(0).getAvancement()
					.setPosition(new Point(850, 240 - y1));
			avancement.setBounds(850, 240 - y1, 40, 22);
			container.add(nom);
			container.add(avancement);
			avancement.setVisible(true);
			nom.setVisible(true);
			listeJoueurs.get(0).getJeuJoueur()
					.setPosition(new Point(760, 200 - y1));
			listeJoueurs.get(0).getFeu().setPosition(new Point(760, 310 - y1));
			listeJoueurs.get(0).getPileBataille()
					.setPosition(new Point(760, 310 - y1));
			listeJoueurs.get(0).getPileVitesse()
					.setPosition(new Point(850, 310 - y1));
			listeJoueurs.get(0).getPileBotte()
					.setPosition(new Point(760, 415 - y1));
			listeJoueurs.get(0).setRegion(
					new Rectangle(750, 190 - y1, 190, 330));
			listeJoueurs.get(0).finir(false);
			break;
		}
		case (1): {
			listeJoueurs.get(1).setPositionJoueur(new Point(0, 190 - y1));
			JLabel nom = new JLabel(listeJoueurs.get(1).getNomJoueur());
			JLabel avancement = new JLabel();
			nom.setBounds(10, 200 - y1, 40 * 2, 22);
			listeJoueurs.get(1).setNbrAvancement(avancement);
			avancement.setBounds(10, 240 - y1, 40, 22);
			listeJoueurs.get(1).getAvancement()
					.setPosition(new Point(10, 240 - y1));
			container.add(nom);
			container.add(avancement);
			nom.setVisible(true);
			avancement.setVisible(true);
			listeJoueurs.get(1).getJeuJoueur()
					.setPosition(new Point(100, 200 - y1));
			listeJoueurs.get(1).getFeu().setPosition(new Point(100, 310 - y1));
			listeJoueurs.get(1).getPileBataille()
					.setPosition(new Point(100, 310 - y1));
			listeJoueurs.get(1).getPileVitesse()
					.setPosition(new Point(10, 310 - y1));
			listeJoueurs.get(1).getPileBotte()
					.setPosition(new Point(10, 415 - y1));
			listeJoueurs.get(1).setRegion(new Rectangle(0, 190, 190, 330 - y1));
			listeJoueurs.get(1).finir(false);
			break;
		}
		case (2): {
			listeJoueurs.get(2).setPositionJoueur(new Point(50, 0));
			JLabel nom = new JLabel(listeJoueurs.get(2).getNomJoueur());
			JLabel avancement = new JLabel();
			listeJoueurs.get(2).setNomJoueurLabel(nom);
			nom.setBounds(80, 20 + y3, 40 * 2, 22);
			listeJoueurs.get(2).setNbrAvancement(avancement);
			avancement.setBounds(245, 20 + y3, 40, 22);
			listeJoueurs.get(2).getAvancement()
					.setPosition(new Point(245, 20 + y3));
			container.add(nom);
			container.add(avancement);
			nom.setVisible(true);
			avancement.setVisible(true);
			listeJoueurs.get(2).getJeuJoueur()
					.setPosition(new Point(360, 60 + y3));
			listeJoueurs.get(2).getFeu().setPosition(new Point(270, 60 + y3));
			listeJoueurs.get(2).getPileBataille()
					.setPosition(new Point(270, 60 + y3));
			listeJoueurs.get(2).getPileVitesse()
					.setPosition(new Point(180, 60 + y3));
			listeJoueurs.get(2).getPileBotte()
					.setPosition(new Point(60, 60 + y3));
			listeJoueurs.get(2).setRegion(new Rectangle(50, 0, 410, 180));
			listeJoueurs.get(2).finir(false);
			break;
		}
		case (3): {
			listeJoueurs.get(3).setPositionJoueur(new Point(480, 0));
			JLabel nom = new JLabel(listeJoueurs.get(3).getNomJoueur());
			JLabel avancement = new JLabel();
			listeJoueurs.get(3).setNomJoueurLabel(nom);
			nom.setBounds(515, 20 + y1, 40 * 2, 22);
			listeJoueurs.get(3).setNbrAvancement(avancement);
			avancement.setBounds(715, 20 + y1, 40, 22);
			listeJoueurs.get(3).getAvancement().setPosition(new Point(715, 20));
			container.add(nom);
			container.add(avancement);
			nom.setVisible(true);
			avancement.setVisible(true);
			listeJoueurs.get(3).getJeuJoueur().setPosition(new Point(490, 60));
			listeJoueurs.get(3).getFeu().setPosition(new Point(580, 60 + y1));
			listeJoueurs.get(3).getPileBataille()
					.setPosition(new Point(580, 60));
			listeJoueurs.get(3).getPileVitesse()
					.setPosition(new Point(670, 60));
			listeJoueurs.get(3).getPileBotte().setPosition(new Point(760, 60));
			listeJoueurs.get(3).setRegion(new Rectangle(480, 0, 410, 180));
			listeJoueurs.get(3).finir(false);
			break;
		}
		}
	}

	/**
	 * Procédure d'affichage du jeu du joueur qui doit jouer.
	 * 
	 * @param i
	 */
	public void deplier(int i) {
		listeJoueurs.get(i).commencer();
	}

	/**
	 * Procédure qui permet de cacher le jeu lorsque le joueur a fini de jouer.
	 * 
	 * @param i
	 */
	public void recuperer(int i) {
		listeJoueurs.get(i).finir(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
	}
/**
 * Procédure qui va déplacer les cartes dans la zone de jeu.
 * @param carte
 * @param de
 * @param a
 */
	public static void mouvoir(Carte carte, Point de, Point a) {
		if (a.x != de.x) {
			double k = (1.0) * (a.y - de.y) / (a.x - de.x);
			double b = a.y - a.x * k;
			int distance = 0;
			if (de.x < a.x)
				distance = 20;
			else {
				distance = -20;
			}
			for (int i = de.x; Math.abs(i - a.x) > 20; i += distance) {
				double y = k * i + b;
				carte.setLocation(i, (int) y);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			carte.setLocation(a);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main vue = new Main();
		Thread vueTread = new Thread(vue);
		vueTread.start();

	}

	@Override
	/**
	 * Procédure d'affichage du compteur
	 */
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		this.compteurLabel.setText(compteur.getValeur() + " seconds");
	}

}
