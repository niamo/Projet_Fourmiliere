package Environnement;

import java.util.ArrayList;

import Fourmi.Pheromone;
import Fourmi.Proie;
import Utils.Position;
import Utils.RepresentationFourmi;
import Utils.RepresentationFourmilliere;
import Utils.RepresentationProie;
import Utils.Representation;

public class Region {

	ArrayList<Object> element;
	Position coordonnees;
	public Pheromone tauxPheromones;
	Monde monde;


	Region(Monde monde, int x, int y)
	{
		this.monde = monde;
		this.element = new ArrayList<Object>();
		this.coordonnees = new Position(x,y);
		this.tauxPheromones = new Pheromone();

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

	public void run() {
		for(int i = 0; i < this.element.size(); i++)
		{
			((Representation)this.element.get(i)).run();
		}	
	}
	
	public Monde instanceMonde()
	{
		return this.monde;
	}

	public int recupererTaux(RepresentationFourmi representationFourmi) {
		return this.tauxPheromones.recupererTaux(representationFourmi);
	}
	
	/* Pas bon, a revoir */
	public void rafraichirTaux(RepresentationFourmi representationFourmi) {
		this.tauxPheromones.rafraichit(representationFourmi);
	}
	
	public void ajouterElement(Object o)
	{
		this.element.add(o);
	}
	
	public void retirerElement(Object o)
	{
		this.element.remove(o);
	}
	
	public Proie presenceProie()
	{
		Object o;
		for(int i = 0; i < this.element.size(); i++)
		{
			o = this.element.get(i);
			if( o instanceof RepresentationProie)
			{
				return ((RepresentationProie)o).instanceProie();
			}
		}
		return null;
	}
}
