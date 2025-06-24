package fr.ildeilc.view;

import fr.ildeilc.controller.AppController;
import fr.ildeilc.view.panels.StockPanel;
import fr.ildeilc.view.panels.CommandePanel;
import fr.ildeilc.view.panels.LivraisonPanel;
import fr.ildeilc.view.panels.FacturePanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
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
