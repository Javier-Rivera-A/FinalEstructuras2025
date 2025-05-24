package co.edu.uniquindio.monederoVirtual.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.PriorityQueue;

/**
 * Modelo que representa una transacción automática programada
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Información de una transacción automática programada")
public class AutomaticTransaction {

    @Schema(description = "ID único de la transacción automática", example = "AUTO123")
    private String id;

    @Schema(description = "Billetera origen de donde se tomará el dinero", example = "WALLET123")
    private String originWallet;

    @Schema(description = "Billetera destino que recibirá el dinero", example = "WALLET456")
    private String recipientWallet;

    @Schema(description = "Monto de la transacción automática", example = "200.50")
    private double amount;

    @Schema(description = "Fecha y hora programada para la ejecución")
    private LocalDateTime scheduledDate;

    @Schema(description = "Periodo de recurrencia en días (0 para transacción única)", example = "30")
    private int recurrence;

    @Schema(description = "Indica si la transacción automática está activa", example = "true")
    private boolean active;

    @Schema(description = "Cliente que programó la transacción automática")
    private Customer owner;

    PriorityQueue<AutomaticTransaction> scheduledTransactions;

    @Schema(description = "Descripción de la transacción automática", example = "Pago mensual de servicios")
    private String description;

    /**
     * Verifica si la transacción debe ejecutarse en la fecha actual
     * @return true si debe ejecutarse, false en caso contrario
     */
    public boolean shouldExecute() {
        return active && LocalDateTime.now().isAfter(scheduledDate);
    }
}