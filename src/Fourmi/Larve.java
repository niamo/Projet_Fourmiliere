package Fourmi;


public class Larve extends Etape_Fourmi{

	@Override
	public void fairePour(Fourmi f) {
		f.fairePourLarve(this);
	}

	@Override
	public void evolutionPour(Fourmi f) {
		f.etape = new Nymphe();
	}

}
