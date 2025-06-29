package fr.ildeilc.model;

/**
 * Génère une facture pour une commande.
 */
public class Facture {
    /** Commande associée à la facture */
    private final Commande commande;

    /**
     * Construit une facture pour la commande donnée.
     * @param commande la commande concernée
     */
    public Facture(Commande commande) {
        this.commande = commande;
    }

    /**
     * Imprime la facture sur la sortie standard.
     */
    public void imprimer() {
        System.out.println("\n--- FACTURE ---");
        System.out.println("Commande #" + commande.getId() + " pour " + commande.getClient().getNom());
        for (LigneCommande l : commande.getLignes()) {
            System.out.println(" - " + l);
        }
        System.out.println("TOTAL : " + commande.getTotal() + "€");
    }
}
