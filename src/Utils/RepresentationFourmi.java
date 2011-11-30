package Utils;

import java.util.ArrayList;

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

	public void run(RepresentationFourmi f) {
		verificationFourmiPerdu();
		f.fourmi.etape.fairePour(fourmi);
		if(f.fourmi.estVivant())
			this.deplacementFourmi();
	}
	
	public Fourmi instanceFourmi()
	{
		return fourmi;
	}

	private void verificationFourmiPerdu() {
		if((this.pos.getX() > Constantes.eloignementMax)||(this.pos.getY() > Constantes.eloignementMax))
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
			this.regionActuelle.instanceMonde().accederChangements().add(this.regionActuelle);
			this.regionActuelle.instanceMonde().accederChangements().add(regionFuture);
			deplacementRegion(regionFuture);
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void deplacementRegion(Region regionPheromonisee) 
	{
		if(this.fourmi.etat == Constantes.Etat.retourMaison && this.fourmi.proieCapturee > 0)
			this.regionActuelle.rafraichirTaux(this);
		this.regionActuelle.retirerElement(this);
		this.pos = regionPheromonisee.donnePos();
		regionPheromonisee.ajouterElement(this);
		this.regionActuelle=regionPheromonisee;
		if(this.regionActuelle.donnePos().equals(this.fourmi.fourmilliere.region.donnePos()))
			this.fourmi.aLaMaison = true;
		else
			this.fourmi.aLaMaison = false;
	}

	private Region regarderAutour()
	{
		Region[] proximite = recupererProximite();
		Region destination = null;
		// utilisé si calcul de proba a faire;
		ArrayList<Region> destinations = new ArrayList<Region>();
		// Si elle a faim et qu'elle n'est pas a la fourmilliere, elle doit rentrer
		if(!this.fourmi.aRecemmentManger() && !this.fourmi.aLaMaison)
			this.fourmi.etat = Constantes.Etat.retourMaison;
		// Si elle chasse et qu'elle trouve une proie, elle doit rentrer
		if(this.fourmi.etat == Constantes.Etat.chasse && this.regionActuelle.presenceProie() != null)
		{
			this.fourmi.proieCapturee = this.regionActuelle.presenceProie().getPoids();
			this.regionActuelle.retirerElement(this.regionActuelle.presenceProie());
			this.fourmi.etat = Constantes.Etat.retourMaison;
		}
		// Elle se deplace vers la fourmilliere
		if(this.fourmi.etat == Constantes.Etat.retourMaison)
		{
			return retournerMaison(proximite);
		}
		// sinon elle chasse
		for(int i=0; i< proximite.length;i++)
		{
			// On ne reviens pas en arrière, ni a la fourmilliere quand on chasse... logique quoi !
			if(this.ancienneRegion != proximite[i] && destination == null && this.fourmi.fourmilliere.region != proximite[i])
			{
				destination = proximite[i];
				break;
			}
		}

		for(int i=0; i< proximite.length;i++)
		{
			if(proximite[i].recupererTaux(this) == destination.recupererTaux(this))
			{
				destinations.add(proximite[i]);
			}
			else if(proximite[i].recupererTaux(this) > destination.recupererTaux(this))
				destination = proximite[i];
		}
		if(destinations.size() == 0)
			return destination;
		else
			return calculerProbaDestination(destinations);
	}
		
	private Region calculerProbaDestination(ArrayList<Region> destinations) {
		int choix = 0 + Aleatoire.random.nextInt(destinations.size());
		return destinations.get(choix);
	}

	private Region retournerMaison(Region[] proximite) 
	{
		Position posMaison = this.fourmi.fourmilliere.region.donnePos();
		int xMaison = posMaison.getX();
		int yMaison = posMaison.getY();
		if(xMaison < this.regionActuelle.donnePos().getX()) // Trop a droite
		{
			return proximite[1];
		}else if(xMaison > this.regionActuelle.donnePos().getX()) // Trop a gauche
		{
			return proximite[0];
		}else if(yMaison < this.regionActuelle.donnePos().getY()) // Trop haut
		{
			return proximite[3];
		}else if(yMaison < this.regionActuelle.donnePos().getY()) // Trop bas
		{
			return proximite[2];
		}
		else
			return this.regionActuelle;
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
