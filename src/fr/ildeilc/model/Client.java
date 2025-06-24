package fr.ildeilc.model;

/**
 * Représente un client.
 */
public class Client {
    private final int id;
    private final String nom;
    private final String telephone;

    public Client(int id, String nom, String telephone) {
        this.id = id;
        this.nom = nom;
        this.telephone = telephone;
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getTelephone() { return telephone; }

    @Override
    public String toString() {
        return id + ": " + nom + " (" + telephone + ")";
    }
}
