package Fourmi;

import java.util.Hashtable;

import Utils.Constantes;
import Utils.RepresentationFourmi;
import Utils.Constantes.Etat;

public class Pheromone {

	int tauxChasse;
	int tauxDepot;
	int tauxFemelleSexue;
	Hashtable<Etat, Integer> associationPheronomes = new Hashtable<Etat, Integer>();
	
	public Pheromone()
	{
		associationPheronomes.put(Etat.chasse, this.tauxChasse);
		associationPheronomes.put(Etat.nettoye, this.tauxDepot);
		associationPheronomes.put(Etat.copule, this.tauxFemelleSexue);
		this.tauxChasse = 0;
		this.tauxDepot = 0;
		this.tauxFemelleSexue = 0;
	}

	public void rafraichit(RepresentationFourmi representationFourmi) {
		if(representationFourmi.instanceFourmi().etat == Constantes.Etat.chasse)
		{
			this.tauxChasse = 100;
		}else if(representationFourmi.instanceFourmi().etat == Constantes.Etat.nettoye)
		{
			this.tauxDepot = 100;
		}else if(representationFourmi.instanceFourmi().etat == Constantes.Etat.copule)
		{
			this.tauxFemelleSexue = 100;
		}
	}
	public int recupererTaux(RepresentationFourmi representationFourmi) {
		return this.associationPheronomes.get(representationFourmi.instanceFourmi().etat);
	}
	
	
}
