package fr.ildeilc.controller;

import fr.ildeilc.model.*;
import fr.ildeilc.view.MainFrame;

import java.util.*;

/**
 * Contrôleur principal de l'application.
 * Gère le stock, les commandes, la persistance et la communication avec la vue.
 */
public class AppController {
    /** Stock de produits de l'application */
    private final Stock stock;
    /** Fenêtre principale */
    private final MainFrame frame;
    /** Liste des commandes */
    private final List<Commande> commandes;
    /** Commande en cours */
    private Commande currentCommande;
    /** Prochain identifiant de commande */
    private int nextCommandeId = 1;

    // listeners
    /** Listeners pour les changements de stock */
    private final List<StockChangeListener> stockListeners = new ArrayList<>();
    /** Listeners pour les changements de commandes */
    private final List<CommandeChangeListener> cmdListeners = new ArrayList<>();

    /**
     * Construit le contrôleur principal et initialise les données.
     */
    public AppController() {
        stock = new Stock();
        commandes = new ArrayList<>();

        // Charger le stock AVANT d'instancier la vue
        chargerDonnees();

        frame = new MainFrame(this);
    }

    /**
     * Charge les données du stock depuis le fichier CSV ou initialise des données par défaut.
     */
    private void chargerDonnees() {
        try {
            stock.loadCsv("stock.csv");
        } catch (Exception e) {
            // données initiales si pas de fichier
            stock.ajouterProduit(new Produit(1, "Ordinateur portable", 999.99, 10));
            stock.ajouterProduit(new Produit(2, "Écran 24\"", 199.99, 20));
        }
    }

    /**
     * Sauvegarde le stock dans le fichier CSV et notifie les listeners.
     */
    public void enregistrerStock() {
        try {
            stock.saveCsv("stock.csv");
            fireStockChanged();
        } catch (Exception ignored) {}
    }

    /**
     * Retourne le stock courant.
     * @return le stock
     */
    public Stock getStock() {
        return stock;
    }

    /**
     * Retourne la liste des commandes.
     * @return liste des commandes
     */
    public List<Commande> getCommandes() {
        return commandes;
    }

    /**
     * Retourne la commande en cours.
     * @return commande courante
     */
    public Commande getCurrentCommande() {
        return currentCommande;
    }

    /**
     * Définit la commande courante.
     * @param c commande à définir comme courante
     */
    public void setCurrentCommande(Commande c) {
        currentCommande = c;
    }

    /**
     * Crée une nouvelle commande pour un client donné.
     * @param c le client
     * @return la commande créée
     */
    public Commande creerCommande(Client c) {
        Commande cmd = new Commande(nextCommandeId++, c);
        commandes.add(cmd);
        currentCommande = cmd;
        fireCommandesChanged();
        return cmd;
    }


    /** Listeners */

    /**
     * Ajoute un listener pour les changements de stock.
     * @param l listener à ajouter
     */
    public void addStockChangeListener(StockChangeListener l) {
        stockListeners.add(l);
    }
    /**
     * Notifie tous les listeners d'un changement de stock.
     */
    private void fireStockChanged() {
        stockListeners.forEach(StockChangeListener::stockChanged);
    }

    /**
     * Ajoute un listener pour les changements de commandes.
     * @param l listener à ajouter
     */
    public void addCommandeChangeListener(CommandeChangeListener l) {
        cmdListeners.add(l);
    }
    /**
     * Notifie tous les listeners d'un changement de commandes.
     */
    private void fireCommandesChanged() {
        cmdListeners.forEach(CommandeChangeListener::commandesChanged);
    }

    /**
     * Démarre l'application en affichant la fenêtre principale.
     */
    public void demarrer() {
        frame.setVisible(true);
    }

    /**
     * Point d'entrée principal de l'application.
     * @param args arguments de la ligne de commande
     */
    public static void main(String[] args) {
        new AppController().demarrer();
    }
}
