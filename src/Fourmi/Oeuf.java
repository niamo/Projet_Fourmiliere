package Fourmi;




public class Oeuf extends Etape_Fourmi{

	@Override
	public void fairePour(Fourmi f) {
		f.fairePourOeuf(this);
	}

	@Override
	public void evolutionPour(Fourmi f) {
		f.etape = new Larve();
		
	}
	
	
}
