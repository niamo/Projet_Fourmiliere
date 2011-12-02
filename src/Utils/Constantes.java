package Utils;

public class Constantes {
	
	/* Constantes utilisees dans l'application */
	/* Cette classe permet une modification des paramètres plus aisee */
	public enum Etat{
		chasse, retourMaison, aFaim, nettoye, copule, neFaisRien
	}
	
	public static int dureeLarve = 24;//48;
	public static int dureeOeuf = 36;//72;
	public static int dureeNymphe = 48;//96;
	
	public static int eloignementMax = 200;
	public static int vitesseDeplacement = 2;
	public static int mondeX = 200;
	public static int mondeY = 200;
	public static int taillePixelFourmilliere = 10;

	public static Integer ageMinReine = 1460;
	public static Integer ageMaxReine = 3650;
	public static Integer ageMinFourmi = 547;
	public static Integer ageMaxFourmi = 912;
	
	public static double poidsMin = 1.5;
	public static double poidsMax = 2.0;
	public static double poidsLarve = 3.0;
	
	public static int tempsRepas = 200;
	public static int tempsPonte = 200;
	public static int tempsDeplacement = 10;
	public static int tempsJournee = 100;
	
	public static int aFaim = 50;
	public static int meursDeFaim = 70;
	public static int nbOeufsJour = 8;
	
	public static double pourcentageMinOuvriere = 0.60;
	public static double pourcentageMaxOuvriere = 0.70;
	public static double pourcentageMinSoldat= 0.20;
	public static double pourcentageMaxSoldat= 0.30;
	public static double pourcentageMinSexue= 0.05;
	public static double pourcentageMaxSexue= 0.20;



	
}
