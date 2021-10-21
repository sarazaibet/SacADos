package sacADos;

public class Item {

	private String nom;
	private double valeur;
	private double poids;
	private double rapport;
	
	/**
	 * Constructeur d'Item
	 * @param nom
	 * @param poids
	 * @param valeur
	 */
	public Item(String nom, double poids, double valeur) {
		this.nom = nom;
		this.poids = poids;
		this.valeur = valeur;
		this.rapport = valeur/poids;
	}
	
	public String toString() {
        return this.nom + " : " +
                "poids= " + this.poids +
                ", valeur= " + this.valeur;
	}
	
	
	/////////// Getter ///////////
	
	public double getRapport() {
		return this.rapport;
	}
	
	public double getPoids() {
		return this.poids;		
	}
	
	public double getValeur() {
		return this.valeur;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	
}
