package Environnement;

import Fourmi.Proie;

public class Stock {

	Double quantite =0.0;
	
	Stock()
	{
		quantite = 1000.0;
	}
	
	public double renvoyerQuantite(){
		return quantite;
	}
	
	public void ajouterProie(Proie p){
		quantite+=p.getPoids();
	}
	
	public boolean retirerNourriture(Double d)
	{
		if((this.quantite - d) > 0 ){
			quantite-=d;
			return true;
		}
		return false;
	}
	
	public String toString(){
		return quantite.toString().substring(0, quantite.toString().indexOf("."));
	}
	
	
	
}
