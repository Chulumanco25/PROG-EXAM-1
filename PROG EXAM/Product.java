/**
 * Holds product names (single-dimensional array) and monthly sales
 * (two-dimensional array: rows = products, columns = months).
 */
public class Product implements IProduct {

    private final String[] names;
    private final int[][] sales;

    public Product(String[] names, int[][] sales) {
        this.names = names;
        this.sales = sales;
    }

    /** Total units sold for one product across all months. */
    public int GetTotalForProduct(int productIndex) {
        int total = 0;
        for (int month = 0; month < sales[productIndex].length; month++) {
            total += sales[productIndex][month];
        }
        return total;
    }

    @Override
    public int GetTotalProductsSold() {
        int total = 0;
        for (int p = 0; p < sales.length; p++) {
            total += GetTotalForProduct(p);
        }
        return total;
    }

    /** Average number of products sold per product per month. */
    @Override
    public double GetAverageProductSold() {
        int entries = 0;
        for (int[] row : sales) {
            entries += row.length;
        }
        return entries == 0 ? 0 : (double) GetTotalProductsSold() / entries;
    }

    @Override
    public String GetMostPopularProduct() {
        int best = 0;
        for (int p = 1; p < sales.length; p++) {
            if (GetTotalForProduct(p) > GetTotalForProduct(best)) {
                best = p;
            }
        }
        return names[best];
    }

    public int GetMostPopularProductTotal() {
        for (int p = 0; p < names.length; p++) {
            if (names[p].equals(GetMostPopularProduct())) {
               return GetTotalForProduct(p);
            }
        }
        return 0;
    }
}
