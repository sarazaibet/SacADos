package arbre;

import java.util.ArrayList;

import sacADos.Item;


public class ABR {

	private ABR filsGauche;
	private ABR filsDroit;
	
	//liste d'objets que l'on va mettre dans le sac
	private ArrayList<Item> objetsDansLeSac;
	
	private double poids; //poids courant d'un noeud
	private double valeur; //valeur courante d'un noeud
	
	//potentiel du fils gauche (valeur max)
	private double borneSup; 

	
	/**
	 * constructeur de la racine de l'arbre
	 * @param objetsPossibles du sac
	 * @param poidsMax du sac
	 */
	public ABR(ArrayList<Item> objetsPossibles, double poidsMax) {
		
		this.objetsDansLeSac = new ArrayList<>();
		this.poids = 0.0;
		this.valeur = 0.0;
		this.borneSup = 0.0;
		
		for(int i = 0; i < objetsPossibles.size(); ++i)
			this.borneSup += objetsPossibles.get(i).getValeur();
		
		this.creerFils(objetsPossibles, poidsMax,  0);
	}
	
	/**
	 * Constructeur récursif d'ABR
	 * @param objetsPossibles du sac
	 * @param poidsMax du sac
	 * @param parent, le noeud père
	 * @param profondeur du noeud courant
	 * @param ajouterItem, booléen : si true alors on se trouve dans le fils droit si false dans le fils gauche
	 */
	private ABR(ArrayList<Item> objetsPossibles, double poidsMax, ABR parent, int profondeur, boolean ajouterItem) {
		
		this.poids = parent.getPoids();
		this.valeur = parent.getValeur();
		this.borneSup = parent.getBorneSup();
	
		this.objetsDansLeSac = new ArrayList<>(parent.getObjetsDansLeSac());
		
		if(ajouterItem == true) {
				
				this.poids += objetsPossibles.get(profondeur).getPoids();
				this.valeur += objetsPossibles.get(profondeur).getValeur();

				this.objetsDansLeSac.add(objetsPossibles.get(profondeur));
			}
		else {
			//si on ne prend pas l'objet, on aura pas sa valeur dans la liste d'objetsDansLeSac donc on enlève sa valeur
			this.borneSup -= objetsPossibles.get(profondeur).getValeur();
			
		}
		this.creerFils(objetsPossibles, poidsMax, profondeur+1);
	} 
	
	
	/**
	 * crée les fils du noeud courant
	 * @param objetsPossibles du sac
	 * @param poidsMax du sac
	 * @param profondeur des fils
	 */
	private void creerFils(ArrayList<Item> objetsPossibles, double poidsMax, int profondeur) {
		if(profondeur < objetsPossibles.size()) {
			if((this.poids + objetsPossibles.get(profondeur).getPoids()) <= poidsMax)
				this.filsDroit = new ABR(objetsPossibles, poidsMax, this, profondeur, true);
		this.filsGauche = new ABR(objetsPossibles, poidsMax, this, profondeur, false);
		}
	}
	
	////////////// Getter //////////////
	
	public double getPoids() {
		return this.poids;
	}
	
	public double getValeur() {
		return this.valeur;
	}
	
	public ABR getFilsGauche() {
		return this.filsGauche;
	}
	
	public ABR getFilsDroit()  {
		return this.filsDroit;
	}
	
	public double getBorneSup() {
		return this.borneSup;
	}	
	
	public ArrayList<Item> getObjetsDansLeSac() {
		return this.objetsDansLeSac;
	}
	
}

