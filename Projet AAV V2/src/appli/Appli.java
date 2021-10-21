package appli;
import java.io.IOException;

import sacADos.*;
import methodes.*;
import gestionAppli.GestionAppli;

public class Appli {

	public static final int PRECISION = 10; //pour les variables à virgule, algorithme dynamique
	public static Double poidsMAX; 
	public static String CHEMIN;
	public static Algorithme ALGO;
	
	public static void main(String[] args) throws IOException {
		
		GestionAppli.lancerAppli(args);
		SacADos sacFichier = new SacADos(CHEMIN, poidsMAX);
		
		System.out.print(sacFichier.toString());
		
		sacFichier.resoudre(ALGO);
		
		System.out.println(sacFichier.toString());

	}
}
