package test;

import fr.ildeilc.model.Produit;
import fr.ildeilc.model.Stock;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StockTest {
    @Test
    void testAjoutEtRechercheProduit() {
        Stock stock = new Stock();
        Produit p = new Produit(1, "Test", 100.0, 10);
        stock.ajouterProduit(p);

        assertEquals(p, stock.chercherParId(1));
        assertNull(stock.chercherParId(2));
    }
}
