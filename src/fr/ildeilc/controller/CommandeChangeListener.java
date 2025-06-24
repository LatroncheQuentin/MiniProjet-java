package fr.ildeilc.controller;

/**
 * Interface pour notifier les panels Livraison/Facture d'une nouvelle commande ou d'un changement.
 */
public interface CommandeChangeListener {
    /**
     * Méthode appelée lorsqu'il y a un changement dans les commandes.
     */
    void commandesChanged();
}
