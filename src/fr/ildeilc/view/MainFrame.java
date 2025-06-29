package fr.ildeilc.view;

import fr.ildeilc.controller.AppController;
import fr.ildeilc.view.panels.StockPanel;
import fr.ildeilc.view.panels.CommandePanel;
import fr.ildeilc.view.panels.LivraisonPanel;
import fr.ildeilc.view.panels.FacturePanel;

import javax.swing.*;
import java.awt.*;

/**
 * Fenêtre principale de l'application.
 */
public class MainFrame extends JFrame {
    /**
     * Construit la fenêtre principale avec les onglets.
     * @param ctrl contrôleur principal
     */
    public MainFrame(AppController ctrl) {
        super("Gestion de stock ILDEILC");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Stock",     new StockPanel(ctrl));
        tabs.addTab("Commande",  new CommandePanel(ctrl));
        tabs.addTab("Livraison", new LivraisonPanel(ctrl));
        tabs.addTab("Facture",   new FacturePanel(ctrl));

        add(tabs, BorderLayout.CENTER);
    }
}
