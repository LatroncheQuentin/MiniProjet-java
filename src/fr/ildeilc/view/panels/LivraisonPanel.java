package fr.ildeilc.view.panels;

import fr.ildeilc.controller.AppController;
import fr.ildeilc.controller.CommandeChangeListener;
import fr.ildeilc.model.Commande;

import javax.swing.*;
import java.awt.*;

/**
 * Panel pour la génération des bons de livraison.
 */
public class LivraisonPanel extends JPanel implements CommandeChangeListener {
    private final AppController ctrl;
    private final JComboBox<Commande> cbCommandes;

    /**
     * Construit le panel de génération de bons de livraison.
     * @param ctrl contrôleur principal
     */
    public LivraisonPanel(AppController ctrl) {
        this.ctrl = ctrl;
        setLayout(new BorderLayout());

        cbCommandes = new JComboBox<>();
        ctrl.addCommandeChangeListener(this);
        refresh();

        JButton btnGen = new JButton("Générer Bon de livraison");
        btnGen.addActionListener(e -> {
            Commande c = (Commande) cbCommandes.getSelectedItem();
            if (c != null) {
                try {
                    c.genererBonLivraison().imprimer();
                } catch (IllegalStateException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            }
        });

        add(cbCommandes, BorderLayout.NORTH);
        add(btnGen, BorderLayout.CENTER);
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
