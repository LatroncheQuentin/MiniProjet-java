package fr.ildeilc.model;

/**
 * Représente une ligne de commande.
 */
public class LigneCommande {
    private final Produit produit;
    private final int quantite;

    public LigneCommande(Produit produit, int quantite) {
        this.produit = produit;
        this.quantite = quantite;
    }

    public Produit getProduit() { return produit; }
    public int getQuantite() { return quantite; }
    public double getTotal() { return produit.getPrixUnitaire() * quantite; }

    @Override
    public String toString() {
        return produit.getNom() + " x " + quantite + " = " + getTotal() + "€";
    }
}
