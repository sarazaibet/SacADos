package methodes;
import java.util.ArrayList;

import sacADos.*;

public abstract class Methode {

	protected SacADos sac;
	protected ArrayList<Item> objetsPossibles;
	
	/**
	 * Constructeur permettant d'instancier une classe Methode
	 * @param sac à résoudre
	 */
	public Methode(SacADos sac) {
		this.sac = sac;
		this.objetsPossibles = sac.getObjetsPossibles();
	}
	
	/**
	 * Toutes les classes qui implémentent Résolution doivent contenir une méthode resoudre
	 * AlgorithmeGlouton.resoudre() , AlgorithmeDynamique.resoudre() et pse.resoudre() 
	 */
	public abstract void resoudre();
	
}
