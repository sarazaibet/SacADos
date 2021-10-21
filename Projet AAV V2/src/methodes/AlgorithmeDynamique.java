package methodes;
import appli.Appli;
import sacADos.*;

public class AlgorithmeDynamique extends Methode {

	/**
	 * constructeur d'Algorithme Dynamique
	 * @param sac � r�soudre
	 */
	public AlgorithmeDynamique(SacADos sac) {
		super(sac);
	}
	
	/**
	 * fonction qui permet de r�soudre le sac avec l'algorithme dynamique
	 */
	public void resoudre() {
    	if(this.objetsPossibles.isEmpty()) {
    		System.out.println("Pas d'objets � mettre dans le sac");
    	}
    	
    	//on initialise la matrice 
    	int nb_objets = this.objetsPossibles.size(); //ligne
    	int poidsMax = (int)(sac.getPoidsMax()*Appli.PRECISION +1); //colonne (on caste en int pour n'avoir que des entiers dans la matrice)
    	
    	double [][] matrice = new double[nb_objets][poidsMax];
    	
    	//on remplit la premi�re ligne
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
    	
    	//on recule horizontalement depuis la derni�re valeur de la matrice en bas � droit
    	// tant que le b�n�fice reste le m�me
    	
    	//on r�cup�re dans la derni�re ligne le poids minimal n�cessaire pour faire le b�n�fice optimal
    	
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
