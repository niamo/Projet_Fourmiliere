package Fourmi;


import Utils.Constantes;
import Utils.RepresentationFourmi;

public class Pheromone {

	int tauxChasse;
	int tauxDepot;
	int tauxFemelleSexue;
	
	public Pheromone()
	{
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
		if(representationFourmi.instanceFourmi().etat == Constantes.Etat.chasse)
		{
			return retournerPheroChasse();
		}else if(representationFourmi.instanceFourmi().etat == Constantes.Etat.nettoye)
		{
			return this.retournerPheroDepot();
		}else
		{
			return this.retournerPheroCopulation();
		}
	}
	
	public int retournerPheroChasse()
	{
		return this.tauxChasse;
	}
	
	public int retournerPheroDepot()
	{
		return this.tauxDepot;
	}
	
	public int retournerPheroCopulation()
	{
		return this.tauxFemelleSexue;
	}
	
	public void diminuerPheroChasse(){
		this.tauxChasse = this.tauxChasse - 20;
	}
	
	
}
