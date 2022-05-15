package controllers;

import bll.DeliveryService;
import bll.MenuItem;
import bll.Order;
import dao.FileWriter;
import dao.Serializator;
import presentation.AdminInterface;
import presentation.ClientInterface;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The type Client controller.
 */
public class ClientController {
    /**
     * The constant PATH.
     */
    public static final String PATH = "src/main/resources/deliveryService.ser";

    /**
     * Instantiates a new Client controller.
     *
     * @param deliveryService     the delivery service
     * @param clientInterface     the client interface
     * @param serviceSerializator the service serializator
     */
    public ClientController(DeliveryService deliveryService, ClientInterface clientInterface,
                            Serializator<DeliveryService> serviceSerializator) {
        clientInterface.viewButtonActionListener(e -> {
            System.out.println("1111");
            deliveryService.getBasicProducts().forEach(menuItem -> clientInterface.getTableModel()
                    .addRow(ControllerCommonMethods.getObjects(menuItem)));
        });
        JTable table = clientInterface.getTable();
        clientInterface.searchButtonActionListener(e -> {
            System.out.println("2222");
            String[] fields = new String[7];
            fields[4] = clientInterface.getFatsField().getText();
            fields[6] = clientInterface.getPriceField().getText();
            fields[3] = clientInterface.getProteinField().getText();
            fields[1] = clientInterface.getRatingField().getText();
            fields[5] = clientInterface.getSodiumField().getText();
            fields[0] = clientInterface.getTitleField().getText();
            fields[2] = clientInterface.getNumberOfCaloriesField().getText();
            List<MenuItem> collect = deliveryService.getBasicProducts()
                    .stream()
                    .filter(menuItem -> fields[0].equals("") || menuItem.getName().equals(fields[0]))
                    .peek(System.out::println)
                    .filter(menuItem -> fields[1].equals("") || menuItem.getRating() == Integer.parseInt(fields[1]))
                    .peek(System.out::println)
                    .filter(menuItem -> fields[2].equals("") || menuItem.getNumberOfCalories() == Integer.parseInt(fields[2]))
                    .peek(System.out::println)
                    .filter(menuItem -> fields[3].equals("") || menuItem.getProteins() == Integer.parseInt(fields[3]))
                    .peek(System.out::println)
                    .filter(menuItem -> fields[4].equals("") || menuItem.getFats() == Integer.parseInt(fields[4]))
                    .peek(System.out::println)
                    .filter(menuItem -> fields[5].equals("") || menuItem.getSodium() == Integer.parseInt(fields[5]))
                    .peek(System.out::println)
                    .filter(menuItem -> fields[6].equals("") || menuItem.getPrice() == Float.parseFloat(fields[6]))
                    .peek(System.out::println)
                    .collect(Collectors.toList());
            HashSet<MenuItem> hashSet = new HashSet<>(collect);
            System.out.println(hashSet);
            deleteAllRows(clientInterface, table);
            hashSet.forEach(menuItem -> clientInterface.getTableModel()
                    .addRow(ControllerCommonMethods.getObjects(menuItem)));
            serviceSerializator.write(PATH, deliveryService);
        });
        clientInterface.placeOrderButtonActionListener(e ->{
            int[] selectedRows = table.getSelectedRows();
            List<MenuItem> menuItems = new ArrayList<>();
            for(int i = 0; i < selectedRows.length; i++)
            {
                MenuItem menuItem = new MenuItem(
                        table.getValueAt(i, 0).toString(),
                        table.getValueAt(i, 6).toString(),
                        table.getValueAt(i, 1).toString(),
                        table.getValueAt(i, 5).toString(),
                        table.getValueAt(i, 4).toString(),
                        table.getValueAt(i, 2).toString(),
                        table.getValueAt(i, 3).toString()
                );
                menuItems.add(menuItem);
            }
            String time = clientInterface.getTimeField().getText();
            Order order;
            if(time.equals("")) {
                order = new Order(LocalDateTime.now().toString(), DeliveryService.getCurrentUser().getUsername());
            } else {
                order = new Order(time, DeliveryService.getCurrentUser().getUsername());
            }
            deliveryService.createNewOrder(order, menuItems);
            System.out.println(order);
            System.out.println(menuItems);
            FileWriter fileWriter = new FileWriter();
            fileWriter.writeBill(order.getOrderId(),  order, menuItems);
            serviceSerializator.write(PATH, deliveryService);
        });
    }
    private void deleteAllRows(ClientInterface clientInterface, JTable table) {
        for (int i = table.getRowCount() - 1; i >= 0; i--) {
            clientInterface.getTableModel().removeRow(i);
        }
    }
}

