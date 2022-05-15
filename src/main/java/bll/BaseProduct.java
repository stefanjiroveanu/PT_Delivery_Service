package bll;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Base product.
 */
public class BaseProduct extends MenuItem{

    /**
     * Instantiates a new Base product.
     *
     * @param name             the name
     * @param price            the price
     * @param rating           the rating
     * @param numberOfCalories the number of calories
     * @param proteins         the proteins
     * @param sodium           the sodium
     * @param fats             the fats
     */
    public BaseProduct(String name, float price, int rating, int numberOfCalories, int proteins, int sodium, int fats) {
        super(name, price, rating, sodium, fats, numberOfCalories, proteins);
    }

    /**
     * Instantiates a new Base product.
     *
     * @param name             the name
     * @param rating           the rating
     * @param numberOfCalories the number of calories
     * @param proteins         the proteins
     * @param fats             the fats
     * @param sodium           the sodium
     * @param price            the price
     */
    public BaseProduct(String name,
                       String rating,
                       String numberOfCalories,
                       String proteins,
                       String fats,
                       String sodium,
                       String price){
        super(name,
                Float.parseFloat(price),
                Integer.parseInt(rating),
                Integer.parseInt(sodium),
                Integer.parseInt(fats),
                Integer.parseInt(numberOfCalories),
                Integer.parseInt(proteins));
    }

    /**
     * Read from csv list.
     *
     * @param path the path
     * @return the list
     */
    public static List<BaseProduct> readFromCsv(String path) {
        List<BaseProduct> list = new ArrayList<>();
        try(FileInputStream inputStream = new FileInputStream(path);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            list = bufferedReader
                    .lines()
                    .skip(1)
                    .map(BaseProduct::mapToItem)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Map to item base product.
     *
     * @param s the s
     * @return the base product
     */
    public static BaseProduct mapToItem(String s){
        String[] split = s.split(",");
        assert split.length == 6;
        return new BaseProduct(
                split[0],
                Float.parseFloat(split[1]),
                Integer.parseInt(split[2]),
                Integer.parseInt(split[3]),
                Integer.parseInt(split[4]),
                Integer.parseInt(split[5]),
                Integer.parseInt(split[6]));
    }
}
