package gestionAppli;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import sacADos.Item;

public class GestionFichier {
	
	public GestionFichier() {}

	/**
	 * extrait chaque objet et ses caractéristiques, ligne par ligne, du fichier en entrée
	 * @param chemin du fichier où on va récupérer les informations	
	 * @return la liste d'objets possibles qui sera affectée à la classe SacADos
	 * @throws Exception le fichier est introuvable
	 */
	public static ArrayList<Item> extractionObjets(String chemin) throws IOException {
		try {
			@SuppressWarnings("resource")
			BufferedReader lecteur = new BufferedReader(new FileReader(chemin));
			String line;
			@SuppressWarnings("unused")
			int nbLines = 0;
			ArrayList<Item> objetsPossibles = new ArrayList<>();
			while((line = lecteur.readLine()) != null) {
				nbLines++;
				String[] infosObjet = line.split(" ; ");
				if (infosObjet.length == 3)
				{
					for(int i = 0; i < 3; ++i) 
						infosObjet[i] = infosObjet[i].trim().replaceAll("\n", " ");
					objetsPossibles.add(new Item(infosObjet[0], 
							Double.parseDouble(infosObjet[1]), 
							Double.parseDouble(infosObjet[2])));
				}
				else continue;
			}
			return objetsPossibles;
		} catch(Exception e) {
			throw e;
		}
	}
	
}
