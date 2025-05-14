package co.edu.uniquindio.monederoVirtual.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

/**
 * Modelo que representa los puntos acumulados por un cliente
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Registro de puntos acumulados por transacción")
public class Points {

    @Schema(description = "ID único del registro de puntos", example = "PNT123")
    private String pointsID;

    @Schema(description = "Cantidad de puntos asignados", example = "15")
    private int amount;

    @Schema(description = "Fecha y hora en que se asignaron los puntos")
    private LocalDateTime dateTime;

    @Schema(description = "Transacción que generó estos puntos")
    private Transaction sourceTransaction;

    @Schema(description = "Cliente al que pertenecen los puntos")
    private Customer customer;

    @Schema(description = "Indica si los puntos ya fueron canjeados", example = "false")
    private boolean redeemed;

    @Schema(description = "Fecha de expiración de los puntos, si aplica")
    private LocalDateTime expirationDate;

    /**
     * Verifica si los puntos han expirado
     * @return true si los puntos han expirado, false en caso contrario
     */
    public boolean isExpired() {
        if (expirationDate == null) {
            return false;
        }
        return LocalDateTime.now().isAfter(expirationDate);
    }

    /**
     * Marca los puntos como canjeados
     */
    public void markAsRedeemed() {
        this.redeemed = true;
    }
}