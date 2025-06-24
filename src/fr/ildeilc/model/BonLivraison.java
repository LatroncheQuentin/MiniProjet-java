package fr.ildeilc.model;

/**
 * Génère un bon de livraison pour une commande.
 */
public class BonLivraison {
    /** Commande associée au bon de livraison */
    private final Commande commande;

    /**
     * Construit un bon de livraison pour la commande donnée.
     * @param commande la commande concernée
     */
    public BonLivraison(Commande commande) {
        this.commande = commande;
    }

    /**
     * Imprime le bon de livraison sur la sortie standard.
     */
    public void imprimer() {
        System.out.println("\n--- BON DE LIVRAISON ---");
        System.out.println("Commande #" + commande.getId() + " pour " + commande.getClient().getNom());
        for (LigneCommande l : commande.getLignes()) {
            System.out.println(" - " + l.getProduit().getNom() + " x " + l.getQuantite());
        }
    }
}
