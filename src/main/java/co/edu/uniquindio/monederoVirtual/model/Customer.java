package co.edu.uniquindio.monederoVirtual.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;

/**
 * Modelo que representa la información de un cliente del sistema
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Información del cliente del sistema")
public class Customer {

    @Schema(description = "Nombre completo del cliente", example = "Juan Pérez")
    private String name;

    @Schema(description = "Identificador único del cliente", example = "C12345")
    private String id;

    @Schema(description = "Correo electrónico del cliente", example = "juan.perez@example.com")
    private String email;

    @Schema(description = "Número de teléfono del cliente", example = "+34123456789")
    private String phone;

    @Schema(description = "Rango actual del cliente")
    private Rank rank;

    @Schema(description = "Puntos acumulados del cliente", example = "1500")
    private int totalPoints;

    @Schema(description = "Lista de wallets asociados al cliente")
    private List<Wallet> wallets = new ArrayList<>();

    @Schema(description = "Lista de cuentas bancarias asociadas al cliente")
    private List<Account> accounts = new ArrayList<>();

    @Schema(description = "Lista de transacciones realizadas por el cliente")
    private List<Transaction> transactions = new ArrayList<>();

    /**
     * Actualiza el rango del cliente basado en sus puntos actuales
     */
    public void updateRank() {
        this.rank = Rank.getRankByPoints(totalPoints);
    }

    /**
     * Añade puntos al total del cliente y actualiza su rango
     * @param points Los puntos a añadir
     */
    public void addPoints(int points) {
        this.totalPoints += points;
        updateRank();
    }

    /**
     * Añade una billetera a la lista de billeteras del cliente
     * @param wallet La billetera a añadir
     */
    public void addWallet(Wallet wallet) {
        if (wallets == null) {
            wallets = new ArrayList<>();
        }
        wallets.add(wallet);
    }

    /**
     * Añade una cuenta a la lista de cuentas del cliente
     * @param account La cuenta a añadir
     */
    public void addAccount(Account account) {
        if (accounts == null) {
            accounts = new ArrayList<>();
        }
        accounts.add(account);
    }

    /**
     * Añade una transacción al historial del cliente
     * @param transaction La transacción a añadir
     */
    public void addTransaction(Transaction transaction) {
        if (transactions == null) {
            transactions = new ArrayList<>();
        }
        transactions.add(transaction);
    }

    /**
     * Calcula los puntos necesarios para llegar al siguiente rango
     * @return Puntos necesarios para el siguiente rango
     */
    public int pointsToNextRank() {
        return rank.pointsToNextRank(totalPoints);
    }
}