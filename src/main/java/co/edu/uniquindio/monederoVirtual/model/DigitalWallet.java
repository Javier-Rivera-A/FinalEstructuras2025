package co.edu.uniquindio.monederoVirtual.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;

/**
 * Modelo que representa el monedero digital principal que administra todas las entidades del sistema
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Sistema de monedero digital que gestiona todas las operaciones")
public class DigitalWallet {

    @Schema(description = "Nombre del monedero digital", example = "QuindiWallet")
    private String name;

    @Schema(description = "Lista de clientes registrados en el sistema")
    private List<Customer> customers = new ArrayList<>();

    @Schema(description = "Lista de transacciones realizadas en el sistema")
    private List<Transaction> transactions = new ArrayList<>();

    @Schema(description = "Lista de notificaciones generadas por el sistema")
    private List<Notification> notifications = new ArrayList<>();

    @Schema(description = "Lista de cuentas registradas en el sistema")
    private List<Account> accounts = new ArrayList<>();

    @Schema(description = "Lista de sistemas de puntos configurados")
    private List<PointsSystem> pointsSystems = new ArrayList<>();

    /**
     * Añade un nuevo cliente al sistema
     * @param customer El cliente a añadir
     */
    public void addCustomer(Customer customer) {
        if (customers == null) {
            customers = new ArrayList<>();
        }
        customers.add(customer);
    }

    /**
     * Registra una nueva transacción en el sistema
     * @param transaction La transacción a registrar
     */
    public void addTransaction(Transaction transaction) {
        if (transactions == null) {
            transactions = new ArrayList<>();
        }
        transactions.add(transaction);
    }

    /**
     * Añade una notificación al sistema
     * @param notification La notificación a añadir
     */
    public void addNotification(Notification notification) {
        if (notifications == null) {
            notifications = new ArrayList<>();
        }
        notifications.add(notification);
    }

    /**
     * Registra una nueva cuenta en el sistema
     * @param account La cuenta a registrar
     */
    public void addAccount(Account account) {
        if (accounts == null) {
            accounts = new ArrayList<>();
        }
        accounts.add(account);
    }

    /**
     * Configura un nuevo sistema de puntos
     * @param pointsSystem El sistema de puntos a configurar
     */
    public void addPointsSystem(PointsSystem pointsSystem) {
        if (pointsSystems == null) {
            pointsSystems = new ArrayList<>();
        }
        pointsSystems.add(pointsSystem);
    }
}