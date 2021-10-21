package methodes;
import java.util.ArrayList;

import arbre.ABR;
import sacADos.*;

// J'ai réussi à faire fonctionner l'algorithme PSE, il me donne une valeur de 1430
// pour la liste de itemsEval.txt, soit le même résultat que l'algorithme dynamique


public class PSE extends Methode{

	/**valeur maximale qu'on a trouvé jusque là, 
	 * donc borneInf concerne la recherche 
	 * d'où sa présence dans la classe PSE
	 */
	private double borneInf; 
	
	
	/**
	 * Constructeur de PSE
	 * @param sac à résoudre
	 */
	public PSE(SacADos sac) {
		super(sac);
	}
	
	/**
	 * fonction récursive permettant de chercher la solution
	 * @param noeudCourant
	 * @return retourne le noeud contenant la meilleure solution
	 */
	private ABR chercherSolution(ABR noeudCourant) {
		
		//mise à jour de borneInf si une meilleure valeur est trouvée
		if(noeudCourant.getValeur() > this.borneInf)
			this.borneInf = noeudCourant.getValeur();
		
		//si le fils gauche est null on retourne le fils droit
		if(noeudCourant.getFilsGauche() == null)
			return noeudCourant;
		
		//si le fils droit est null on cherche dans le fils gauche
		if(noeudCourant.getFilsDroit() == null)
			return this.chercherSolution(noeudCourant.getFilsGauche());

		ABR filsD = chercherSolution(noeudCourant.getFilsDroit());
		
		if(noeudCourant.getFilsGauche().getBorneSup() < this.borneInf)
			return filsD;
		
		if(filsD.getValeur()>noeudCourant.getFilsGauche().getBorneSup())
			return filsD;
		
		ABR filsG = chercherSolution(noeudCourant.getFilsGauche());
		if(filsD.getValeur() > filsG.getValeur())
			return filsD;
		else
			return filsG;
	}
	
	/**
	 * fonction de résolution avec la méthode PSE
	 */
	@Override
	public void resoudre() {

		ABR racine = new ABR(sac.getObjetsPossibles(), sac.getPoidsMax()); //création de la racine de l'arbre
    	
    	ABR solution = chercherSolution(racine); //on cherche la solution à partir de la racine
    	
    	ArrayList<Item> resultat = solution.getObjetsDansLeSac(); //on récupère les objets du noeud contenant la meilleure solution dans une arrayList

    	//ajout des objetsDansLeSac de solution dans le sac
    	for(Item o : resultat) 
    		sac.ajouter(o);

	}
	
}
