package Utils;

import Environnement.Region;
import Fourmi.Fourmi;

public class RepresentationFourmi extends Representation{
	Fourmi fourmi;
	Region regionActuelle;
	Position pos;
	Region ancienneRegion;
	
	
	public RepresentationFourmi(Region region, Fourmi elem)
	{
		this.fourmi = elem;
		this.regionActuelle = region;
		this.ancienneRegion = region;
		this.pos = region.donnePos();
	}

	public void lancementFourmi(RepresentationFourmi f) {
		verificationFourmiPerdu();
		f.fourmi.etape().fairePour(fourmi);
		if(f.fourmi.estVivant()){
			this.deplacementFourmi();
		}
		
	}
	
	public Fourmi instanceFourmi()
	{
		return fourmi;
	}

	private void verificationFourmiPerdu() {
		if((this.pos.getX() - this.fourmi.fourmilliere().region().donnePos().getX() > Constantes.eloignementMax)
				|| (this.pos.getY() - this.fourmi.fourmilliere().region().donnePos().getY() > Constantes.eloignementMax)
				|| (this.pos.getX() - this.fourmi.fourmilliere().region().donnePos().getX() < -Constantes.eloignementMax)
				|| (this.pos.getX() - this.fourmi.fourmilliere().region().donnePos().getY() < -Constantes.eloignementMax))
		{
			this.fourmi.meurs();
		}
	}
		
