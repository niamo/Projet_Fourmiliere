package Environnement;
import java.util.ArrayList;
import java.util.Calendar;


import Utils.RepresentationFourmi;
import Utils.Aleatoire;

import Fourmi.*;

public final class Fourmilliere extends Thread{
	public Stock reserve;
	public Calendar calendrier;
	public ArrayList<RepresentationFourmi> fourmilliere;
	public Integer nbOuvrieres;
	public Integer nbSoldats;
	public Integer nbSexues;
	public Integer nbLarves;
	public Integer nbOeufs;
	public Integer nbNymphes;
	public Integer nbAdultes;
	public Integer nbCadavres;
	public Region region;

	
	public Fourmilliere(Region region, Calendar calendar)
	{
		this.region = region;
		this.initialiserAttr();
		this.calendrier = calendar;
		this.fourmilliere = new ArrayList<RepresentationFourmi>();
		this.reserve = new Stock();
		this.fourmilliere.add(new RepresentationFourmi(region, new Reine(this, Temps.nbHeuresDepuisDebut(), 2)));
	}
	
	private void initialiserAttr() {
		this.nbOuvrieres = 0;
		this.nbSoldats = 0;
		this.nbSexues = 0;
		this.nbLarves = 0;
		this.nbOeufs = 0;
		this.nbAdultes = 0;
		this.nbNymphes = 0;
		this.nbCadavres = 0;
	}

	public void run()
	{
		RepresentationFourmi f;
		while(true)
		{
			for(int i = 0; i< this.fourmilliere.size();i++)
			{
				f = this.fourmilliere.get(i);
				if(f.instanceFourmi().estVivant())
				{
					f.run(f);
				}
			}	
		}
	}
	
	public void ajouterIndividu()
	{
		this.fourmilliere.add(new RepresentationFourmi(this.region, Aleatoire.naissanceFourmi(this)));
		this.nbOeufs++;
	}
	
	public void addOuvriere() {
		this.nbOuvrieres++;		
	}

	public void addSoldat() {
		this.nbSoldats++;
		
	}

	public void addSexue() {
		this.nbSexues++;
		
	}

	public String afficherEtat() {
		String etat;
		etat = "Taille de la fourmilliere : "+this.fourmilliere.size();
		etat += "\nNombre d'oeufs : "+this.nbOeufs;
		etat += "\nNombre de larves : "+this.nbLarves;
		etat += "\nNombre de nymphes : "+this.nbNymphes;
		etat += "\nNombre d'adultes : "+this.nbAdultes;
		etat += "\nNombre d'ouvriers : "+this.nbOuvrieres;
		etat += "\nNombre de soldats : "+this.nbSoldats;
		etat += "\nNombre de sexues : "+this.nbSexues;
		etat += "\nStock de nourriture : "+this.reserve.quantite;
		etat += "\n";
		return etat;
	}

	public void retirerCadavre() {
		RepresentationFourmi f;
		for(int i = 0; i < this.fourmilliere.size(); i++)
		{
			f = this.fourmilliere.get(i);
			if(!f.instanceFourmi().estVivant())
			{
				System.out.println("Une fourmi nous a quitté");
				fourmilliere.remove(i);
				return;
			}
		}
		
	}
	
}
