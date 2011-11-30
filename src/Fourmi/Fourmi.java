package Fourmi;


import Utils.Constantes;
import Utils.Constantes.*;

import Environnement.Fourmilliere;
import Environnement.Temps;

public abstract class Fourmi {

	
	public Etat etat; 
	public int naissance;
	public int proieCapturee;
	public int dureeVie;
	public int dernierRepas;
	public double poids;
	public Etape_Fourmi etape;
	public Fourmilliere fourmilliere;
	public int pheromonisation;
	public boolean aLaMaison = true;
	
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
			if(fourmilliere.reserve.retirerNourriture(this.poids))
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
	//	System.out.println((this.naissance+this.dureeVie)+"--"+Temps.nbHeuresDepuisDebut());
		if(this.naissance+this.dureeVie < Temps.nbHeuresDepuisDebut())
		{
			this.meurs();
		}else
		{
			//System.out.println((this.dernier_repas+Constantes.meursDeFaim)+"--"+Temps.nbHeuresDepuisDebut());
			if(this.dernierRepas+Constantes.meursDeFaim < Temps.nbHeuresDepuisDebut())
				this.meurs();
		}
	}

	public void verificationVivanteLarve(Larve larve)
	{
	//	System.out.println((this.dernier_repas+Constantes.meursDeFaim)+"--"+Temps.nbHeuresDepuisDebut());
		if(this.dernierRepas+Constantes.meursDeFaim < Temps.nbHeuresDepuisDebut())
			this.meurs();
	}
	
	public void verificationEvolutionOeuf(Oeuf oeuf)
	{
		if(this.naissance+Constantes.dureeOeuf < Temps.nbHeuresDepuisDebut())
		{
			this.etape = new Larve();
			this.fourmilliere.nbOeufs--;
			this.fourmilliere.nbLarves++;
			this.dernierRepas = mangeUnBout();
		}
	}
	public void verificationEvolutionLarve(Larve larve)
	{
		if(this.naissance+Constantes.dureeOeuf+Constantes.dureeLarve < Temps.nbHeuresDepuisDebut())
		{
			this.etape = new Nymphe();
			this.fourmilliere.nbLarves--;
			this.fourmilliere.nbNymphes++;
		}
	}
	public void verificationEvolutionNymphe(Nymphe nymphe)
	{
		if(this.naissance+Constantes.dureeLarve+Constantes.dureeNymphe+Constantes.dureeOeuf < Temps.nbHeuresDepuisDebut())
		{
			this.etape = new Adulte();
			this.fourmilliere.nbNymphes--;
			this.fourmilliere.nbAdultes++;
			this.dernierRepas = mangeUnBout();
		}
	}
	
	public abstract void travaillerPourAdulte(Adulte adulte);
	
	public void nettoyerFourmilliere() {
		if(this.aLaMaison)
		{
			if(this.fourmilliere.nbCadavres > 0)
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
		System.out.println("JDIE");
		this.fourmilliere.nbCadavres++;
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


}
