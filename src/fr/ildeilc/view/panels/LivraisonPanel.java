package fr.ildeilc.view.panels;

import fr.ildeilc.controller.AppController;
import fr.ildeilc.controller.CommandeChangeListener;
import fr.ildeilc.model.Commande;

import javax.swing.*;
import java.awt.*;

public class LivraisonPanel extends JPanel implements CommandeChangeListener {
    private final AppController ctrl;
    private final JComboBox<Commande> cbCommandes;

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

    private void refresh() {
        cbCommandes.removeAllItems();
        for (Commande c : ctrl.getCommandes()) {
            cbCommandes.addItem(c);
        }
    }

    @Override
    public void commandesChanged() {
        refresh();
    }
}
