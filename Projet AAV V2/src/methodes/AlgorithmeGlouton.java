package methodes;

import java.util.ArrayList;
import java.util.Collections;

import sacADos.*;


public class AlgorithmeGlouton extends Methode {
	
	/**
	 * Constructeur d'AlgorithmeGlouton
	 * @param sac
	 */
	public AlgorithmeGlouton(SacADos sac) {
		super(sac);
	}
	
	/**
	 * Fonction qui permet de résoudre le problème avec l'algorithme glouton 
	 */
	public void resoudre() {
		this.objetsPossibles = quicksort(this.objetsPossibles, 0, this.objetsPossibles.size()-1); 
		
		for (int i=0; i< this.objetsPossibles.size() ; ++i){
			if(this.objetsPossibles.get(i).getPoids() + sac.getPoidsCourant() <= sac.getPoidsMax()) {
				sac.ajouter(this.objetsPossibles.get(i));
			} else
				break;
		}
	}
	
	/**
	 * fonction de tri rapide et récursif
	 * @param objets, la liste d'Item du sac à trier
	 * @param indexDebut
	 * @param indexFin
	 * @return le tableau d'Item trié
	 */
	private ArrayList<Item> quicksort(ArrayList<Item> objets, int indexDebut, int indexFin) {
        if(indexDebut < indexFin) {
            int pivot = indexDebut;
            
            TableauPivot tabPivot = new TableauPivot();
            tabPivot = repartition(objets, indexDebut, indexFin, pivot);
            
            quicksort(tabPivot.getTab(), indexDebut, tabPivot.getPivot()-1); 
			quicksort(tabPivot.getTab(), tabPivot.getPivot()+1, indexFin); 
        }
       
        return objets;
	}
	
	/**
	 * fonction de répartition (met les éléments < pivot a gauche et > pivot à droite)
	 * @return une variable de type TableauPivot contenant le tableau réparti autour du pivot
	 */
	private TableauPivot repartition(ArrayList<Item> objets, int indexDebut, int indexFin, int pivot){
		 TableauPivot tabPivot = new TableauPivot();
			Collections.swap(objets, pivot, indexFin);
	        int i = indexDebut;
	        for (int j = indexDebut; j < indexFin; ++j) {
	            if(objets.get(j).getRapport() >= objets.get(indexFin).getRapport()) {
	                Collections.swap(objets, j, i);
	                i++;
	            }
	        }
	        Collections.swap(objets, indexFin, i);
	        tabPivot.setPivot(i);
	        tabPivot.setTab(objets);
	        return tabPivot;
	}
	
	
		/**
		 * Classe TableauPivot permet de stocker le tableau trié ainsi que le pivot
		 * @author sara
		 *
		 */
		static class TableauPivot {
			private ArrayList<Item> tab;
			private int pivot;
			
			public ArrayList<Item> getTab(){
				return this.tab;
			}
			
			public int getPivot(){
				return this.pivot;
			}
			
			public void setTab(ArrayList<Item> tab){
				this.tab = tab;
			}
			
			public void setPivot(int p){
				this.pivot = p;
			}
		}
		
}
