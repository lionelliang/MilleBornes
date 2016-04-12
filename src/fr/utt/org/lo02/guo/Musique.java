package fr.utt.org.lo02.guo;

import java.io.File;
import java.io.FileInputStream;

import javazoom.jlme.util.Player;

public class Musique {
	static Thread pt = null;

	/**
	 * Procédure qui va lancer des sons.
	 * 
	 * @param af
	 */
	public static void play(final File af) {
		if (pt != null && pt.isAlive()) {
			return;
		} else {
			pt = new Thread() {
				public Player player;

				public void run() {
					try {
						player = new Player(new FileInputStream(af));
						player.play();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
		}
		pt.start();
	}

	public static void play(String fs) {
		play(new File(fs));
	}

	public static void play() {
		play("sounds\\bonjourdemain.mp3");
	}

}