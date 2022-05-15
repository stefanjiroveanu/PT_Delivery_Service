package dao;

import bll.CompositeProduct;
import bll.MenuItem;
import bll.Order;

import java.io.PrintWriter;
import java.util.List;

/**
 * The type File writer.
 */
public class FileWriter {
    /**
     * Write bill.
     *
     * @param orderId the order id
     * @param order   the order
     * @param list    the list
     */
    public void writeBill(int orderId, Order order, List<MenuItem> list) {
        try (PrintWriter printWriter = new PrintWriter("src/main/resources/bill" + orderId + ".txt")) {
            printWriter.println("Order: " + orderId);
            printWriter.println("Placed on the date of: " + order.getDate());
            printWriter.println("Contains the products: ");
            list.forEach(menuItem -> printWriter.println(menuItem.getName()));
            CompositeProduct compositeProduct = new CompositeProduct();
            compositeProduct.getProducts().addAll(list);
            compositeProduct.computePrice();
            printWriter.println("For the total price of: " + compositeProduct.getPrice());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
