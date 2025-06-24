// filepath: /Users/quentin/Documents/limayrac/BSI/java/MiniProjet-java/src/fr/ildeilc/view/panels/StockPanel.java

package fr.ildeilc.view.panels;

import fr.ildeilc.controller.AppController;
import fr.ildeilc.controller.StockChangeListener;
import fr.ildeilc.model.Produit;

import javax.swing.*;
import java.awt.*;

/**
 * StockPanel est un panneau qui permet de gérer le stock de produits.
 * Il offre des fonctionnalités pour ajouter, mettre à jour et supprimer des produits.
 * 
 * Ce panneau affiche une liste des produits disponibles et fournit un formulaire
 * pour modifier les détails d'un produit sélectionné ou pour créer un nouveau produit.
 * 
 * Les actions effectuées dans ce panneau sont reflétées dans le modèle de données
 * et les changements de stock sont notifiés aux autres composants de l'application.
 */
public class StockPanel extends JPanel implements StockChangeListener {
    private final AppController ctrl;
    private final DefaultListModel<Produit> model;
    private final JComboBox<Produit> cbProduits;
    private final JTextField tfNom, tfPrix, tfQuantite;

    // Champs pour nouveau produit
    private final JTextField tfNewNom, tfNewPrix, tfNewQuantite;

    /**
     * Constructeur de StockPanel.
     * 
     * @param ctrl Le contrôleur de l'application qui gère les interactions avec le modèle.
     */
    public StockPanel(AppController ctrl) {
        this.ctrl = ctrl;
        setLayout(new BorderLayout());

        // Liste des produits affichée
        model = new DefaultListModel<>();
        JList<Produit> list = new JList<>(model);
        add(new JScrollPane(list), BorderLayout.CENTER);

        // Formulaire de modification
        JPanel editPanel = new JPanel(new GridLayout(4, 2, 10, 5));
        cbProduits = new JComboBox<>();
        tfNom = new JTextField(15);
        tfPrix = new JTextField(6);
        tfQuantite = new JTextField(5);

        cbProduits.addActionListener(e -> remplirChampsDepuisProduit());

        JButton btnMaj = new JButton("Mettre à jour");
        btnMaj.addActionListener(e -> mettreAJourProduit());

        JButton btnSuppr = new JButton("Supprimer");
        btnSuppr.addActionListener(e -> supprimerProduit());

        editPanel.add(new JLabel("Produit :"));
        editPanel.add(cbProduits);
        editPanel.add(new JLabel("Nom :"));
        editPanel.add(tfNom);
        editPanel.add(new JLabel("Prix (€) :"));
        editPanel.add(tfPrix);
        editPanel.add(new JLabel("Quantité :"));
        editPanel.add(tfQuantite);

        // Formulaire de création
        JPanel createPanel = new JPanel(new GridLayout(4, 2, 10, 5));
        tfNewNom = new JTextField(15);
        tfNewPrix = new JTextField(6);
        tfNewQuantite = new JTextField(5);

        JButton btnCreer = new JButton("Créer produit");
        btnCreer.addActionListener(e -> creerProduit());

        createPanel.add(new JLabel("Nouveau produit"));
        createPanel.add(new JLabel());
        createPanel.add(new JLabel("Nom :"));
        createPanel.add(tfNewNom);
        createPanel.add(new JLabel("Prix (€) :"));
        createPanel.add(tfNewPrix);
        createPanel.add(new JLabel("Quantité :"));
        createPanel.add(tfNewQuantite);

        // Boutons d'action
        JPanel actions = new JPanel();
        JButton btnRefresh = new JButton("Rafraîchir");
        btnRefresh.addActionListener(e -> refresh());
        actions.add(btnMaj);
        actions.add(btnSuppr);
        actions.add(btnCreer);
        actions.add(btnRefresh);

        // Agencement final
        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(editPanel, BorderLayout.NORTH);
        bottom.add(createPanel, BorderLayout.CENTER);
        bottom.add(actions, BorderLayout.SOUTH);

        add(bottom, BorderLayout.SOUTH);

        ctrl.addStockChangeListener(this);
        refresh();
    }

