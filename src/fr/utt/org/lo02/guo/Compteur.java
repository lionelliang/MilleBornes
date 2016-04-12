package fr.utt.org.lo02.guo;

import java.io.Serializable;
import java.util.Observable;

public class Compteur extends Observable implements Runnable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static int TEMPORISATION = 1000;
	private int compteur = 0;

	public void compter() {
		Thread t = new Thread(this);
		t.start();
	}

	public int getValeur() {
		return this.compteur;
	}

	public void clear() {
		compteur = 0;
	}

	public void attendre() {
		try {
			Thread.sleep(Compteur.TEMPORISATION);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			compteur++;
			setChanged();
			notifyObservers();
			attendre();
		}

	}
}
