package methodes;
import java.util.ArrayList;

import sacADos.*;

public abstract class Methode {

	protected SacADos sac;
	protected ArrayList<Item> objetsPossibles;
	
	/**
	 * Constructeur permettant d'instancier une classe Methode
	 * @param sac � r�soudre
	 */
	public Methode(SacADos sac) {
		this.sac = sac;
		this.objetsPossibles = sac.getObjetsPossibles();
	}
	
	/**
	 * Toutes les classes qui impl�mentent R�solution doivent contenir une m�thode resoudre
	 * AlgorithmeGlouton.resoudre() , AlgorithmeDynamique.resoudre() et pse.resoudre() 
	 */
	public abstract void resoudre();
	
}
