package Utils;

import Environnement.Region;
import Fourmi.Proie;

public class RepresentationProie extends Representation{
	Proie proie;
	
	
	public RepresentationProie(Region region, Proie elem)
	{
		this.proie = elem;
	}
	
	
	public Proie instanceProie()
	{
		return this.proie;
	}
}
