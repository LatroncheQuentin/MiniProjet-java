package fr.ildeilc.view.panels;

import fr.ildeilc.controller.AppController;
import fr.ildeilc.model.*;

import javax.swing.*;
import java.awt.*;

public class CommandePanel extends JPanel {
    private final AppController ctrl;
    private final JComboBox<Produit> cbProduits;
    private final JTextField tfQuantite;
    private final DefaultListModel<LigneCommande> model;

    public CommandePanel(AppController ctrl) {
        this.ctrl = ctrl;
        setLayout(new BorderLayout());

        model = new DefaultListModel<>();
        JList<LigneCommande> list = new JList<>(model);
        add(new JScrollPane(list), BorderLayout.CENTER);

        JPanel north = new JPanel();
        cbProduits = new JComboBox<>();
        refreshProduits();

        tfQuantite = new JTextField(5);

        JButton btnRefreshProd = new JButton("Rafraîchir produits");
        btnRefreshProd.addActionListener(e -> refreshProduits());

        JButton btnNew = new JButton("Nouvelle commande");
        btnNew.addActionListener(e -> {
            String nom = JOptionPane.showInputDialog(this, "Nom du client :");
            if (nom == null || nom.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nom client non valide.");
                return;
            }
            String tel = JOptionPane.showInputDialog(this, "Téléphone :");
            if (tel == null || tel.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Téléphone non valide.");
                return;
            }
            Client c = new Client(0, nom.trim(), tel.trim());
            ctrl.creerCommande(c);
            model.clear();
            JOptionPane.showMessageDialog(this,
                "Commande créée (ID: " + ctrl.getCurrentCommande().getId() + ")");
        });

        JButton btnAdd = new JButton("Ajouter au panier");
        btnAdd.addActionListener(e -> {
            Commande current = ctrl.getCurrentCommande();
            if (current == null) {
                JOptionPane.showMessageDialog(this,
                    "Aucune commande en cours. Créez-en une d'abord.");
                return;
            }
            String qText = tfQuantite.getText().trim();
            if (qText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez saisir une quantité.");
                return;
            }
            int q;
            try {
                q = Integer.parseInt(qText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Quantité invalide.");
                return;
            }
            Produit p = (Produit) cbProduits.getSelectedItem();
            if (p == null) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner un produit.");
                return;
            }
            try {
                // décrémente et ajoute la ligne
                current.ajouterLigne(p, q);

                // persister le stock (uniquement)
                ctrl.enregistrerStock();

                // rafraîchir l'affichage
                refreshLines();
                JOptionPane.showMessageDialog(this, "Ligne ajoutée.");
            } catch (IllegalStateException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        north.add(new JLabel("Produit :"));    north.add(cbProduits);
        north.add(btnRefreshProd);
        north.add(new JLabel("Quantité :"));   north.add(tfQuantite);
        north.add(btnNew);                     north.add(btnAdd);
        add(north, BorderLayout.NORTH);
    }

    private void refreshProduits() {
        cbProduits.removeAllItems();
        for (Produit p : ctrl.getStock().getProduits()) {
            cbProduits.addItem(p);
        }
    }

    private void refreshLines() {
        model.clear();
        Commande cmd = ctrl.getCurrentCommande();
        if (cmd != null) {
            for (LigneCommande lc : cmd.getLignes()) {
                model.addElement(lc);
            }
        }
    }
}
