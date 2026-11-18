import java.util.Scanner;

/** Console application (main class). */

public class SalesReport {
    public static void main(String[] args) {
        String[] names = {"Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"};
        int months = 3;
        int[][] sales = new int[names.length][months];

        Scanner in = new Scanner(System.in);
        for (int p = 0; p < names.length; p++) {
            for (int m = 0; m < months; m++) {
                System.out.print("Enter number of sales for month " + (m + 1)
                        + " for " + names[p] + " mattress: ");
                sales[p][m] = in.nextInt();
            }
        }

        Product product = new Product(names, sales);
        new PrintProduct(product).PrintReport(2026);
    }
}
