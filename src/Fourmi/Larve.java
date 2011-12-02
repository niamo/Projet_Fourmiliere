package Fourmi;


public class Larve extends EtapeFourmi{

	@Override
	public void fairePour(Fourmi f) {
		f.fairePourLarve(this);
	}

	@Override
	public void evolutionPour(Fourmi f) {
		f.etape = new Nymphe();
	}

}
