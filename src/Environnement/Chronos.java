package Environnement;

import java.awt.Event;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Chronos extends Observable{
	Temps temps;
	ArrayList<Observer> obs;
	
	public Chronos()
	{
		temps = new Temps(this);
		obs = new ArrayList<Observer>();
	}

	public void nouveauJour() {
		this.setChanged();
		this.notifyObservers(new Event("Nouveau jour", 0, this));
	}
	
	public void nouvelleHeure()
	{
		this.setChanged();
		this.notifyObservers(new Event("Nouvelle Heure", 0, this));
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