    /**
     * Rafraîchit la liste des produits et les champs de saisie.
     */
    private void refresh() {
        model.clear();
        cbProduits.removeAllItems();
        for (Produit p : ctrl.getStock().getProduits()) {
            model.addElement(p);
            cbProduits.addItem(p);
        }
        remplirChampsDepuisProduit();
    }

    /**
     * Remplit les champs de saisie avec les informations du produit sélectionné.
     */
    private void remplirChampsDepuisProduit() {
        Produit p = (Produit) cbProduits.getSelectedItem();
        if (p != null) {
            tfNom.setText(p.getNom());
            tfPrix.setText(String.valueOf(p.getPrixUnitaire()));
            tfQuantite.setText(String.valueOf(p.getQuantiteEnStock()));
        }
    }

    /**
     * Met à jour le produit sélectionné avec les nouvelles informations saisies.
     */
    private void mettreAJourProduit() {
        Produit p = (Produit) cbProduits.getSelectedItem();
        if (p == null) {
            JOptionPane.showMessageDialog(this, "Aucun produit sélectionné.");
            return;
        }

        String nom = tfNom.getText().trim();
        String prixStr = tfPrix.getText().trim();
        String qStr = tfQuantite.getText().trim();

        if (nom.isEmpty() || prixStr.isEmpty() || qStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tous les champs sont obligatoires.");
            return;
        }

        try {
            double prix = Double.parseDouble(prixStr.replace(",", "."));
            int q = Integer.parseInt(qStr);
            if (prix < 0 || q < 0) throw new NumberFormatException();

            p.setNom(nom);
            p.setPrix(prix);
            p.setQuantite(q);

            ctrl.enregistrerStock();
            refresh();
            JOptionPane.showMessageDialog(this, "Produit mis à jour.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Prix ou quantité invalide.");
        }
    }

    /**
     * Supprime le produit sélectionné.
     */
    private void supprimerProduit() {
        Produit p = (Produit) cbProduits.getSelectedItem();
        if (p == null) {
            JOptionPane.showMessageDialog(this, "Aucun produit sélectionné.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
            "Supprimer le produit \"" + p.getNom() + "\" ?", "Confirmation",
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            ctrl.getStock().getProduits().remove(p);
            ctrl.enregistrerStock();
            refresh();
            JOptionPane.showMessageDialog(this, "Produit supprimé.");
        }
    }

    /**
     * Crée un nouveau produit avec les informations saisies.
     */
    private void creerProduit() {
        String nom = tfNewNom.getText().trim();
        String prixStr = tfNewPrix.getText().trim();
        String qStr = tfNewQuantite.getText().trim();

        if (nom.isEmpty() || prixStr.isEmpty() || qStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tous les champs sont obligatoires.");
            return;
        }

        try {
            double prix = Double.parseDouble(prixStr.replace(",", "."));
            int q = Integer.parseInt(qStr);
            if (prix < 0 || q < 0) throw new NumberFormatException();

            // Générer ID unique
            int maxId = ctrl.getStock().getProduits().stream()
                .mapToInt(Produit::getId).max().orElse(0);
            int newId = maxId + 1;

            Produit p = new Produit(newId, nom, prix, q);
            ctrl.getStock().ajouterProduit(p);
            ctrl.enregistrerStock();
            refresh();

            // Réinitialiser les champs
            tfNewNom.setText("");
            tfNewPrix.setText("");
            tfNewQuantite.setText("");

            JOptionPane.showMessageDialog(this, "Produit créé.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Prix ou quantité invalide.");
        }
    }

    /**
     * Notifie que le stock a changé et rafraîchit l'affichage.
     */
    @Override
    public void stockChanged() {
        refresh();
    }
}