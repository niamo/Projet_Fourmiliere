package Environnement;

import java.util.ArrayList;
import Fourmi.Pheromone;
import Fourmi.Proie;
import Utils.Aleatoire;
import Utils.Position;
import Utils.RepresentationFourmi;
import Utils.RepresentationFourmilliere;
import Utils.RepresentationProie;
import Utils.Representation;

public class Region {

	public ArrayList<Representation> element;
	Position coordonnees;
	public Pheromone tauxPheromones;
	Monde monde;
	int ajoutProie = 0;

	Region(Monde monde, int x, int y)
	{
		this.monde = monde;
		this.element = new ArrayList<Representation>();
		this.coordonnees = new Position(x,y);
		this.tauxPheromones = new Pheromone();
		ajoutProie = Aleatoire.nouveauRandom().nextInt(100);
		if(ajoutProie == 1)
			this.ajouterProie();
	}
	
	void ajouterProie()
	{
		this.element.add(new RepresentationProie(this, new Proie(3)));
	}
	
	void creerFourmilliere()
	{
		this.element.add(new RepresentationFourmilliere(this, new Fourmilliere(this, this.monde.temps.UT.cal)));
		
	}
	
	public Position donnePos()
	{
		return this.coordonnees;
	}

	public void lancementRegion() {
		for(int i = 0; i < this.element.size(); i++)
		{
			((Representation)this.element.get(i)).lancementFourmi();
		}	
	}
	
	public Monde instanceMonde()
	{
		return this.monde;
	}

	public int recupererTauxChasse(){
		return this.tauxPheromones.retournerPheroChasse();
	}
	
	public int recupererTauxDepot(){
		return this.tauxPheromones.retournerPheroDepot();
	}

	public int recupererTauxFemelleSexue(){
		return this.tauxPheromones.retournerPheroCopulation();
	}
	
	
	public int recupererTaux(RepresentationFourmi representationFourmi) {
		return this.tauxPheromones.recupererTaux(representationFourmi);
	}
	
	public void rafraichirTaux(RepresentationFourmi representationFourmi) {
		this.tauxPheromones.rafraichit(representationFourmi);
	}
	
	public void ajouterElement(Representation o)
	{
		this.element.add(o);
	}
	
	public void retirerElement(Representation o)
	{
		for(int i = 0; i < this.element.size(); i++)
		{
			if(element.get(i) == o)
			{
				this.element.remove(i);
			}
		}
	}
	
	public RepresentationProie presenceProie()
	{
		Object o;
		for(int i = 0; i < this.element.size(); i++)
		{
			o = this.element.get(i);
			if( o instanceof RepresentationProie)
			{
				return (RepresentationProie)o;
			}
		}
		return null;
	}
}