// filepath: /Users/quentin/Documents/limayrac/BSI/java/MiniProjet-java/src/fr/ildeilc/model/Produit.java
package fr.ildeilc.model;

/**
 * Représente un produit en stock.
 * Cette classe contient des informations sur le produit, telles que son nom,
 * son prix unitaire et sa quantité en stock. Elle fournit également des méthodes
 * pour gérer ces informations, y compris la possibilité d'ajouter ou de retirer
 * du stock, ainsi que de convertir les données du produit en format CSV.
 */
public class Produit {
    private final int id;
    private String nom;
    private double prixUnitaire;
    private int quantiteEnStock;

    /**
     * Constructeur pour créer un produit avec un identifiant, un nom, un prix unitaire
     * et une quantité en stock.
     *
     * @param id            L'identifiant unique du produit.
     * @param nom           Le nom du produit.
     * @param prixUnitaire  Le prix unitaire du produit.
     * @param quantite     La quantité en stock du produit.
     */
    public Produit(int id, String nom, double prixUnitaire, int quantite) {
        this.id = id;
        this.nom = nom;
        this.prixUnitaire = prixUnitaire;
        this.quantiteEnStock = quantite;
    }

    public int getId() { return id; }

    public String getNom() { return nom; }

    public double getPrixUnitaire() { return prixUnitaire; }

    public int getQuantiteEnStock() { return quantiteEnStock; }

    /**
     * Retire une quantité spécifiée du stock.
     *
     * @param q La quantité à retirer.
     * @throws IllegalStateException Si la quantité demandée dépasse le stock disponible.
     */
    public void retirerStock(int q) {
        if (q <= quantiteEnStock) quantiteEnStock -= q;
        else throw new IllegalStateException("Stock insuffisant");
    }

    /**
     * Ajoute une quantité spécifiée au stock.
     *
     * @param q La quantité à ajouter.
     */
    public void ajouterStock(int q) {
        quantiteEnStock += q;
    }

    /**
     * Modifie le nom du produit.
     *
     * @param nom Le nouveau nom du produit.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Modifie le prix unitaire du produit.
     *
     * @param prix Le nouveau prix unitaire du produit.
     */
    public void setPrix(double prix) {
        this.prixUnitaire = prix;
    }

    /**
     * Modifie la quantité en stock du produit.
     *
     * @param quantite La nouvelle quantité en stock du produit.
     */
    public void setQuantite(int quantite) {
        this.quantiteEnStock = quantite;
    }

    @Override
    public String toString() {
        return id + ": " + nom + " (" + quantiteEnStock + " en stock, " + prixUnitaire + "€/u)";
    }

    /**
     * Convertit les données du produit en format CSV.
     *
     * @return Une chaîne représentant le produit au format CSV.
     */
    public String toCsv() {
        return id + "," + nom + "," + prixUnitaire + "," + quantiteEnStock;
    }

    /**
     * Crée un produit à partir d'une chaîne CSV.
     *
     * @param line La chaîne CSV contenant les données du produit.
     * @return Un nouvel objet Produit.
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