package bll;

import java.util.HashSet;

/**
 * The type Composite product.
 */
public class CompositeProduct extends MenuItem{
    private HashSet<MenuItem> products = new HashSet<>();
    private static int numberOfTimesOrdered = 1;

    /**
     * Instantiates a new Composite product.
     *
     * @param name     the name
     * @param price    the price
     * @param products the products
     */
    public CompositeProduct(String name, float price, HashSet<MenuItem> products) {
        super(name, price, 0, 0, 0, 0, 0);
        this.products = products;
    }

    /**
     * Instantiates a new Composite product.
     */
    public CompositeProduct(){
        super();
    }

    /**
     * Compute price.
     */
    public void computePrice(){
        int sum = 0;
        for (MenuItem product : products) {
            sum += product.getPrice();
        }
        super.setPrice(sum);
    }

    /**
     * Compute.
     */
    public void compute(){
        for (MenuItem product : products) {
            super.setFats(super.getFats() + product.getFats());
            super.setProteins(super.getProteins() + product.getProteins());
            super.setNumberOfCalories(super.getNumberOfCalories() + product.getNumberOfCalories());
            super.setSodium(super.getSodium() + product.getSodium());
            super.setRating(super.getRating() + product.getRating());
        }
        super.setRating(super.getRating() / products.size());
    }

    /**
     * Gets products.
     *
     * @return the products
     */
    public HashSet<MenuItem> getProducts() {
        return products;
    }

    /**
     * Sets products.
     *
     * @param products the products
     */
    public void setProducts(HashSet<MenuItem> products) {
        this.products = products;
    }
}
