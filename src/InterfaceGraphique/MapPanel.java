package InterfaceGraphique;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import Environnement.Monde;
import Environnement.Region;
import Utils.Constantes;
import Utils.RepresentationProie;

public class MapPanel extends JPanel{
	Monde m;
	
	public MapPanel(Monde monde){
		this.m = monde;
	}
	
	private static final long serialVersionUID = 1L;
	

	public void paintComponent(Graphics g){
		Region r;
         
         /* Dessin de notre monde */
         g.setColor(Color.YELLOW);
         g.fillRect(this.getWidth()/2-Constantes.mondeX/2,this.getHeight()/2-Constantes.mondeY/2,Constantes.mondeX,Constantes.mondeY);
         
         /* Dessin de notre fourmilliere */
         g.setColor(Color.BLACK);
         g.drawOval(this.getWidth()/2-Constantes.taillePixelFourmilliere/2,this.getHeight()/2-Constantes.taillePixelFourmilliere/2,Constantes.taillePixelFourmilliere,Constantes.taillePixelFourmilliere);
         
         
    	 /* Dessin de nos Proies */
         g.setColor(Color.RED);
		 for(int i=0; i<this.m.monde.length;i++){
			for(int j=0; j<this.m.monde.length;j++){
				for(int k=0; k<m.monde[i][j].element.size(); k++){
					if(m.monde[i][j].element.get(k) instanceof RepresentationProie){
						 g.fillOval(i+this.getWidth()/2-Constantes.mondeX/2,j+this.getHeight()/2-Constantes.mondeY/2,2,2);
					}
				}
			 }
		 }

		 /* Dessin des pheromones */		
		 for(int i=0; i < this.m.accederChangementsPheromones().size(); i++)
         {
        	 r = this.m.accederChangementsPheromones().get(i);
             g.setColor(retournerCouleurPheromone(r.tauxPheromones.retournerPheroChasse()));
        	 g.drawOval(r.donnePos().getX()+this.getWidth()/2-Constantes.mondeX/2,r.donnePos().getY()+this.getHeight()/2-Constantes.mondeY/2,2,2);
         }
		 
         /* Dessin de nos fourmis */
         for(int i=0; i < this.m.accederChangementsFourmis().size(); i++)
         {
        	 r = this.m.accederChangementsFourmis().remove(i);
             g.setColor(Color.BLACK);
        	 g.drawOval(r.donnePos().getX()+this.getWidth()/2-Constantes.mondeX/2,r.donnePos().getY()+this.getHeight()/2-Constantes.mondeY/2,3,3);
         }
         /* Dessin de notre fourmilliere */
         g.setColor(Color.BLACK);
         g.drawOval(this.getWidth()/2-Constantes.taillePixelFourmilliere/2,this.getHeight()/2-Constantes.taillePixelFourmilliere/2,Constantes.taillePixelFourmilliere,Constantes.taillePixelFourmilliere);
         
	}    
	
	private Color retournerCouleurPheromone(int pheromoneChasse) {
		int phero = 100;
		Color couleur = Color.CYAN.darker().darker().darker();
		while(phero > pheromoneChasse)
		{
			couleur = couleur.brighter();
			phero = phero-10;
		}

		return couleur;
	}

	public void dessinProie(Graphics g){
       
	}
	
	
}