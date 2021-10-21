package gestionAppli;
import appli.Appli;
import methodes.Algorithme;

public class GestionAppli extends Appli {

	/**
	 * Constructeur vide de GestionAppli
	 */
	public GestionAppli() {}
	
	
	/**
	 * Permet d'extraire les arguments en ligne de commande
	 * @param args du main
	 */
	public static void lancerAppli(String [] args)  {
		for(int i = 0; i <args.length; i++) {
			switch(i) {
			case 0 :
				CHEMIN = args[i];
				break;
			case 1 : 
				poidsMAX = Double.parseDouble(args[i]);
				break;
			case 2 :
				switch(args[i].toLowerCase()) {
				case "glouton":
				case "gloutonne":
				case "g":
					ALGO = Algorithme.GLOUTON;
					break;
				case "dynamique":
				case "d":
					ALGO = Algorithme.DYNAMIQUE;
					break;
				case "pse":
					ALGO = Algorithme.PSE;
				}
				break;
			default :
				break;
			}
			
		}
	}
}
