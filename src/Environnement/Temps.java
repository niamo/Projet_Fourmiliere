package Environnement;

import java.util.Calendar;
import java.util.Date;

import Utils.Constantes;


public class Temps implements Runnable{

	UniteTemps UT;
	Chronos maitre;
	private static int time;

	

	public Temps(Chronos maitre)
	{
		this.maitre = maitre;
		UT = new UniteTemps();
		(new Thread(this)).start();
	}
	
	public void run()
	{
        while(true) {
                try {
                        Thread.sleep(Constantes.tempsJournee);
                        Thread.yield();
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }
    			incrementationTemps();               
        }
	}

	public void afficherTemps() {
		System.out.println("Calendar :"+UT.Affichage());	
	}


	private void incrementationTemps() {
		time++;
		int Day = UT.cal.get(Calendar.DATE);
		int Month = UT.cal.get(Calendar.MONTH);
		int Year = UT.cal.get(Calendar.YEAR);
		UT.cal.set(Calendar.HOUR, UT.cal.get(Calendar.HOUR)+1);
		
		this.maitre.nouvelleHeure();
		
		if(UT.cal.get(Calendar.DATE) != Day)
		{
			if(UT.cal.get(Calendar.MONTH) != Month)
			{
				if(UT.cal.get(Calendar.YEAR) != Year)
				{
					this.maitre.nouvelleAnnee();
				}else
				{
					this.maitre.nouveauMois();
				}
			}else
			{
				this.maitre.nouveauJour();
			}
			
		}
	}
	
	public static int differenceDates(Calendar c1, Date d)
	{
	    Calendar calendar = (Calendar) c1.clone();
		int retard = 0;
        Date date1 = c1.getTime();
        Date date2 = d;
        long diff = Math.abs(date2.getTime() - date1.getTime());
        long numberOfDay = (long)diff/86400000;
        if(numberOfDay != 0){
        	for(int i=1;i<=numberOfDay;i++){
        		if(calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY && calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY)
        			retard++;
        		calendar.add(Calendar.DAY_OF_MONTH, 1);
        	}
        	return retard;
         }
        return 0;
        }

	public static int nbHeuresDepuisDebut()
	{
		return time;
	}

}