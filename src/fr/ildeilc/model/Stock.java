package fr.ildeilc.model;

import java.io.*;
import java.util.*;

/**
 * Gère le stock de produits avec persistance CSV.
 */
public class Stock {
    /** Liste des produits en stock */
    private final List<Produit> produits;

    /**
     * Construit un stock vide.
     */
    public Stock() {
        produits = new ArrayList<>();
    }

    /**
     * Ajoute un produit au stock.
     * @param p produit à ajouter
     */
    public void ajouterProduit(Produit p) {
        produits.add(p);
    }

    /**
     * Recherche un produit par son identifiant.
     * @param id identifiant du produit
     * @return produit trouvé ou null
     */
    public Produit chercherParId(int id) {
        return produits.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    /**
     * Retourne la liste des produits en stock.
     * @return liste des produits
     */
    public List<Produit> getProduits() {
        return produits;
    }

    /**
     * Sauvegarde le stock dans un fichier CSV.
     * @param path chemin du fichier
     * @throws IOException en cas d'erreur d'écriture
     */
    public void saveCsv(String path) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path))) {
            for (Produit p : produits) pw.println(p.toCsv());
        }
    }

    /**
     * Charge le stock depuis un fichier CSV.
     * @param path chemin du fichier
     * @throws IOException en cas d'erreur de lecture
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
