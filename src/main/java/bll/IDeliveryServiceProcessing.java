package bll;

import model.User;

import java.util.HashSet;
import java.util.List;

/**
 * The interface Delivery service processing.
 */
public interface IDeliveryServiceProcessing {
    /**
     * Adds a new menu item
     *
     * @param baseProduct the base product
     * @return the hash set
     * @pre baseProduct !=null
     */
    HashSet<MenuItem> importProduct(MenuItem baseProduct);

    /**
     * Deletes an existent menu item
     *
     * @param baseProduct the base product
     * @return the hash set
     * @pre baseProduct !=null
     * @post menu.size = menu.size@pre -1
     */
    HashSet<MenuItem> deleteProduct(MenuItem baseProduct);

    /**
     * Edits an existent menu item
     *
     * @param toBeUpdated the to be updated
     * @param baseProduct the base product
     * @return the hash set
     * @pre baseProduct !=null
     */
    HashSet<MenuItem> updateProduct(MenuItem toBeUpdated, MenuItem baseProduct);

    /**
     * Imports the initial set of products
     *
     * @return the hash set
     * @post menu.size !=0;
     */
    HashSet<MenuItem> importAllItems();

    /**
     * Generates a report ordered between a given start and end hour
     *
     * @param start the start
     * @param end   the end
     * @return
     * @pre start >=0 && start<=23
     * @pre end >=0 && end<=23
     * @pre start < end
     */
    void generateReportOne(String start, String end);

    /**
     * Generates a report with products which have beed ordered more than a given number of times
     *
     * @param numberOfTimes the number of times
     * @return
     * @pre numberOfTimes >=0
     */
    void generateReportTwo(int numberOfTimes);

    /**
     * Generates a report with clients details who ordered more than a given times and a given value
     *
     * @param noOfTimes the no of times
     * @param price     the price
     * @return
     * @pre times >=0
     * @pre value >=0
     */
    void generateReportThree(int noOfTimes, float price);

    /**
     * Generates a report with products ordered in a given day
     *
     * @param day the day
     * @return
     * @pre day >=-1 && day<32
     */
    void generateReportFour(int day);

    /**
     * Creates a new order order
     *
     * @param order the order
     * @param items the items
     * @pre order !=null
     * @pre items !=null
     */
    void createNewOrder(Order order, List<MenuItem> items);
}
