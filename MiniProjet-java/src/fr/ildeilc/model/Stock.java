package fr.ildeilc.model;

import java.io.*;
import java.util.*;

/**
 * Gère le stock avec persistance CSV.
 * Cette classe permet d'ajouter des produits, de les chercher par ID,
 * et de charger ou sauvegarder le stock à partir d'un fichier CSV.
 */
public class Stock {
    private final List<Produit> produits;

    /**
     * Constructeur de la classe Stock.
     * Initialise la liste des produits.
     */
    public Stock() {
        produits = new ArrayList<>();
    }

    /**
     * Ajoute un produit au stock.
     *
     * @param p Le produit à ajouter.
     */
    public void ajouterProduit(Produit p) {
        produits.add(p);
    }

    /**
     * Cherche un produit par son ID.
     *
     * @param id L'ID du produit à chercher.
     * @return Le produit correspondant à l'ID, ou null si non trouvé.
     */
    public Produit chercherParId(int id) {
        return produits.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    /**
     * Retourne la liste des produits dans le stock.
     *
     * @return La liste des produits.
     */
    public List<Produit> getProduits() {
        return produits;
    }

    /**
     * Sauvegarde le stock dans un fichier CSV.
     *
     * @param path Le chemin du fichier CSV.
     * @throws IOException Si une erreur se produit lors de l'écriture dans le fichier.
     */
    public void saveCsv(String path) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path))) {
            for (Produit p : produits) pw.println(p.toCsv());
        }
    }

    /**
     * Charge le stock à partir d'un fichier CSV.
     *
     * @param path Le chemin du fichier CSV.
     * @throws IOException Si une erreur se produit lors de la lecture du fichier.
     */
    public void loadCsv(String path) throws IOException {
        produits.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                produits.add(Produit.fromCsv(line));
            }
        }
    }
}