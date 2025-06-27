package test;

import fr.ildeilc.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommandeTest {
    @Test
    void testAjoutLigneEtTotal() {
        Client c = new Client(1, "Client A", "0102030405");
        Commande cmd = new Commande(1, c);
        Produit p1 = new Produit(1, "P1", 10.0, 10);
        Produit p2 = new Produit(2, "P2", 20.0, 10);

        cmd.ajouterLigne(p1, 2);
        cmd.ajouterLigne(p2, 1);

        assertEquals(40.0, cmd.getTotal());
        assertEquals(Commande.Status.EN_COURS, cmd.getStatus());
    }

    @Test
    void testStockInsuffisant() {
        Client c = new Client(2, "Client B", "0601020304");
        Commande cmd = new Commande(2, c);
        Produit p = new Produit(1, "StockLimite", 15.0, 1);

        cmd.ajouterLigne(p, 2);

        assertEquals(Commande.Status.EN_ATTENTE_STOCK, cmd.getStatus());
    }
}
