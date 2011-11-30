package Fourmi;




public class Adulte extends Etape_Fourmi{

	@Override
	public void fairePour(Fourmi f) {
		f.fairePourAdulte(this);
	}
	
	void mangerPour(Fourmi f)
	{
		f.mangerPourAdulte(this);
	}
	
	void travaillerPour(Fourmi f)
	{
		f.travaillerPourAdulte(this);
	}

	@Override
	public void evolutionPour(Fourmi f) {
		// do Nothing
		
	}
}
