package Environnement;

import java.awt.Event;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Class Chronos qui gèrent le temps de notre monde
 * C'est également lui qui transmet les nouveaux évènements a notre classe Dieu
 * via les méthodes observer/observable
 * 
 * @author Gwen/Romain ;)
 */
public class Chronos extends Observable{
	Temps temps;
	ArrayList<Observer> obs;
	
	/**
	 * Constructeur, crée une nouvelle instance de temps
	 * et crée une liste vite d'observers
	 */
	public Chronos()
	{
		temps = new Temps(this);
		obs = new ArrayList<Observer>();
	}

	/**
	 * Nouveaux evenements à notifier aux observeurs
	 */
	public void nouveauJour() {
		this.setChanged();
		this.notifyObservers(new Event("Nouveau jour", 0, this));
	}
	
	public void nouvelleHeure()
	{
		this.setChanged();
		this.notifyObservers(new Event("Nouvelle heure", 0, this));
	}
	
	public Temps renvoiTemps()
	{
		return this.temps;
	}

	public void nouvelleAnnee() {
		this.setChanged();
		this.notifyObservers(new Event("Nouvelle Annee", 0, this));
		
	}

	public void nouveauMois() {
		this.setChanged();
		this.notifyObservers(new Event("Nouveau mois", 0, this));
		
	}

}
