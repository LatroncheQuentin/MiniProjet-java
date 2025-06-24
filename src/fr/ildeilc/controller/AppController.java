package fr.ildeilc.controller;

import fr.ildeilc.model.*;
import fr.ildeilc.view.MainFrame;

import java.util.*;

/**
 * Contrôleur principal de l'application.
 */
public class AppController {
    private final Stock stock;
    private final MainFrame frame;
    private final List<Commande> commandes;
    private Commande currentCommande;
    private int nextCommandeId = 1;

    // listeners
    private final List<StockChangeListener> stockListeners = new ArrayList<>();
    private final List<CommandeChangeListener> cmdListeners = new ArrayList<>();

    public AppController() {
        stock = new Stock();
        commandes = new ArrayList<>();

        // Charger le stock AVANT d'instancier la vue
        chargerDonnees();

        frame = new MainFrame(this);
    }

    private void chargerDonnees() {
        try {
            stock.loadCsv("stock.csv");
        } catch (Exception e) {
            // données initiales si pas de fichier
            stock.ajouterProduit(new Produit(1, "Ordinateur portable", 999.99, 10));
            stock.ajouterProduit(new Produit(2, "Écran 24\"", 199.99, 20));
        }
    }

    public void enregistrerStock() {
        try {
            stock.saveCsv("stock.csv");
            fireStockChanged();
        } catch (Exception ignored) {}
    }

    public Stock getStock() {
        return stock;
    }

    public List<Commande> getCommandes() {
        return commandes;
    }

    public Commande getCurrentCommande() {
        return currentCommande;
    }

    public void setCurrentCommande(Commande c) {
        currentCommande = c;
    }

    public Commande creerCommande(Client c) {
        Commande cmd = new Commande(nextCommandeId++, c);
        commandes.add(cmd);
        currentCommande = cmd;
        fireCommandesChanged();
        return cmd;
    }


    /** Listeners */
    public void addStockChangeListener(StockChangeListener l) {
        stockListeners.add(l);
    }
    private void fireStockChanged() {
        stockListeners.forEach(StockChangeListener::stockChanged);
    }

    public void addCommandeChangeListener(CommandeChangeListener l) {
        cmdListeners.add(l);
    }
    private void fireCommandesChanged() {
        cmdListeners.forEach(CommandeChangeListener::commandesChanged);
    }

    public void demarrer() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new AppController().demarrer();
    }
}
