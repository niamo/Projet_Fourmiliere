package Fourmi;

import Utils.Constantes;

import Environnement.Fourmilliere;



public class Ouvriere extends Fourmi{
	
	
	public Ouvriere(Fourmilliere f, int naissance, double d, int dureeVie)
	{
		this.etat = Constantes.Etat.neFaisRien;
		this.dureeVie = dureeVie;
		this.proieCapturee = 0;
		this.fourmilliere = f;
		this.naissance = naissance;
		this.poids = d;
		this.etape = new Oeuf();
	}
	


	@Override
	public void fairePourAdulte(Adulte adulte) {
		verificationVivanteAdulte(adulte);
		if(this.estVivant())
		{	
			this.mangerPourAdulte(adulte);
			this.nettoyerFourmilliere();
			this.travaillerPourAdulte(adulte);
		}
	}


	@Override
	public void fairePourOeuf(Oeuf oeuf) {
		this.verificationEvolutionOeuf(oeuf);
		this.mangerPourOeuf(oeuf);
	}


	@Override
	public void fairePourLarve(Larve larve) {
		this.verificationVivanteLarve(larve);
		if(this.estVivant())
		{
			this.verificationEvolutionLarve(larve);
			this.mangerPourLarve(larve);
		}
	}

	@Override
	public void fairePourNymphe(Nymphe nymphe) {
		this.verificationEvolutionNymphe(nymphe);
		this.mangerPourNymphe(nymphe);
	}


	@Override
	public void travaillerPourAdulte(Adulte adulte) {
		this.chasser();
	}
	

	private void chasser() {
		this.etat = Constantes.Etat.chasse;
	}

}
