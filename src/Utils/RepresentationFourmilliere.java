package Utils;

import Environnement.Fourmilliere;
import Environnement.Region;

public class RepresentationFourmilliere extends Representation{
	Fourmilliere fourmilliere;
	public RepresentationFourmilliere(Region region, Fourmilliere elem)
	{
		this.fourmilliere = elem;
	}
	
	
	public Fourmilliere instanceFourmilliere()
	{
		return this.fourmilliere;
	}
	
	public void run()
	{
		this.fourmilliere.run();
	}
}
