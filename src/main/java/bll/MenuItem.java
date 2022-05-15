package bll;

import java.io.Serializable;
import java.util.Objects;

/**
 * The type Menu item.
 */
public class MenuItem implements Serializable {
    private String name;
    private float price;
    private int rating;
    private int sodium;
    private int fats;
    private int numberOfCalories;
    private int proteins;

    /**
     * Instantiates a new Menu item.
     *
     * @param name             the name
     * @param price            the price
     * @param rating           the rating
     * @param sodium           the sodium
     * @param fats             the fats
     * @param numberOfCalories the number of calories
     * @param proteins         the proteins
     */
    public MenuItem(String name, float price, int rating, int sodium, int fats, int numberOfCalories, int proteins) {
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.sodium = sodium;
        this.fats = fats;
        this.numberOfCalories = numberOfCalories;
        this.proteins = proteins;
    }

    /**
     * Instantiates a new Menu item.
     *
     * @param name     the name
     * @param price    the price
     * @param rating   the rating
     * @param sodium   the sodium
     * @param fats     the fats
     * @param cal      the cal
     * @param proteins the proteins
     */
    public MenuItem(String name, String price, String rating, String sodium, String fats, String cal, String proteins) {
        this.name = name;
        this.price = Float.parseFloat(price);
        this.rating = Integer.parseInt(rating);
        this.sodium = Integer.parseInt(sodium);
        this.fats = Integer.parseInt(fats);
        this.numberOfCalories = Integer.parseInt(cal);
        this.proteins = Integer.parseInt(proteins);
    }

    /**
     * Instantiates a new Menu item.
     */
    public MenuItem() {

    }

    /**
     * Gets sodium.
     *
     * @return the sodium
     */
    public int getSodium() {
        return sodium;
    }

    /**
     * Sets sodium.
     *
     * @param sodium the sodium
     */
    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    /**
     * Gets fats.
     *
     * @return the fats
     */
    public int getFats() {
        return fats;
    }

    /**
     * Sets fats.
     *
     * @param fats the fats
     */
    public void setFats(int fats) {
        this.fats = fats;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                ", numberOfCalories=" + numberOfCalories +
                ", proteins=" + proteins +
                '}';
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Gets rating.
     *
     * @return the rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * Sets rating.
     *
     * @param rating the rating
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Gets number of calories.
     *
     * @return the number of calories
     */
    public int getNumberOfCalories() {
        return numberOfCalories;
    }

    /**
     * Sets number of calories.
     *
     * @param numberOfCalories the number of calories
     */
    public void setNumberOfCalories(int numberOfCalories) {
        this.numberOfCalories = numberOfCalories;
    }

    /**
     * Gets proteins.
     *
     * @return the proteins
     */
    public int getProteins() {
        return proteins;
    }

    /**
     * Sets proteins.
     *
     * @param proteins the proteins
     */
    public void setProteins(int proteins) {
        this.proteins = proteins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        MenuItem menuItem = (MenuItem) o;
        return Objects.equals(name, menuItem.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, rating, sodium, fats, numberOfCalories, proteins);
    }
}
