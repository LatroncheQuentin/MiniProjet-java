package test;

import fr.ildeilc.model.Produit;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProduitTest {
    @Test
    void testAjoutEtRetraitStock() {
        Produit p = new Produit(1, "Test", 10.0, 5);
        p.ajouterStock(3);
        assertEquals(8, p.getQuantiteEnStock());

        p.retirerStock(2);
        assertEquals(6, p.getQuantiteEnStock());

        assertThrows(IllegalStateException.class, () -> p.retirerStock(10));
    }

    @Test
    void testSetters() {
        Produit p = new Produit(1, "Test", 10.0, 5);
        p.setNom("Modifié");
        p.setPrix(20.0);
        p.setQuantite(12);
        assertEquals("Modifié", p.getNom());
        assertEquals(20.0, p.getPrixUnitaire());
        assertEquals(12, p.getQuantiteEnStock());
    }
}
