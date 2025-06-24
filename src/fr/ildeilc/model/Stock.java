package fr.ildeilc.model;

import java.io.*;
import java.util.*;

/**
 * Gère le stock avec persistance CSV.
 */
public class Stock {
    private final List<Produit> produits;

    public Stock() {
        produits = new ArrayList<>();
    }

    public void ajouterProduit(Produit p) {
        produits.add(p);
    }

    public Produit chercherParId(int id) {
        return produits.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void saveCsv(String path) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path))) {
            for (Produit p : produits) pw.println(p.toCsv());
        }
    }

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
