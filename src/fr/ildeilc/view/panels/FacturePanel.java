package fr.ildeilc.view.panels;

import fr.ildeilc.controller.AppController;
import fr.ildeilc.controller.CommandeChangeListener;
import fr.ildeilc.model.Commande;

import javax.swing.*;
import java.awt.*;

public class FacturePanel extends JPanel implements CommandeChangeListener {
    private final AppController ctrl;
    private final JComboBox<Commande> cbCommandes;

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
