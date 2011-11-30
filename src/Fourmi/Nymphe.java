package Fourmi;





public class Nymphe extends Etape_Fourmi{

	@Override
	public void fairePour(Fourmi f) {
		f.fairePourNymphe(this);
		
	}

	@Override
	public void evolutionPour(Fourmi f) {
		f.etape = new Adulte();
	}

}
