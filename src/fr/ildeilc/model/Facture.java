package fr.ildeilc.model;

/**
 * Génère une facture.
 */
public class Facture {
    private final Commande commande;

    public Facture(Commande commande) {
        this.commande = commande;
    }

    public void imprimer() {
        System.out.println("\n--- FACTURE ---");
        System.out.println("Commande #" + commande.getId() + " pour " + commande.getClient().getNom());
        for (LigneCommande l : commande.getLignes()) {
            System.out.println(" - " + l);
        }
        System.out.println("TOTAL : " + commande.getTotal() + "€");
    }
}
