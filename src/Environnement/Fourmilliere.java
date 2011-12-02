package Environnement;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;


import Utils.RepresentationFourmi;
import Utils.Aleatoire;

import Fourmi.*;

/**
 * Classe représentant notre fourmilliere
 * Elle est composé d'une reserve de nourriture, du calendrier propre
 * au temps du monde, d'une liste de fourmis et d'une région
 * @author root
 *
 */
public final class Fourmilliere{
	public Stock reserve;
	Calendar calendrier;
	ArrayList<RepresentationFourmi> fourmilliere;
	Region region;

	
	public Fourmilliere(Region region, Calendar calendar)
	{
		this.region = region;
		this.calendrier = calendar;
		this.fourmilliere = new ArrayList<RepresentationFourmi>();
		this.reserve = new Stock();
		this.fourmilliere.add(new RepresentationFourmi(region, new Reine(this, Temps.nbHeuresDepuisDebut(), 2)));
	}

	/**
	 * La fourmilliere actionne les fourmis indéfiniment
	 */
	public void lancementFourmis()
	{
		RepresentationFourmi f;
		while(true)
		{
			for(int i = 0; i< this.fourmilliere.size();i++)
			{
				f = this.fourmilliere.get(i);
				if(f.instanceFourmi().estVivant())
				{
					f.lancementFourmi(f);
				}
			}	
		}
	}
	
	/**
	 * Création d'une nouvelle fourmi
	 */
	public void ajouterIndividu()
	{
		this.fourmilliere.add(new RepresentationFourmi(this.region, Aleatoire.naissanceFourmi(this)));
	}


	public String afficherEtat() {
		String etat;
		etat = "Taille de la fourmilliere : "+this.fourmilliere.size();
		etat += "\nNombre d'oeufs : "+this.nbOeufs();
		etat += "\nNombre de larves : "+this.nbLarves();
		etat += "\nNombre de nymphes : "+this.nbNymphes();
		etat += "\nNombre d'adultes : "+this.nbAdultes();
		etat += "\nNombre d'ouvriers : "+this.nbOuvrieres();
		etat += "\nNombre de soldats : "+this.nbSoldats();
		etat += "\nNombre de sexues : "+this.nbSexues();
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
				fourmilliere.remove(i);
				return;
			}
		}
		
	}
	
	public Integer nbOuvrieres()
	{
		ArrayList<RepresentationFourmi> copie = (ArrayList<RepresentationFourmi>)fourmilliere.clone();
		Integer nombre = 0;
		Iterator<RepresentationFourmi> itor = copie.iterator();
		while(itor.hasNext())
		{
			if(itor.next().instanceFourmi() instanceof Ouvriere)
			{
				nombre++;
			}
		}
		return nombre;
	}
	
	
	/**
	 * Accesseurs
	 */
	public Integer nbSoldats()
	{
		ArrayList<RepresentationFourmi> copie = (ArrayList<RepresentationFourmi>)fourmilliere.clone();
		Integer nombre = 0;
		Iterator<RepresentationFourmi> itor = copie.iterator();
		while(itor.hasNext())
		{
			if(itor.next().instanceFourmi() instanceof Soldat)
			{
				nombre++;
			}
		}
		return nombre;
	}
	
	public Integer nbSexues()
	{
		ArrayList<RepresentationFourmi> copie = (ArrayList<RepresentationFourmi>)fourmilliere.clone();
		Integer nombre = 0;
		Iterator<RepresentationFourmi> itor = copie.iterator();
		while(itor.hasNext())
		{
			if(itor.next().instanceFourmi() instanceof Sexue)
			{
				nombre++;
			}
		}
		return nombre;
	}
	
	public Integer nbLarves()
	{
		ArrayList<RepresentationFourmi> copie = (ArrayList<RepresentationFourmi>)fourmilliere.clone();
		Integer nombre = 0;
		Iterator<RepresentationFourmi> itor = copie.iterator();
		while(itor.hasNext())
		{
			if(itor.next().instanceFourmi().etape() instanceof Larve)
			{
				nombre++;
			}
		}
		return nombre;
	}
	
	public Integer nbOeufs()
	{
		ArrayList<RepresentationFourmi> copie = (ArrayList<RepresentationFourmi>)fourmilliere.clone();
		Integer nombre = 0;
		Iterator<RepresentationFourmi> itor = copie.iterator();
		while(itor.hasNext())
		{
			if(itor.next().instanceFourmi().etape() instanceof Oeuf)
			{
				nombre++;
			}
		}
		return nombre;
	}
	
	public Integer nbNymphes()
	{
		ArrayList<RepresentationFourmi> copie = (ArrayList<RepresentationFourmi>)fourmilliere.clone();
		Integer nombre = 0;
		Iterator<RepresentationFourmi> itor = copie.iterator();
		while(itor.hasNext())
		{
			if(itor.next().instanceFourmi().etape() instanceof Nymphe)
			{
				nombre++;
			}
		}
		return nombre;
	}
	
	public Integer nbAdultes()
	{
		ArrayList<RepresentationFourmi> copie = (ArrayList<RepresentationFourmi>)fourmilliere.clone();
		Integer nombre = 0;
		Iterator<RepresentationFourmi> itor = copie.iterator();
		while(itor.hasNext())
		{
			if(itor.next().instanceFourmi().etape() instanceof Adulte)
			{
				nombre++;
			}
		}
		return nombre;
	}
	
	
	public Integer nbCadavres()
	{
		ArrayList<RepresentationFourmi> copie = (ArrayList<RepresentationFourmi>)fourmilliere.clone();
		Integer nombre = 0;
		Iterator<RepresentationFourmi> itor = copie.iterator();
		while(itor.hasNext())
		{
			if(itor.next().instanceFourmi().fourmilliere() == null)
			{
				nombre++;
			}
		}
		return nombre;
	}
	
	
	public Region region()
	{
		return this.region;
	}
	
	public Calendar calendrier()
	{
		return this.calendrier;
	}
	
	
	public ArrayList<RepresentationFourmi> fourmilliere()
	{
		return this.fourmilliere;
	}
	
	
	
	
	
}
