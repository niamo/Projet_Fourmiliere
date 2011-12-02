package Fourmi;


import Utils.Aleatoire;
import Utils.Constantes;
import Utils.Constantes.*;

import Environnement.Fourmilliere;
import Environnement.Temps;

public abstract class Fourmi {

	
	public Etat etat; 
	public int proieCapturee;
	public boolean aLaMaison = true;
	int naissance;
	int dureeVie;
	int dernierRepas;
	double poids;
    EtapeFourmi etape;
	Fourmilliere fourmilliere;
	int pheromonisation;

	
	public abstract void fairePourOeuf(Oeuf oeuf);
	public abstract void fairePourAdulte(Adulte adulte);
	public abstract void fairePourNymphe(Nymphe nymphe);
	public abstract void fairePourLarve(Larve larve);
	
	public void mangerPourAdulte(Adulte adulte){
		if(!aRecemmentManger())
		{
			if(!aLaMaison)
				this.etat = Etat.retourMaison;
			else
			{
				if(fourmilliere.reserve.retirerNourriture(this.poids/3))
					this.dernierRepas = mangeUnBout();
			}	
		}
	}

	public void mangerPourLarve(Larve larve){
		if(!aRecemmentManger())
		{
			if(fourmilliere.reserve.retirerNourriture(this.poids*(2+Aleatoire.nouveauRandom().nextInt(1))))
				this.dernierRepas = mangeUnBout();

		}
	};
	public void mangerPourOeuf(Oeuf oeuf){
		// ne rien faire
	};
	public void mangerPourNymphe(Nymphe nymphe){
		// ne rien faire
	};
	

	public void verificationVivanteAdulte(Adulte adulte)
	{
		if(this.naissance+this.dureeVie < Temps.nbHeuresDepuisDebut())
		{
			this.meurs();
		}else
		{
			if(this.dernierRepas+Constantes.meursDeFaim < Temps.nbHeuresDepuisDebut())
			{
				this.meurs();
			}
		}
	}

	public void verificationVivanteLarve(Larve larve)
	{
		if(this.dernierRepas+Constantes.meursDeFaim < Temps.nbHeuresDepuisDebut())
			this.meurs();
	}
	
	public void verificationEvolutionOeuf(Oeuf oeuf)
	{
		if(this.naissance+Constantes.dureeOeuf < Temps.nbHeuresDepuisDebut())
		{
			this.etape = new Larve();
			this.dernierRepas = mangeUnBout();
		}
	}
	public void verificationEvolutionLarve(Larve larve)
	{
		if(this.naissance+Constantes.dureeOeuf+Constantes.dureeLarve < Temps.nbHeuresDepuisDebut())
		{
			this.etape = new Nymphe();
		}
	}
	public void verificationEvolutionNymphe(Nymphe nymphe)
	{
		if(this.naissance+Constantes.dureeLarve+Constantes.dureeNymphe+Constantes.dureeOeuf < Temps.nbHeuresDepuisDebut())
		{
			this.etape = new Adulte();
			this.dernierRepas = mangeUnBout();
		}
	}
	
	public abstract void travaillerPourAdulte(Adulte adulte);
	
	public void nettoyerFourmilliere() {
		if(this.aLaMaison)
		{
			if(this.fourmilliere.nbCadavres() > 0)
			{
				this.fourmilliere.retirerCadavre();
			}
		}
	}

	
	public void tempsAction(int temps)
	{
		try {
			Thread.sleep(temps);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void meurs() {
		this.fourmilliere=null;
	}
	
	public boolean estVivant()
	{
		return this.fourmilliere==null?false:true;
	}
	
	
	public boolean aRecemmentManger() {
		return (this.dernierRepas+Constantes.aFaim > Temps.nbHeuresDepuisDebut())?true:false;
	}
	
	protected int mangeUnBout()
	{
		return Temps.nbHeuresDepuisDebut();
	}
	
	
	public double poids()
	{
		return this.poids;
	}

	public EtapeFourmi etape()
	{
		return this.etape;
	}
	
	public Fourmilliere fourmilliere()
	{
		return this.fourmilliere;
	}

}
