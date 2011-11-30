package Environnement;

import java.awt.Event;
import java.util.Observable;
import java.util.Observer;

import InterfaceGraphique.Fenetre;

public class Dieu implements Observer{

	Monde monde;
	Chronos gestionTemps;
	Fenetre fenetre;
	
	public Dieu()
	{
		this.gestionTemps = new Chronos();
		this.gestionTemps.addObserver(this);
		this.monde = new Monde(gestionTemps.temps);
		this.fenetre = new Fenetre(this);
	}
	@Override
	public void update(Observable chronos, Object arg1) {
		Event evenement = (Event)arg1;
		gererEvenement(evenement);
	}
	
	public Monde instanceMonde()
	{
		return this.monde;
	}
	
	private void gererEvenement(Event evenement) {
		if(evenement.target.toString().contains("mois"))	
		{
			
		}else if(evenement.target.toString().contains("jour"))
		{
			System.out.print("Un jour est passe : ");
			this.gestionTemps.temps.afficherTemps();
			this.fenetre.repaint(this);
			System.out.println("Etat de la fourmilliere :");
			System.out.println(monde.recupererFourmilliere().afficherEtat());
		}
		
	}
	private void mettreAJourCarte() {
		
		
	}
	public void creeLaVie() {
		this.monde.start();
	}

}
