package methodes;
import appli.Appli;
import sacADos.*;

public class AlgorithmeDynamique extends Methode {

	/**
	 * constructeur d'Algorithme Dynamique
	 * @param sac à résoudre
	 */
	public AlgorithmeDynamique(SacADos sac) {
		super(sac);
	}
	
	/**
	 * fonction qui permet de résoudre le sac avec l'algorithme dynamique
	 */
	public void resoudre() {
    	if(this.objetsPossibles.isEmpty()) {
    		System.out.println("Pas d'objets à mettre dans le sac");
    	}
    	
    	//on initialise la matrice 
    	int nb_objets = this.objetsPossibles.size(); //ligne
    	int poidsMax = (int)(sac.getPoidsMax()*Appli.PRECISION +1); //colonne (on caste en int pour n'avoir que des entiers dans la matrice)
    	
    	double [][] matrice = new double[nb_objets][poidsMax];
    	
    	//on remplit la première ligne
    	for(int j=0; j<= sac.getPoidsMax()*Appli.PRECISION; j++) {
    		if(this.objetsPossibles.get(0).getPoids()*Appli.PRECISION > j)
    			matrice[0][j] = 0;
    		else 
    			matrice[0][j] = (int)(this.objetsPossibles.get(0).getValeur());
    	}
    	
    	//On remplit les autres lignes
    	for(int i=1; i < nb_objets; ++i) {
    		for(int j=0; j<= sac.getPoidsMax()*Appli.PRECISION; ++j)
    			if(this.objetsPossibles.get(i).getPoids()*Appli.PRECISION > j)
    				matrice[i][j] = matrice[i-1][j];
    			else
    				matrice[i][j] = (int)Math.max(matrice[i-1][j], matrice[i-1][(int)(j- this.objetsPossibles.get(i).getPoids()*Appli.PRECISION)] + this.objetsPossibles.get(i).getValeur());
    	}
    	
    	//on recule horizontalement depuis la dernière valeur de la matrice en bas à droit
    	// tant que le bénéfice reste le même
    	
    	//on récupère dans la dernière ligne le poids minimal nécessaire pour faire le bénéfice optimal
    	
    	int i = nb_objets - 1;
    	int j = (int) (sac.getPoidsMax()*Appli.PRECISION);
    	while(matrice[i][j] == matrice[i][j-1]) {
    		--j;
    	}
    	
    	while(j > 0) {
    		while(i > 0 && matrice[i][(int)j] == matrice[i-1][(int)j])
    			--i;
    		j = j - (int) (this.objetsPossibles.get(i).getPoids()*Appli.PRECISION);
    		if( j >= 0)
    			sac.ajouter(this.objetsPossibles.get(i));
    		--i;
    	}
	}
}
