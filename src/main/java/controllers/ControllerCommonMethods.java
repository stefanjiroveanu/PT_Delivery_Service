package controllers;

import bll.MenuItem;

/**
 * The type Controller common methods.
 */
public class ControllerCommonMethods {
    /**
     * Get objects object [ ].
     *
     * @param baseProduct the base product
     * @return the object [ ]
     */
    public static Object[] getObjects(MenuItem baseProduct) {
        Object[] products = new Object[7];
        products[0] = baseProduct.getName();
        products[1] = baseProduct.getRating();
        products[2] = baseProduct.getNumberOfCalories();
        products[3] = baseProduct.getProteins();
        products[4] = baseProduct.getFats();
        products[5] = baseProduct.getSodium();
        products[6] = baseProduct.getPrice();
        return products;
    }
}
