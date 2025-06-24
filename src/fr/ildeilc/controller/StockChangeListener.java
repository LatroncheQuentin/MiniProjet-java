package fr.ildeilc.controller;

/**
 * Interface pour notifier les panels Stock d'un changement de stock.
 */
public interface StockChangeListener {
    /**
     * Méthode appelée lorsqu'il y a un changement dans le stock.
     */
    void stockChanged();
}