	public void deplacementFourmi()
	{
		if(this.fourmi.etat == Constantes.Etat.chasse || this.fourmi.etat == Constantes.Etat.copule 
				|| this.fourmi.etat == Constantes.Etat.retourMaison)
		{
			Region regionFuture = regarderAutour();
			this.regionActuelle.instanceMonde().accederChangementsPheromones().add(this.regionActuelle);
			this.regionActuelle.instanceMonde().accederChangementsFourmis().add(regionFuture);
			deplacementRegion(regionFuture);
			
			try {
				Thread.sleep(0,2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	private void deplacementRegion(Region regionPheromonisee) 
	{
		if(this.fourmi.etat != Constantes.Etat.retourMaison)
			this.regionActuelle.rafraichirTaux(this);
		
		this.regionActuelle.retirerElement(this);
		this.pos = regionPheromonisee.donnePos();
		regionPheromonisee.ajouterElement(this);
		this.regionActuelle=regionPheromonisee;
		if(this.regionActuelle.donnePos().equals(this.fourmi.fourmilliere().region().donnePos()))
			this.fourmi.aLaMaison = true;
		else
			this.fourmi.aLaMaison = false;
	}

	private Region regarderAutour()
	{
		Region[] proximite = recupererProximite();
		int  tauxMax = 0;
		int casesPheromonisees = 0;
		// utilisé si calcul de proba a faire;
		// Si elle a faim et qu'elle n'est pas a la fourmilliere, elle doit rentrer
		if(!this.fourmi.aRecemmentManger() && !this.fourmi.aLaMaison)
			this.fourmi.etat = Constantes.Etat.retourMaison;
		// Si elle chasse et qu'elle trouve une proie, elle doit rentrer
		if(this.fourmi.etat == Constantes.Etat.chasse && this.regionActuelle.presenceProie() != null)
		{
			this.fourmi.proieCapturee = this.regionActuelle.presenceProie().instanceProie().getPoids();
			this.regionActuelle.retirerElement(this.regionActuelle.presenceProie());
			this.fourmi.etat = Constantes.Etat.retourMaison;
		}
		
		for(int i=0; i< proximite.length;i++)
		{
			if((proximite[i].recupererTaux(this) == tauxMax) 
					&& (proximite[i] != this.ancienneRegion && this.fourmi.etat != Constantes.Etat.retourMaison) )
			{
					casesPheromonisees++;
			}
			else if(proximite[i].recupererTaux(this) > tauxMax)
			{
				if(proximite[i] != this.ancienneRegion && this.fourmi.etat != Constantes.Etat.retourMaison)
				{
					casesPheromonisees=1;
					tauxMax = proximite[i].recupererTaux(this);
				}
			}
		}
		if(this.fourmi.etat == Constantes.Etat.retourMaison)
		{
			return retournerMaison(casesPheromonisees, proximite, tauxMax);
		}else if(this.fourmi.etat == Constantes.Etat.chasse)
		{
			return retournerChasse(casesPheromonisees, proximite, tauxMax);
		}
		
		return calculerProbaDestination(casesPheromonisees, proximite, tauxMax);
	}
		
	private Region retournerChasse(int nbCases, Region[] proximite, int tauxMax) {
		Region r = calculerProbaDestination(nbCases, proximite, tauxMax);
		while(r == this.ancienneRegion)
		{
			r = calculerProbaDestination(nbCases, proximite, tauxMax);
		}
		
		return r;
	}

	private Region calculerProbaDestination(int nbCases, Region[] proximite, int tauxMax) {
		int tabProbaProxi[] = {25,25,25,25};
		if(nbCases != 4 || nbCases != 0)
		{
			for(int i = 0; i < proximite.length; i++)
			{
				if((proximite[i] == this.ancienneRegion && this.fourmi.etat != Constantes.Etat.retourMaison)
						|| (proximite[i].recupererTaux(this) < tauxMax))
				{
					tabProbaProxi[i] = (100/4)-(nbCases*5);
				}else
				{
					tabProbaProxi[i] = (100/4)+((4-nbCases)*5);
				}
			}
		}
		int choix = 0 + Aleatoire.nouveauRandom().nextInt(101);
		if(choix < tabProbaProxi[0])
			return proximite[0];
		else if((choix > tabProbaProxi[0]) && (choix < (tabProbaProxi[1]+tabProbaProxi[0])))
			return proximite[1];
		else if(choix > (tabProbaProxi[1]+tabProbaProxi[0]) && (choix < (tabProbaProxi[0]+tabProbaProxi[1]+tabProbaProxi[2])))
			return proximite[2];
		else
			return proximite[3];
	}

	private Region retournerMaison(int nbCases, Region[] proximite, int tauxMax) 
	{
		Region r = calculerProbaDestination(nbCases, proximite, tauxMax);
		while(nousEloigneMaison(r))
		{
			r = calculerProbaDestination(nbCases, proximite, tauxMax);
		}
		
		return r;
	}


	/**
	 * On verifie, si la fourmi doit rentrer chez elle que sa prochaine destination ne l'éloigne pas de la fourmilliere
	 * @param region de destination
	 * @return vrai si on s'éloigne, faux sinon
	 */
	private boolean nousEloigneMaison(Region r)
	{	
		Position posMaison = this.fourmi.fourmilliere().region().donnePos();
		int distanceDestinationMaison;
		int distanceFourmiMaison;
		distanceDestinationMaison = (int)Math.sqrt(Math.pow((double)r.donnePos().getX()-(double)posMaison.getX(), 2)+Math.pow((double)r.donnePos().getY()-(double)posMaison.getY(),2));
		distanceFourmiMaison = (int)Math.sqrt(Math.pow((double)this.pos.getX()-(double)posMaison.getX(), 2)+Math.pow((double)this.pos.getY()-(double)posMaison.getY(),2)); 
		return distanceDestinationMaison > distanceFourmiMaison?true:false;
	}

	private Region[] recupererProximite()
	{
		Region[] proximite = new Region[4];
		// Region droite
		proximite[0] = this.regionActuelle.instanceMonde().accederRegion(this.pos.getX()+1, this.pos.getY());
		//Region gauche
		proximite[1] = this.regionActuelle.instanceMonde().accederRegion(this.pos.getX()-1, this.pos.getY());
		// Region haut
		proximite[2] = this.regionActuelle.instanceMonde().accederRegion(this.pos.getX(), this.pos.getY()+1);
		// Region bas
		proximite[3] = this.regionActuelle.instanceMonde().accederRegion(this.pos.getX(), this.pos.getY()-1);
		return proximite;
	}
}