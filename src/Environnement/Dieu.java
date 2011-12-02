package Environnement;

import java.awt.Event;
import java.util.Observable;
import java.util.Observer;

import InterfaceGraphique.Fenetre;


/**
 * Class Dieu qui gère le monde dans lequel se trouvent nos fourmis
 * Cette classe observe la classe Chronos afin de d'être au courant
 * de nouveaux evenements
 * 
 * @author Gwen/Romain
 */
public class Dieu implements Observer{

	Monde monde;
	Chronos gestionTemps;
	Fenetre fenetre;
	
	/**
	 * Constructeur de la classe
	 * Crée une nouvelle instance de Chronos, l'observe
	 * Puis crée un nouveau monde ainsi qu'une nouvelle interface
	 */
	public Dieu()
	{
		this.gestionTemps = new Chronos();
		this.gestionTemps.addObserver(this);
		this.monde = new Monde(gestionTemps.temps);
		this.fenetre = new Fenetre(this);
		
	}
	/**
	 * Fonction implémenté par le pattern observer/observable
	 * Elle catch les mises a jours des observables
	 */
	@Override
	public void update(Observable chronos, Object arg1) {
		Event evenement = (Event)arg1;
		gererEvenement(evenement);
	}

	/**
	 * En fonction de l'evenement recu, effectue diverss affichage
	 * @param evenement
	 */
	private void gererEvenement(Event evenement) {

		if(evenement.target.toString().contains("heure"))	
		{
			if(Temps.nbHeuresDepuisDebut() >  8){
				this.fenetre.repaint(this);
			}
			
			if(Temps.nbHeuresDepuisDebut() >  40 && (Temps.nbHeuresDepuisDebut()%10) == 0)
			{
				
				for(int i=0; i<instanceMonde().monde.length;i++){
					for(int j=0; j<instanceMonde().monde.length;j++){
						if(instanceMonde().monde[i][j].recupererTauxChasse() != 0){
							instanceMonde().monde[i][j].tauxPheromones.diminuerPheroChasse();
						}
					}
				}
				
				int l=0;
				while(l<instanceMonde().changementsPheromones.size()){
					if(instanceMonde().changementsPheromones.get(l).tauxPheromones.retournerPheroChasse() == 0){
						instanceMonde().changementsPheromones.remove(l);
					}
					l++;
				}
			}
		}	
	}
	
	/**
	 * Lance le monde
	 */
	public void creeLaVie() {
		this.monde.lancementMonde();
	}
	
	
	
	public Monde instanceMonde()
	{
		return this.monde;
	}

}