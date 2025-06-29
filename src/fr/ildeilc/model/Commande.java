package fr.ildeilc.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Représente une commande client.
 */
public class Commande {
    /**
     * Statut d'une commande.
     */
    public enum Status { EN_COURS, EN_ATTENTE_STOCK, LIVREE, PAYEE }

    /** Identifiant de la commande */
    private final int id;
    /** Client ayant passé la commande */
    private final Client client;
    /** Date de la commande */
    private final Date date;
    /** Lignes de la commande */
    private final List<LigneCommande> lignes;
    /** Statut de la commande */
    private Status status;

    /**
     * Construit une commande pour un client donné.
     * @param id identifiant de la commande
     * @param client client associé
     */
    public Commande(int id, Client client) {
        this.id = id;
        this.client = client;
        this.date = new Date();
        this.lignes = new ArrayList<>();
        this.status = Status.EN_COURS;
    }

    /**
     * Retourne l'identifiant de la commande.
     * @return id
     */
    public int getId() { return id; }

    /**
     * Retourne le client associé à la commande.
     * @return client
     */
    public Client getClient() { return client; }

    /**
     * Retourne la date de la commande.
     * @return date
     */
    public Date getDate() { return date; }

    /**
     * Retourne la liste des lignes de commande.
     * @return lignes
     */
    public List<LigneCommande> getLignes() { return lignes; }

    /**
     * Retourne le statut de la commande.
     * @return statut
     */
    public Status getStatus() { return status; }

    /**
     * Calcule le total de la commande.
     * @return montant total
     */
    public double getTotal() {
        return lignes.stream().mapToDouble(LigneCommande::getTotal).sum();
    }

    /**
     * Ajoute une ligne à la commande.
     * @param p produit
     * @param q quantité
     * @throws IllegalStateException si le stock est insuffisant
     */
    public void ajouterLigne(Produit p, int q) {
        if (p.getQuantiteEnStock() < q) {
            status = Status.EN_ATTENTE_STOCK;
        } else {
            p.retirerStock(q);
            lignes.add(new LigneCommande(p, q));
            status = Status.EN_COURS;
        }
    }

    /**
     * Génère un bon de livraison pour la commande.
     * @return bon de livraison
     * @throws IllegalStateException si le stock est insuffisant
     */
    public BonLivraison genererBonLivraison() {
        if (status == Status.EN_ATTENTE_STOCK) {
            throw new IllegalStateException("Stock insuffisant pour livraison");
        }
        status = Status.LIVREE;
        return new BonLivraison(this);
    }

    /**
     * Génère une facture pour la commande.
     * @return facture
     */
    public Facture genererFacture() {
        status = Status.PAYEE;
        return new Facture(this);
    }

    /**
     * Retourne une représentation textuelle de la commande.
     * @return chaîne descriptive
     */
    @Override
    public String toString() {
        // Format de date : jour/mois/année
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return "Commande n°" + id
             + " du " + df.format(date)
             + " effectué par " + client.getNom();
    }
}
