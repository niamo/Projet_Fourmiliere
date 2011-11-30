package InterfaceGraphique;

import java.awt.*;
import javax.swing.*;
import Environnement.Dieu;

public class Fenetre extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel container = new JPanel();
	JPanel panelInfos = new JPanel();
	JPanel panelHeureStock = new JPanel();
	JPanel panelMonde = new JPanel();
	
	/* Layouts */
	GridLayout layoutInfos = new GridLayout(4,2);
	GridLayout layoutHeureStock = new GridLayout(1,2);
	GridLayout layoutMonde = new GridLayout(1,1);
	
	/* Labels */
	JLabel nbOeufs = new JLabel();
	JLabel nbLarves = new JLabel();
	JLabel nbNymphes = new JLabel();
	JLabel nbAdultes = new JLabel();
	JLabel nbOuvrieres = new JLabel();
	JLabel nbSoldats = new JLabel();
	JLabel nbSexues = new JLabel();
	JLabel date = new JLabel();
	JLabel stock = new JLabel();
	
	/* Slider Temps A voir si on a le temps de faire un echelle entre le temps system et le temps de la fourmi*/
	
	/* JSlider temps = new JSlider(JSlider.HORIZONTAL,Constantes.vitesseMin, Constantes.vitesseMax, Constantes.vitesseInit); */
	
	public Fenetre(Dieu d){
		super();
		construireFenetre(d);
	}
	
	public void construireFenetre(Dieu d) {  

		setTitle("Fourmiz"); //On donne un titre à l'application
		setSize(800,600); //On donne une taille à notre fenêtre
		setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix

		/* Initialisation des labels */

		
		this.nbOeufs.setHorizontalAlignment(SwingConstants.CENTER);
		this.nbLarves.setHorizontalAlignment(SwingConstants.CENTER);
		this.nbNymphes.setHorizontalAlignment(SwingConstants.CENTER);
		this.nbAdultes.setHorizontalAlignment(SwingConstants.CENTER);
		this.nbOuvrieres.setHorizontalAlignment(SwingConstants.CENTER);
		this.nbSoldats.setHorizontalAlignment(SwingConstants.CENTER);
		this.nbSexues.setHorizontalAlignment(SwingConstants.CENTER);

		this.date.setHorizontalAlignment(SwingConstants.RIGHT);
		this.stock.setHorizontalAlignment(SwingConstants.RIGHT);
		
		this.repaint(d);
		
		/* Ajout des labels au gridlayouts */
		this.panelInfos.add(nbOeufs);
		this.panelInfos.add(nbOuvrieres);
		this.panelInfos.add(nbLarves);
		this.panelInfos.add(nbSoldats);
		this.panelInfos.add(nbNymphes);
		this.panelInfos.add(nbSexues);
		this.panelInfos.add(nbAdultes);
		
		this.panelHeureStock.add(date);
		this.panelHeureStock.add(stock);
		
		/* Ajout des gridLayout au GridLayout global */
		
		/* Couleur d'arriere des panels */
		this.panelInfos.setBackground(Color.GRAY);
		this.panelHeureStock.setBackground(Color.GRAY);
		this.panelMonde.setBackground(Color.YELLOW);
		/* Ajouts des gridlayouts aux pannels */
		this.panelInfos.setLayout(layoutInfos);
		this.panelHeureStock.setLayout(layoutInfos);
		this.panelMonde.setSize(200, 200);
		System.out.println(this.panelMonde.getSize());
		
		
		/* definition de notre pannel */
		this.container.setBackground(Color.WHITE);
		this.container.setLayout(new BorderLayout());
		this.container.add(panelInfos, BorderLayout.NORTH);
		this.container.add(panelHeureStock, BorderLayout.SOUTH);
		this.container.add(panelMonde, BorderLayout.CENTER);
		
		this.setContentPane(container);
		this.setVisible(true);
	}
	
	public void repaint(Dieu d){
		this.nbOeufs.setText("Nombre d'oeufs : "+d.instanceMonde().recupererFourmilliere().nbOeufs.toString());
		this.nbLarves.setText("Nombre de larves : "+d.instanceMonde().recupererFourmilliere().nbLarves.toString());
		this.nbNymphes.setText("Nombre de nymphes : "+d.instanceMonde().recupererFourmilliere().nbNymphes.toString());
		this.nbAdultes.setText("Nombre d'adultes : "+d.instanceMonde().recupererFourmilliere().nbAdultes.toString());
		this.nbOuvrieres.setText("Nombre d'ouvrieres : "+d.instanceMonde().recupererFourmilliere().nbOuvrieres.toString());
		this.nbSoldats.setText("Nombre de soldats : "+d.instanceMonde().recupererFourmilliere().nbSoldats.toString());
		this.nbSexues.setText("Nombre de sexues : "+d.instanceMonde().recupererFourmilliere().nbSexues.toString());

		this.date.setText(""+d.instanceMonde().recupererFourmilliere().calendrier.getTime());
		this.stock.setText("Stock : "+d.instanceMonde().recupererFourmilliere().reserve.toString());
		
	}
	
	
}
