package controllers;

import bll.BaseProduct;
import bll.CompositeProduct;
import bll.DeliveryService;
import bll.MenuItem;
import dao.Serializator;
import presentation.AdminInterface;
import presentation.GenerateReportInterface;

import javax.swing.*;
import java.util.HashSet;

/**
 * The type Admin controller.
 */
public class AdminController {
    /**
     * The constant PATH.
     */
    public static final String PATH = "src/main/resources/deliveryService.ser";
    /**
     * The Generate report interface.
     */
    public GenerateReportInterface generateReportInterface = new GenerateReportInterface();

    /**
     * Instantiates a new Admin controller.
     *
     * @param deliveryService     the delivery service
     * @param adminInterface      the admin interface
     * @param serviceSerializator the service serializator
     */
    public AdminController(DeliveryService deliveryService, AdminInterface adminInterface,
                           Serializator<DeliveryService> serviceSerializator) {
        adminInterface.importButtonActionListener(e -> {
            deleteAllRows(adminInterface, adminInterface.getTable());
            deliveryService.importAllItems().forEach(baseProduct -> {
                Object[] products = ControllerCommonMethods.getObjects(baseProduct);
                adminInterface.getTableModel().addRow(products);
            });
            serviceSerializator.write(PATH, deliveryService);
        });
        adminInterface.addButtonActionListener(e -> {
            deleteAllRows(adminInterface, adminInterface.getTable());
            deliveryService.importProduct(new MenuItem(
                    adminInterface.getTitleField().getText(),
                    adminInterface.getRatingField().getText(),
                    adminInterface.getNumberOfCaloriesField().getText(),
                    adminInterface.getProteinField().getText(),
                    adminInterface.getFatsField().getText(),
                    adminInterface.getSodiumField().getText(),
                    adminInterface.getPriceField().getText()
            )).forEach(baseProduct -> adminInterface.getTableModel()
                    .addRow(ControllerCommonMethods.getObjects(baseProduct)));
            serviceSerializator.write(PATH, deliveryService);
        });
        adminInterface.deleteButtonActionListener(e -> {
            JTable table = adminInterface.getTable();
            BaseProduct valueAt = new BaseProduct(
                    table.getValueAt(table.getSelectedRow(), 0).toString(),
                    table.getValueAt(table.getSelectedRow(), 1).toString(),
                    table.getValueAt(table.getSelectedRow(), 2).toString(),
                    table.getValueAt(table.getSelectedRow(), 3).toString(),
                    table.getValueAt(table.getSelectedRow(), 4).toString(),
                    table.getValueAt(table.getSelectedRow(), 5).toString(),
                    table.getValueAt(table.getSelectedRow(), 6).toString()
            );
            System.out.println(valueAt);
            deleteAllRows(adminInterface, table);
            deliveryService
                    .deleteProduct(valueAt)
                    .forEach(baseProduct -> adminInterface.getTableModel()
                            .addRow(ControllerCommonMethods.getObjects(baseProduct)));
            serviceSerializator.write(PATH, deliveryService);
        });

        adminInterface.updateButtonActionListener(e ->
        {
            JTable table = adminInterface.getTable();
            BaseProduct valueAt = new BaseProduct(
                    table.getValueAt(table.getSelectedRow(), 0).toString(),
                    table.getValueAt(table.getSelectedRow(), 1).toString(),
                    table.getValueAt(table.getSelectedRow(), 2).toString(),
                    table.getValueAt(table.getSelectedRow(), 3).toString(),
                    table.getValueAt(table.getSelectedRow(), 4).toString(),
                    table.getValueAt(table.getSelectedRow(), 5).toString(),
                    table.getValueAt(table.getSelectedRow(), 6).toString()
            );
            System.out.println(valueAt);
            BaseProduct updatedProduct = new BaseProduct(
                    adminInterface.getTitleField().getText(),
                    adminInterface.getRatingField().getText(),
                    adminInterface.getNumberOfCaloriesField().getText(),
                    adminInterface.getProteinField().getText(),
                    adminInterface.getFatsField().getText(),
                    adminInterface.getSodiumField().getText(),
                    adminInterface.getPriceField().getText());
            System.out.println(updatedProduct);
            HashSet<MenuItem> updatedProducts = deliveryService.updateProduct(valueAt, updatedProduct);
            deleteAllRows(adminInterface, table);
            updatedProducts.forEach(
                    baseProduct -> adminInterface.getTableModel()
                            .addRow(ControllerCommonMethods.getObjects(baseProduct))
            );
            serviceSerializator.write(PATH, deliveryService);
        });
        adminInterface.composeButtonActionListener(e -> {
            CompositeProduct compositeProduct = new CompositeProduct();
            JTable table = adminInterface.getTable();
            int[] selectedRows = table.getSelectedRows();
            for (int i = 0; i < selectedRows.length; i++) {
                MenuItem valueAt = new BaseProduct(
                        table.getValueAt(i, 0).toString(),
                        table.getValueAt(i, 1).toString(),
                        table.getValueAt(i, 2).toString(),
                        table.getValueAt(i, 3).toString(),
                        table.getValueAt(i, 4).toString(),
                        table.getValueAt(i, 5).toString(),
                        table.getValueAt(i, 6).toString()
                );
                compositeProduct.getProducts().add(valueAt);
            }
            compositeProduct.computePrice();
            compositeProduct.compute();
            compositeProduct.setName("menu" + Math.random());
            System.out.println(compositeProduct);
            deleteAllRows(adminInterface, adminInterface.getTable());
            deliveryService.importProduct(compositeProduct).forEach(
                    menuItem -> adminInterface.getTableModel().addRow(ControllerCommonMethods.getObjects(menuItem)));
            serviceSerializator.write(PATH, deliveryService);
        });
        adminInterface.generateReportButtonActionListener(e -> {
            generateReportInterface.getFrame().setVisible(true);
        });
        generateReportInterface.reportOne(e -> {
            String start = generateReportInterface.getStartHour().getText();
            String end = generateReportInterface.getEndHour().getText();
            deliveryService.generateReportOne(start, end);
        });
        generateReportInterface.reportTwo(e -> {
            int noOfTimes = Integer.parseInt(generateReportInterface.getReportTwoField().getText());
            deliveryService.generateReportTwo(noOfTimes);
        });
        generateReportInterface.reportThree(e -> {
            deliveryService.generateReportThree(Integer.parseInt(generateReportInterface.getR3TimesOrdered().getText()),
                    Float.parseFloat(generateReportInterface.getR3TimesOrdered().getText()));
        });
        generateReportInterface.reportFour(e ->{
            deliveryService.generateReportFour(Integer.parseInt(generateReportInterface.getDayField().getText()));
        });
    }

    private void deleteAllRows(AdminInterface adminInterface, JTable table) {
        for (int i = table.getRowCount() - 1; i >= 0; i--) {
            adminInterface.getTableModel().removeRow(i);
        }
    }


}
