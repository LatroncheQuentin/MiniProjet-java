package fr.ildeilc.model;

/**
 * Représente une ligne de commande, associant un produit à une quantité.
 * Cette classe permet de calculer le total de la ligne en fonction du prix unitaire du produit et de la quantité.
 */
public class LigneCommande {
    private final Produit produit; // Le produit associé à cette ligne de commande
    private final int quantite; // La quantité de ce produit dans la commande

    /**
     * Constructeur pour créer une ligne de commande.
     *
     * @param produit  Le produit associé à cette ligne de commande.
     * @param quantite La quantité de ce produit dans la commande.
     */
    public LigneCommande(Produit produit, int quantite) {
        this.produit = produit;
        this.quantite = quantite;
    }

    /**
     * Obtient le produit associé à cette ligne de commande.
     *
     * @return Le produit de cette ligne de commande.
     */
    public Produit getProduit() {
        return produit;
    }

    /**
     * Obtient la quantité de ce produit dans la commande.
     *
     * @return La quantité de ce produit.
     */
    public int getQuantite() {
        return quantite;
    }

    /**
     * Calcule le total de la ligne de commande en multipliant le prix unitaire du produit par la quantité.
     *
     * @return Le total de la ligne de commande.
     */
    public double getTotal() {
        return produit.getPrixUnitaire() * quantite;
    }

    @Override
    public String toString() {
        return produit.getNom() + " x " + quantite + " = " + getTotal() + "€";
    }
}