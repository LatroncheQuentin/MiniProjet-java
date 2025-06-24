package fr.ildeilc.model;

/**
 * Génère une facture à partir d'une commande.
 * Cette classe contient les détails de la commande associée et 
 * fournit une méthode pour imprimer la facture.
 */
public class Facture {
    private final Commande commande;

    /**
     * Constructeur de la classe Facture.
     * 
     * @param commande La commande associée à cette facture.
     */
    public Facture(Commande commande) {
        this.commande = commande;
    }

    /**
     * Imprime les détails de la facture.
     * Affiche le numéro de commande, le nom du client et le total de la commande.
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