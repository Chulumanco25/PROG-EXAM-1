/** Responsible for printing the report. */
public class PrintProduct {

    private final Product product;

    public PrintProduct(Product product) {
        this.product = product;
    }

    public void PrintTotalSales() {
        System.out.println("TOTAL PRODUCT SALES: " + product.GetTotalProductsSold());
    }

    public void PrintAverageSales() {
        System.out.println("AVERAGE PRODUCT SALES: " + (int) product.GetAverageProductSold());
    }

    public void PrintMostPopular() {
        System.out.println("MOST POPULAR PRODUCT: " + product.GetMostPopularProduct()
                + " (" + product.GetMostPopularProductTotal() + ")");
    }

    public void PrintReport(int year) {
        String stars = "*".repeat(40);
        System.out.println("\nPRODUCT SALES REPORT - " + year);
        System.out.println(stars);
        PrintTotalSales();
        PrintAverageSales();
        PrintMostPopular();
    }
}
