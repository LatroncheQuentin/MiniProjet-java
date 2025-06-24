// filepath: /Users/quentin/Documents/limayrac/BSI/java/MiniProjet-java/src/fr/ildeilc/view/panels/FacturePanel.java
package fr.ildeilc.view.panels;

import fr.ildeilc.controller.AppController;
import fr.ildeilc.controller.CommandeChangeListener;
import fr.ildeilc.model.Commande;

import javax.swing.*;
import java.awt.*;

/**
 * FacturePanel est un panneau qui permet de générer des factures à partir des commandes existantes.
 * Il affiche une liste déroulante des commandes et un bouton pour générer la facture correspondante.
 * 
 * Ce panneau implémente l'interface CommandeChangeListener pour être notifié des changements dans les commandes.
 */
public class FacturePanel extends JPanel implements CommandeChangeListener {
    private final AppController ctrl; // Contrôleur principal de l'application
    private final JComboBox<Commande> cbCommandes; // Liste déroulante des commandes

    /**
     * Constructeur de FacturePanel.
     * 
     * @param ctrl Le contrôleur principal de l'application.
     */
    public FacturePanel(AppController ctrl) {
        this.ctrl = ctrl; // Initialisation du contrôleur
        setLayout(new BorderLayout()); // Définition de la mise en page

        cbCommandes = new JComboBox<>(); // Initialisation de la liste déroulante
        ctrl.addCommandeChangeListener(this); // Ajout du listener pour les changements de commandes
        refresh(); // Rafraîchissement de la liste des commandes

        JButton btnFact = new JButton("Générer Facture"); // Bouton pour générer la facture
        btnFact.addActionListener(e -> {
            Commande c = (Commande) cbCommandes.getSelectedItem(); // Récupération de la commande sélectionnée
            if (c != null) {
                c.genererFacture().imprimer(); // Génération et impression de la facture
            }
        });

        add(cbCommandes, BorderLayout.NORTH); // Ajout de la liste déroulante au panneau
        add(btnFact, BorderLayout.CENTER); // Ajout du bouton au panneau
    }

    /**
     * Rafraîchit la liste des commandes affichées dans le panneau.
     */
    private void refresh() {
        cbCommandes.removeAllItems(); // Suppression de tous les éléments de la liste
        for (Commande c : ctrl.getCommandes()) { // Ajout des commandes au panneau
            cbCommandes.addItem(c);
        }
    }

    /**
     * Méthode appelée lorsque les commandes changent.
     * Elle rafraîchit la liste des commandes affichées.
     */
    @Override
    public void commandesChanged() {
        refresh(); // Rafraîchissement de la liste des commandes
    }
}