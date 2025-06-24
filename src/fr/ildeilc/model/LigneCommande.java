package fr.ildeilc.model;

/**
 * Représente une ligne de commande (un produit et une quantité).
 */
public class LigneCommande {
    /** Produit commandé */
    private final Produit produit;
    /** Quantité commandée */
    private final int quantite;

    /**
     * Construit une ligne de commande.
     * @param produit produit concerné
     * @param quantite quantité commandée
     */
    public LigneCommande(Produit produit, int quantite) {
        this.produit = produit;
        this.quantite = quantite;
    }

    /**
     * Retourne le produit de la ligne.
     * @return produit
     */
    public Produit getProduit() { return produit; }

    /**
     * Retourne la quantité commandée.
     * @return quantité
     */
    public int getQuantite() { return quantite; }

    /**
     * Calcule le total de la ligne (prix unitaire * quantité).
     * @return total
     */
    public double getTotal() { return produit.getPrixUnitaire() * quantite; }

    /**
     * Retourne une représentation textuelle de la ligne de commande.
     * @return chaîne descriptive
     */
    @Override
    public String toString() {
        return produit.getNom() + " x " + quantite + " = " + getTotal() + "€";
    }
}
