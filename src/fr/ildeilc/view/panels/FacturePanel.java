package fr.ildeilc.view.panels;

import fr.ildeilc.controller.AppController;
import fr.ildeilc.controller.CommandeChangeListener;
import fr.ildeilc.model.Commande;

import javax.swing.*;
import java.awt.*;

/**
 * Panel pour la génération des factures.
 */
public class FacturePanel extends JPanel implements CommandeChangeListener {
    private final AppController ctrl;
    private final JComboBox<Commande> cbCommandes;

    /**
     * Construit le panel de génération de factures.
     * @param ctrl contrôleur principal
     */
    public FacturePanel(AppController ctrl) {
        this.ctrl = ctrl;
        setLayout(new BorderLayout());

        cbCommandes = new JComboBox<>();
        ctrl.addCommandeChangeListener(this);
        refresh();

        JButton btnFact = new JButton("Générer Facture");
        btnFact.addActionListener(e -> {
            Commande c = (Commande) cbCommandes.getSelectedItem();
            if (c != null) {
                c.genererFacture().imprimer();
            }
        });

        add(cbCommandes, BorderLayout.NORTH);
        add(btnFact, BorderLayout.CENTER);
    }

    /**
     * Rafraîchit la liste des commandes.
     */
    private void refresh() {
        cbCommandes.removeAllItems();
        for (Commande c : ctrl.getCommandes()) {
            cbCommandes.addItem(c);
        }
    }

    /**
     * Méthode appelée lors d'un changement de commandes.
     */
    @Override
    public void commandesChanged() {
        refresh();
    }
}
