package sacADos;
import methodes.*;
import java.io.IOException;
import java.util.ArrayList;


import gestionAppli.GestionFichier;
import methodes.Algorithme;
import methodes.Methode;

public class SacADos {

	private double poidsMax;
	private double poidsCourant;
	private ArrayList<Item> objetsPossibles;
	private ArrayList<Item> objetsDansLeSac;
	
	/**
	 * Constrcuetur de SacADos
	 * @param objets la liste d'objets possibles
	 * @param poidsMax du sac
	 */
	public SacADos(ArrayList<Item> objets, double poidsMax) {
		this.objetsDansLeSac = new ArrayList<>();
		this.objetsPossibles = objets;
		this.poidsMax = poidsMax;
	}
	
	/**
	 * Constructeur de SacADos
	 * @param chemin vers le fichier 
	 * @param poidsMax du sac
	 * @throws IOException si on ne trouve pas le fichier
	 */
	public SacADos(String chemin, Double poidsMax) throws IOException {
		this.objetsDansLeSac = new ArrayList<>();
		this.poidsCourant = 0.0;
		this.poidsMax = poidsMax;
		this.objetsPossibles = GestionFichier.extractionObjets(chemin);
	}
	
	/**
	 * Résout le sac avec la méthode donnée en param, récupérée du main
	 * @param ALGO l'algorithme qu'on veut appliquer
	 */
	public void resoudre(Algorithme ALGO) {
		Methode methodeChoisie = null;
		switch(ALGO) {
		case GLOUTON :
			methodeChoisie = new AlgorithmeGlouton(this);
			break;
		case DYNAMIQUE :
			methodeChoisie = new AlgorithmeDynamique(this);
			break;
		case PSE :
			methodeChoisie = new PSE(this);
			break;
		default :
			break;
		}
		methodeChoisie.resoudre();
	}
	
	
	///////////// Getter //////////////////
	
	public ArrayList<Item> getObjetsPossibles() {
		return this.objetsPossibles;
	}
	
	public double getPoidsMax() {
		return this.poidsMax;
	}
	
	public double getPoidsCourant() {
		return this.poidsCourant;
	}
	
	///////////Fonctions utiles /////////////
	
    /**
     * Permet d'ajouter un objet dans le sac s'il y a assez de place
     * Augmente le poidsCourant
     * @param objet l'objet à ajouter dans le sac
     */
    public void ajouter(Item objet) {
       // if(objet.getPoids() + this.poidsCourant <= this.poidsMax) {
        	this.objetsDansLeSac.add(objet);
        	this.poidsCourant += objet.getPoids();
    }
    
    /**
     *  calcule la valeur du sac
     * @return la valeur du sac, tous les objets dans le sac
     */
    public double valeurSac() {
    	double valeurSac = 0.0;
    	for(int i = 0; i < this.objetsDansLeSac.size(); ++i)
    		valeurSac += this.objetsDansLeSac.get(i).getValeur();
    	return valeurSac;
    }
    /**
     * surcharge de la méthode toString() 
     * affiche les infos du sac
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Sac à dos\n");
        	sb.append("\n");
        sb.append("-Valeur totale: ");
        	sb.append(this.valeurSac());
        	sb.append("\n \n");
        sb.append("-Poids total : ");
        	sb.append(this.poidsCourant);
        	sb.append("\n \n");
        sb.append("-Poids max : ");
        	sb.append(this.poidsMax);
        	sb.append("\n \n");
        sb.append("- Objets dans le sac : \n \n");
         for(Item obj : this.objetsDansLeSac)
        	 sb.append(" - ").append(obj.toString()).append("\n");
        	
        return sb.toString();
    }
    
    
}