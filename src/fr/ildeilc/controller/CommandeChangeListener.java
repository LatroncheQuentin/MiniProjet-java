package fr.ildeilc.controller;

//import fr.ildeilc.model.Commande;

/**
 * Pour notifier les panels Livraison/Facture d'une nouvelle commande.
 */
public interface CommandeChangeListener {
    void commandesChanged();
}
