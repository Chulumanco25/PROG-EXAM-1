import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

/** Unit tests (place in your test package). */
class ProductTest {

    private final String[] names = {"Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"};
    private final int[][] sales = {{25, 18, 20}, {15, 10, 12}, {18, 15, 20}};

    @Test
    void GetTotalProductSales_WithValidData_ReturnsCorrectValue() {
        Product product = new Product(names, sales);
        assertEquals(153, product.GetTotalProductsSold());
    }

    @Test
    void GetAverageSales_WithValidData_ReturnsCorrectValue() {
        Product product = new Product(names, sales);
        assertEquals(17.0, product.GetAverageProductSold(), 0.001);
    }
}
