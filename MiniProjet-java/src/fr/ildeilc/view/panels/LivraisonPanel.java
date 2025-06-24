// filepath: /Users/quentin/Documents/limayrac/BSI/java/MiniProjet-java/src/fr/ildeilc/view/panels/LivraisonPanel.java
package fr.ildeilc.view.panels;

import fr.ildeilc.controller.AppController;
import fr.ildeilc.controller.CommandeChangeListener;
import fr.ildeilc.model.Commande;

import javax.swing.*;
import java.awt.*;

/**
 * LivraisonPanel est un panneau qui permet de générer des bons de livraison
 * à partir des commandes existantes. Il affiche une liste déroulante des
 * commandes disponibles et permet à l'utilisateur de générer un bon de
 * livraison pour la commande sélectionnée.
 */
public class LivraisonPanel extends JPanel implements CommandeChangeListener {
    private final AppController ctrl; // Contrôleur principal de l'application
    private final JComboBox<Commande> cbCommandes; // Liste déroulante des commandes

    /**
     * Constructeur de la classe LivraisonPanel.
     * 
     * @param ctrl Le contrôleur principal de l'application.
     */
    public LivraisonPanel(AppController ctrl) {
        this.ctrl = ctrl;
        setLayout(new BorderLayout());

        cbCommandes = new JComboBox<>(); // Initialisation de la liste déroulante
        ctrl.addCommandeChangeListener(this); // Ajout du listener pour les changements de commande
        refresh(); // Rafraîchissement de la liste des commandes

        JButton btnGen = new JButton("Générer Bon de livraison"); // Bouton pour générer le bon de livraison
        btnGen.addActionListener(e -> {
            Commande c = (Commande) cbCommandes.getSelectedItem(); // Récupération de la commande sélectionnée
            if (c != null) {
                try {
                    c.genererBonLivraison().imprimer(); // Génération et impression du bon de livraison
                } catch (IllegalStateException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage()); // Affichage d'un message d'erreur
                }
            }
        });

        add(cbCommandes, BorderLayout.NORTH); // Ajout de la liste déroulante au panneau
        add(btnGen, BorderLayout.CENTER); // Ajout du bouton au panneau
    }

    /**
     * Rafraîchit la liste des commandes affichées dans le JComboBox.
     */
    private void refresh() {
        cbCommandes.removeAllItems(); // Suppression de toutes les commandes existantes
        for (Commande c : ctrl.getCommandes()) {
            cbCommandes.addItem(c); // Ajout des commandes disponibles à la liste déroulante
        }
    }

    /**
     * Méthode appelée lorsque les commandes changent. Elle rafraîchit la liste
     * des commandes affichées.
     */
    @Override
    public void commandesChanged() {
        refresh(); // Rafraîchissement de la liste des commandes
    }
}