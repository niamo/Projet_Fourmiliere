package Environnement;

import java.util.ArrayList;

import Utils.Constantes;
import Utils.RepresentationFourmilliere;

/**
 * Classe monde, contenant un tableau de régions
 * une liste de changements phéromones et de changements fourmis 
 * permettant un réaffichage plus rapide
 * 
 *
 */

public class Monde{
	public Region monde[][] = new Region[Constantes.mondeX][Constantes.mondeY];
	ArrayList<Region> changementsPheromones;
	ArrayList<Region> changementsFourmis;
	Region RegionFourmilliere; 
	Temps temps;
	
	
	public Monde(Temps temps)
	{
		this.changementsFourmis = new ArrayList<Region>();
		this.changementsPheromones = new ArrayList<Region>();
		this.temps = temps;
		for(int x = 0; x < Constantes.mondeX; x++)
		{
			for(int y = 0; y < Constantes.mondeY; y++)
			{
				this.monde[x][y] = new Region(this,x,y);
			}
		}
		BIGBANG();
	}
	
	
	/**
	 * Création du monde !
	 * Ajout d'une fourmilliere au centre du monde
	 */
	void BIGBANG()
	{
		this.monde[Constantes.mondeX/2][Constantes.mondeY/2].creerFourmilliere();
		RegionFourmilliere = this.monde[Constantes.mondeX/2][Constantes.mondeY/2];
	}
	
	/**
	 * Instance unique de la fourmilliere
	 */
	public Fourmilliere recupererFourmilliere()
	{
		return ((RepresentationFourmilliere)this.RegionFourmilliere.element.get(0)).instanceFourmilliere();
	}
	
	/**
	 * Lancement du monde
	 */
	public void lancementMonde()
	{
		for(int x = 0; x < Constantes.mondeX; x++)
		{
			for(int y = 0; y < Constantes.mondeY; y++)
			{
				this.monde[x][y].lancementRegion();
			}
		}
	}
	
	public ArrayList<Region> accederChangementsFourmis()
	{
		return this.changementsFourmis;
	}
	
	public ArrayList<Region> accederChangementsPheromones()
	{
		return this.changementsPheromones;
	}
	
	
	
	public Region accederRegion(int x, int y)
	{
		if(x >= Constantes.mondeX)
			return monde[x-1][y];
		else if(x < 0 )
			return monde[x+1][y];
		else if(y >= Constantes.mondeY)
			return monde[x][y-1];
		else if(y < 0 )
			return monde[x][y+1];
		else return monde[x][y];
	}

}
