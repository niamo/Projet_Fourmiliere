package Fourmi;

import Utils.Constantes;

import Environnement.Fourmilliere;
import Environnement.Temps;


public final class Reine extends Fourmi{

	int nbOeufsPonduAuj;
	int prochainePonte;
	
	public Reine(Fourmilliere fourmilliere, int naissance, int poids)
	{
		this.etat = Constantes.Etat.neFaisRien;
		this.fourmilliere = fourmilliere;
		this.etape = new Adulte();
		this.naissance = naissance;
		this.poids = poids;
		this.nbOeufsPonduAuj = 0;
		this.prochainePonte = Temps.nbHeuresDepuisDebut();
		this.dernierRepas = Temps.nbHeuresDepuisDebut();
	}

	@Override
	public void fairePourAdulte(Adulte adulte) {
		this.mangerPourAdulte(adulte);
		this.travaillerPourAdulte(adulte);
	}


	@Override
	public void fairePourOeuf(Oeuf oeuf) {
		// Attendre
		
	}


	@Override
	public void fairePourLarve(Larve larve) {
		// Manger && attendre
		
	}


	@Override
	public void fairePourNymphe(Nymphe nymphe) {
		// Attendre
		
	}


	@Override
	public void travaillerPourAdulte(Adulte adulte) {
		this.pondre();	
	}
	
	private void pondre()
	{
		while(Temps.nbHeuresDepuisDebut() > this.prochainePonte && this.nbOeufsPonduAuj < Constantes.nbOeufsJour)
		{
				this.fourmilliere.ajouterIndividu();
				this.nbOeufsPonduAuj++;
				if(this.nbOeufsPonduAuj >= Constantes.nbOeufsJour)
				{
					this.nbOeufsPonduAuj = 0;
					prochainePonte = Temps.nbHeuresDepuisDebut()+24;
				}
		}
		

	}


}
