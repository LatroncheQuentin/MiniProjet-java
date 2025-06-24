package fr.ildeilc.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Représente une commande client.
 */
public class Commande {
    public enum Status { EN_COURS, EN_ATTENTE_STOCK, LIVREE, PAYEE }

    private final int id;
    private final Client client;
    private final Date date;
    private final List<LigneCommande> lignes;
    private Status status;

    public Commande(int id, Client client) {
        this.id = id;
        this.client = client;
        this.date = new Date();
        this.lignes = new ArrayList<>();
        this.status = Status.EN_COURS;
    }

    public int getId() { return id; }
    public Client getClient() { return client; }
    public Date getDate() { return date; }
    public List<LigneCommande> getLignes() { return lignes; }
    public Status getStatus() { return status; }
    public double getTotal() {
        return lignes.stream().mapToDouble(LigneCommande::getTotal).sum();
    }

    public void ajouterLigne(Produit p, int q) {
        if (p.getQuantiteEnStock() < q) {
            status = Status.EN_ATTENTE_STOCK;
        } else {
            p.retirerStock(q);
            lignes.add(new LigneCommande(p, q));
            status = Status.EN_COURS;
        }
    }

    public BonLivraison genererBonLivraison() {
        if (status == Status.EN_ATTENTE_STOCK) {
            throw new IllegalStateException("Stock insuffisant pour livraison");
        }
        status = Status.LIVREE;
        return new BonLivraison(this);
    }

    public Facture genererFacture() {
        status = Status.PAYEE;
        return new Facture(this);
    }

    @Override
    public String toString() {
        // Format de date : jour/mois/année
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return "Commande n°" + id
             + " du " + df.format(date)
             + " effectué par " + client.getNom();
    }
}
