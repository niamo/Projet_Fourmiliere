package Utils;

import java.util.Random;
import Fourmi.*;
import Environnement.Fourmilliere;
import Environnement.Temps;

public class Aleatoire {
	
	public static Random nouveauRandom()
	{
		return new Random();
	}
	
	public static Integer dureeVieReine(){
		Integer dureevie;
		dureevie = Constantes.ageMinReine + nouveauRandom().nextInt(Constantes.ageMaxReine-Constantes.ageMinReine);
		return dureevie;
	}
	
	public static Integer dureeVieFourmi(){
		Integer dureevie;
		dureevie = Constantes.ageMinFourmi + nouveauRandom().nextInt(Constantes.ageMaxFourmi-Constantes.ageMinFourmi);
		return dureevie;
	}
	
	public static double poidsFourmi(){
		double poids;
		poids = Constantes.poidsMin + (Math.random() *(Constantes.poidsMax-Constantes.poidsMin));
		return poids;
	}
	
	public static Fourmi naissanceFourmi(Fourmilliere f){
		
		Integer taille = f.fourmilliere().size();
		
		float pourcentageOuvriere = ((float)f.nbOuvrieres()/(float)taille);
		float pourcentageSoldat = ((float)f.nbSoldats()/(float)taille);
		float pourcentageSexue = ((float)f.nbSexues()/(float)taille);
		
		double differenceOuvriere = maximum(Constantes.pourcentageMaxOuvriere-pourcentageOuvriere, Constantes.pourcentageMinOuvriere-pourcentageOuvriere);
		double differenceSoldat = maximum(Constantes.pourcentageMaxSoldat-pourcentageSoldat, Constantes.pourcentageMinSoldat-pourcentageSoldat);
		double differenceSexue = maximum(Constantes.pourcentageMaxSexue-pourcentageSexue, Constantes.pourcentageMinSexue-pourcentageSexue);
		
		
		double max = maximum(differenceOuvriere, maximum(differenceSoldat, differenceSexue));
		
		if(max == differenceOuvriere){
			return new Ouvriere(f,Temps.nbHeuresDepuisDebut(), Aleatoire.poidsFourmi(),Aleatoire.dureeVieFourmi() );
		}else if(max == differenceSoldat){
			return new Soldat(f,Temps.nbHeuresDepuisDebut(), Aleatoire.poidsFourmi(), Aleatoire.dureeVieFourmi());
		}else{
			return new Sexue(f,Temps.nbHeuresDepuisDebut(), Aleatoire.poidsFourmi(), Aleatoire.dureeVieFourmi());
		}
		
		
	}

	private static double maximum(double i, double j) {
		if(i>=j) return i;
		return j;
	}
	
}