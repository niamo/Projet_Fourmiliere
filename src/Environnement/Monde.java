package Environnement;

import java.util.ArrayList;

import Utils.Constantes;
import Utils.RepresentationFourmilliere;


public class Monde{
	Region monde[][] = new Region[Constantes.mondeX][Constantes.mondeY];
	ArrayList<Region> changements;
	Region RegionFourmilliere; 
	Temps temps;
	
	
	public Monde(Temps temps)
	{
		this.changements = new ArrayList<Region>();
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
	
	void BIGBANG()
	{
		this.monde[Constantes.mondeX/2][Constantes.mondeY/2].creerFourmilliere();
		RegionFourmilliere = this.monde[Constantes.mondeX/2][Constantes.mondeY/2];
		this.monde[95][97].ajouterProie();
	}
	
	public Fourmilliere recupererFourmilliere()
	{
		return ((RepresentationFourmilliere)this.RegionFourmilliere.element.get(0)).instanceFourmilliere();
	}
	
	public void start()
	{
		for(int x = 0; x < Constantes.mondeX; x++)
		{
			for(int y = 0; y < Constantes.mondeY; y++)
			{
				this.monde[x][y].run();
			}
		}
	}
	
	
	public ArrayList<Region> accederChangements()
	{
		return this.changements;
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
