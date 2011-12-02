package Fourmi;

import Utils.Constantes;
import Environnement.Fourmilliere;


public class Soldat extends Fourmi{

	

	public Soldat(Fourmilliere f, int naissance, double poidsFourmi, int dureeVie)
	{
		this.etat = Constantes.Etat.neFaisRien;
		this.dureeVie = dureeVie;
		this.naissance = naissance;
		this.fourmilliere = f;
		this.etape = new Oeuf();
		this.poids = poidsFourmi;
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
		// Monter la garde
		
	}

}
