package bll;

import java.io.Serializable;
import java.util.Objects;

/**
 * The type Order.
 */
public class Order implements Serializable {
    private static int id = 0;
    private int orderId;
    private String date;
    private String clientUsername;

    /**
     * Instantiates a new Order.
     *
     * @param date           the date
     * @param clientUsername the client username
     */
    public Order(String date, String clientUsername) {
        id++;
        orderId = id;
        this.date = date;
        this.clientUsername = clientUsername;
    }

    /**
     * Gets order id.
     *
     * @return the order id
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * Sets order id.
     *
     * @param orderId the order id
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId && Objects.equals(date, order.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, date);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", date='" + date + '\'' +
                ", clientUsername='" + clientUsername + '\'' +
                '}';
    }

    /**
     * Gets client username.
     *
     * @return the client username
     */
    public String getClientUsername() {
        return clientUsername;
    }
}
