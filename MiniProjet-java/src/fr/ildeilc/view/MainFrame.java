// filepath: /Users/quentin/Documents/limayrac/BSI/java/MiniProjet-java/src/fr/ildeilc/view/MainFrame.java
package fr.ildeilc.view;

import fr.ildeilc.controller.AppController;
import fr.ildeilc.view.panels.StockPanel;
import fr.ildeilc.view.panels.CommandePanel;
import fr.ildeilc.view.panels.LivraisonPanel;
import fr.ildeilc.view.panels.FacturePanel;

import javax.swing.*;
import java.awt.*;

/**
 * Représente la fenêtre principale de l'application.
 * Cette classe crée l'interface utilisateur et gère les différents panneaux de l'application.
 */
public class MainFrame extends JFrame {
    
    /**
     * Constructeur de la classe MainFrame.
     * Initialise la fenêtre principale avec les panneaux de l'application.
     *
     * @param ctrl Le contrôleur principal de l'application.
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