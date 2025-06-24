package fr.ildeilc.model;

/**
 * Représente un client.
 */
public class Client {
    /** Identifiant du client */
    private final int id;
    /** Nom du client */
    private final String nom;
    /** Téléphone du client */
    private final String telephone;

    /**
     * Construit un client.
     * @param id identifiant
     * @param nom nom
     * @param telephone téléphone
     */
    public Client(int id, String nom, String telephone) {
        this.id = id;
        this.nom = nom;
        this.telephone = telephone;
    }

    /**
     * Retourne l'identifiant du client.
     * @return id
     */
    public int getId() { return id; }

    /**
     * Retourne le nom du client.
     * @return nom
     */
    public String getNom() { return nom; }

    /**
     * Retourne le téléphone du client.
     * @return téléphone
     */
    public String getTelephone() { return telephone; }

    /**
     * Retourne une représentation textuelle du client.
     * @return chaîne descriptive
     */
    @Override
    public String toString() {
        return id + ": " + nom + " (" + telephone + ")";
    }
}
