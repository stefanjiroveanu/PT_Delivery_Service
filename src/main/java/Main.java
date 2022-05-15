import bll.DeliveryService;
import controllers.AdminController;
import controllers.ClientController;
import dao.Serializator;
import presentation.LoginPage;

import java.util.Objects;

/**
 * The type Main.
 */
public class Main {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        LoginPage loginPage = new LoginPage();
        loginPage.getFrame().setVisible(true);
        Serializator<DeliveryService> serviceSerializator = new Serializator<>();
        DeliveryService service = serviceSerializator.readOneObject("src/main/resources/deliveryService.ser");
        DeliveryService deliveryService;
        deliveryService = Objects.requireNonNullElseGet(service, DeliveryService::new);
        deliveryService.addObserver(loginPage.getEmployeeInterface());
        AdminController adminController = new AdminController(deliveryService, loginPage.getAdminInterface(), serviceSerializator);
        ClientController clientController = new ClientController(deliveryService, loginPage.getClientInterface(), serviceSerializator);
    }
}
