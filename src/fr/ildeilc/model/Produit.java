package fr.ildeilc.model;

/**
 * Représente un produit en stock.
 */
public class Produit {
    /** Identifiant du produit */
    private final int id;
    /** Nom du produit */
    private String nom;
    /** Prix unitaire du produit */
    private double prixUnitaire;
    /** Quantité en stock */
    private int quantiteEnStock;
   
    /**
     * Construit un produit.
     * @param id identifiant
     * @param nom nom
     * @param prixUnitaire prix unitaire
     * @param quantite quantité en stock
     */
    public Produit(int id, String nom, double prixUnitaire, int quantite) {
        this.id = id;
        this.nom = nom;
        this.prixUnitaire = prixUnitaire;
        this.quantiteEnStock = quantite;
    }

    /**
     * Retourne l'identifiant du produit.
     * @return id
     */
    public int getId() { return id; }

    /**
     * Retourne le nom du produit.
     * @return nom
     */
    public String getNom() { return nom; }

    /**
     * Retourne le prix unitaire du produit.
     * @return prix unitaire
     */
    public double getPrixUnitaire() { return prixUnitaire; }

    /**
     * Retourne la quantité en stock.
     * @return quantité en stock
     */
    public int getQuantiteEnStock() { return quantiteEnStock; }

    /**
     * Retire une quantité du stock.
     * @param q quantité à retirer
     * @throws IllegalStateException si stock insuffisant
     */
    public void retirerStock(int q) {
        if (q <= quantiteEnStock) quantiteEnStock -= q;
        else throw new IllegalStateException("Stock insuffisant");
    }

    /**
     * Ajoute une quantité au stock.
     * @param q quantité à ajouter
     */
    public void ajouterStock(int q) {
        quantiteEnStock += q;
    }

    /**
     * Modifie le nom du produit.
     * @param nom nouveau nom
     */
    public void setNom(String nom) {
    this.nom = nom;
}

    /**
     * Modifie le prix du produit.
     * @param prix nouveau prix
     */
    public void setPrix(double prix) {
    this.prixUnitaire = prix;
}

    /**
     * Modifie la quantité en stock.
     * @param quantite nouvelle quantité
     */
    public void setQuantite(int quantite) {
    this.quantiteEnStock = quantite;
}

    /**
     * Retourne une représentation textuelle du produit.
     * @return chaîne descriptive
     */
    @Override
    public String toString() {
        return id + ": " + nom + " (" + quantiteEnStock + " en stock, " + prixUnitaire + "€/u)";
    }

    /**
     * Retourne une représentation CSV du produit.
     * @return ligne CSV
     */
    public String toCsv() {
        return id + "," + nom + "," + prixUnitaire + "," + quantiteEnStock;
    }

    /**
     * Construit un produit à partir d'une ligne CSV.
     * @param line ligne CSV
     * @return produit
     */
    public static Produit fromCsv(String line) {
        String[] s = line.split(",");
        return new Produit(
            Integer.parseInt(s[0]),
            s[1],
            Double.parseDouble(s[2]),
            Integer.parseInt(s[3])
        );
    }
}
