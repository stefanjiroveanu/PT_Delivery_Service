package bll;


import model.User;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The type Delivery service.
 */
public class DeliveryService extends Observable implements IDeliveryServiceProcessing, Serializable {
    private static User currentUser;
    private HashSet<MenuItem> basicProducts = new HashSet<>();
    private HashMap<Order, List<MenuItem>> hashMap = new HashMap<>();

    /**
     * Current user.
     *
     * @param user the user
     */
    public static void currentUser(User user) {
        currentUser = user;
    }

    /**
     * Gets current user.
     *
     * @return the current user
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    /**
     * Gets basic products.
     *
     * @return the basic products
     */
    public HashSet<MenuItem> getBasicProducts() {
        return basicProducts;
    }

    /**
     * Is well formed boolean.
     *
     * @return the boolean
     */
    public boolean isWellFormed() {
        assert !basicProducts.contains(null);
        return !basicProducts.contains(null);
    }


    @Override
    public HashSet<MenuItem> importProduct(MenuItem baseProduct) {
        assert baseProduct != null;
        boolean add = basicProducts.add(baseProduct);
        if (!add) {
            throw new RuntimeException("Cannot Add");
        }
        List<MenuItem> sortedList = new ArrayList<>(basicProducts);
        sortedList.sort(Comparator.comparing(MenuItem::getName));
        basicProducts = new HashSet<>();
        basicProducts.addAll(sortedList);
        return basicProducts;
    }

    @Override
    public HashSet<MenuItem> deleteProduct(MenuItem baseProduct) {
        assert baseProduct != null;
        basicProducts.remove(baseProduct);
        return basicProducts;
    }

    @Override
    public HashSet<MenuItem> updateProduct(MenuItem toBeUpdated, MenuItem updatedObject) {
        assert toBeUpdated != null;
        assert updatedObject != null;
        for (MenuItem baseProduct : basicProducts) {
            if (baseProduct.equals(toBeUpdated)) {
                baseProduct.setFats(updatedObject.getFats());
                baseProduct.setPrice(updatedObject.getPrice());
                baseProduct.setName(updatedObject.getName());
                baseProduct.setProteins(updatedObject.getProteins());
                baseProduct.setSodium(updatedObject.getSodium());
                baseProduct.setNumberOfCalories(updatedObject.getNumberOfCalories());
                baseProduct.setRating(updatedObject.getRating());
                break;
            }
        }
        return basicProducts;
    }

    @Override
    public void createNewOrder(Order order, List<MenuItem> menuItems) {
        assert order != null;
        assert isNotNull(menuItems);
        hashMap.put(order, menuItems);
        setChanged();
        notifyObservers(menuItems);
    }

    @Override
    public HashSet<MenuItem> importAllItems() {
        System.out.println("AAAAAAAAAAAAAAAAAAAA");
        List<BaseProduct> products = BaseProduct.readFromCsv("src/main/resources/products.csv");
        basicProducts.addAll(products);
        return basicProducts;
    }

    @Override
    public void generateReportOne(String start, String end) {
        assert isNotNull(start);
        assert isNotNull(end);
        try (PrintWriter printWriter = new PrintWriter("src/main/resources/report1.txt")) {
            List<Order> orders = new ArrayList<>(hashMap.keySet());
            printWriter.println("Orders performed between " + start + "  " + end);
            System.out.println(LocalDateTime.MIN.plusHours(Integer.parseInt(start)).getHour());
            System.out.println(LocalDateTime.MIN.plusHours(Integer.parseInt(end)).getHour());
            System.out.println(LocalDateTime.parse(orders.get(0).getDate()).getHour());
            orders = orders
                    .stream()
                    .filter(((order ->
                            LocalDateTime.parse(order.getDate()).getHour() > LocalDateTime.MIN.plusHours(Integer.parseInt(start)).getHour())))
                    .filter(order ->
                            LocalDateTime.parse(order.getDate()).getHour() < LocalDateTime.MIN.plusHours(Integer.parseInt(end)).getHour())
                    .collect(Collectors.toList());
            orders.forEach(System.out::println);
            orders.forEach(printWriter::println);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void generateReportTwo(int numberOfTimes) {
        try (PrintWriter printWriter = new PrintWriter("src/main/resources/report2.txt")) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("The products are: ");
            for (MenuItem menuItem : basicProducts) {
                long count = hashMap.entrySet()
                        .stream()
                        .filter(p -> p.getValue().contains(menuItem))
                        .count();
                System.out.println(count);
                if (count > numberOfTimes) {
                    stringBuilder.append(menuItem).append("\n");
                }
            }
            System.out.println(stringBuilder);
            printWriter.println(stringBuilder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void generateReportThree(int noOfTimes, float price) {
        try (PrintWriter printWriter = new PrintWriter("src/main/resources/report3.txt")) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("The clients who ordered more than ")
                    .append(noOfTimes)
                    .append(" and the price of their order is greater than ")
                    .append(price)
                    .append(" are : ");
            List<String> users = hashMap.keySet()
                    .stream()
                    .map(Order::getClientUsername)
                    .distinct()
                    .collect(Collectors.toList());
            users.forEach(user -> {
                long count = hashMap.keySet()
                        .stream()
                        .filter(p -> p.getClientUsername().equals(user))
                        .count();
                CompositeProduct compositeProduct = new CompositeProduct();
                if (count > noOfTimes) {
                    hashMap.values().forEach(list -> {
                        compositeProduct.getProducts().addAll(list);
                        compositeProduct.computePrice();
                    });
                }
                if (compositeProduct.getPrice() > price) {
                    stringBuilder.append(user).append("\n");
                }
            });
            printWriter.println(stringBuilder);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void generateReportFour(int day) {
        try (PrintWriter printWriter = new PrintWriter("src/main/resources/report4")) {
            StringBuilder stringBuilder = new StringBuilder();
            List<MenuItem> products = hashMap.entrySet()
                    .stream()
                    .filter(p -> LocalDateTime.parse(p.getKey().getDate()).getDayOfMonth() == day)
                    .flatMap(p -> p.getValue().stream())
                    .collect(Collectors.toList());
                Set<MenuItem> freq = new HashSet<>(products);
                freq.forEach(menuItem ->
                        stringBuilder.append(menuItem.getName()
                                + " ordered " + Collections.frequency(products, menuItem) + " times\n"));

            printWriter.println(stringBuilder);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets hash map.
     *
     * @return the hash map
     */
    public HashMap<Order, List<MenuItem>> getHashMap() {
        return hashMap;
    }

    /**
     * Is not null boolean.
     *
     * @param object the object
     * @return the boolean
     */
    public boolean isNotNull(Object object) {
        if (object != null) {
            return true;
        }
        return false;
    }
}
