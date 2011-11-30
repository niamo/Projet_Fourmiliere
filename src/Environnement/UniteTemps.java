package Environnement;

import java.util.Calendar;


public class UniteTemps {
	
	public Calendar cal = Calendar.getInstance();
	
	
	public UniteTemps()
	{
		cal.clear();
		cal.set(Calendar.YEAR, 1);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.DATE, 15);
		cal.set(Calendar.HOUR, 0);
	}
	
	public String Affichage(){
		String s = new String();
		s+=cal.get(Calendar.DATE)+"/"+cal.get(Calendar.MONTH)+"/"+cal.get(Calendar.YEAR)+"  ";
		s+=cal.get(Calendar.HOUR)+":00";
		return s;	
	}
}
