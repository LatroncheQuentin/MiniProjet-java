package fr.ildeilc.model;

/**
 * Génère un bon de livraison.
 */
public class BonLivraison {
    private final Commande commande;

    public BonLivraison(Commande commande) {
        this.commande = commande;
    }

    public void imprimer() {
        System.out.println("\n--- BON DE LIVRAISON ---");
        System.out.println("Commande #" + commande.getId() + " pour " + commande.getClient().getNom());
        for (LigneCommande l : commande.getLignes()) {
            System.out.println(" - " + l.getProduit().getNom() + " x " + l.getQuantite());
        }
    }
}
