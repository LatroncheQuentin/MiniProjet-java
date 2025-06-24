package fr.ildeilc.model;

/**
 * Représente un produit en stock.
 */
public class Produit {
    private final int id;
    private String nom;
    private double prixUnitaire;
    private int quantiteEnStock;
   


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

    public void retirerStock(int q) {
        if (q <= quantiteEnStock) quantiteEnStock -= q;
        else throw new IllegalStateException("Stock insuffisant");
    }

    public void ajouterStock(int q) {
        quantiteEnStock += q;
    }

    public void setNom(String nom) {
    this.nom = nom;
}

public void setPrix(double prix) {
    this.prixUnitaire = prix;
}

public void setQuantite(int quantite) {
    this.quantiteEnStock = quantite;
}

    @Override
    public String toString() {
        return id + ": " + nom + " (" + quantiteEnStock + " en stock, " + prixUnitaire + "€/u)";
    }

    public String toCsv() {
        return id + "," + nom + "," + prixUnitaire + "," + quantiteEnStock;
    }

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
