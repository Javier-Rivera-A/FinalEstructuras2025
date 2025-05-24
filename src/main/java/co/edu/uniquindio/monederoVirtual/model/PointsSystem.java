package co.edu.uniquindio.monederoVirtual.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Modelo que representa un sistema de puntos que registra, asigna y permite el canje de puntos por transacción
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Sistema de puntos que registra, asigna y permite el canje de puntos por transacción")
public class PointsSystem {

    @Schema(description = "ID único del registro de puntos", example = "PTS123")
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
    TreeMap<LocalDateTime, PointsSystem> pointsHistory;
    @Schema(description = "Configuración de puntos por tipo de transacción")
    private Map<TransactionType, Integer> pointsConfig = new HashMap<>();

    /**
     * Inicializa la configuración de puntos por defecto
     */
    public void initializeDefaultConfig() {
        pointsConfig.put(TransactionType.DEPOSIT, 1); // 1 punto por cada 100 unidades
        pointsConfig.put(TransactionType.WITHDRAWAL, 2); // 2 puntos por cada 100 unidades
        pointsConfig.put(TransactionType.TRANSFER, 3); // 3 puntos por cada 100 unidades
        pointsConfig.put(TransactionType.AUTOMATIC, 4); // 4 puntos por cada 100 unidades
        pointsConfig.put(TransactionType.BALANCE_INQUIRY, 0); // 0 puntos por consulta
    }

    /**
     * Calcula los puntos a otorgar por una transacción
     * @param transaction La transacción realizada
     * @return Cantidad de puntos a otorgar
     */
    public int calculatePoints(Transaction transaction) {
        if (!pointsConfig.containsKey(transaction.getType())) {
            return 0;
        }

        int pointsPerHundred = pointsConfig.get(transaction.getType());

        // Cálculo de puntos: puntos por cada 100 unidades
        return (int) (transaction.getAmount() / 100) * pointsPerHundred;
    }

    /**
     * Establece los puntos para un tipo de transacción específico
     * @param type El tipo de transacción
     * @param pointsPerHundred Puntos por cada 100 unidades
     */
    public void setPointsForTransactionType(TransactionType type, int pointsPerHundred) {
        pointsConfig.put(type, pointsPerHundred);
    }

    /**
     * Obtiene la configuración de puntos para un tipo de transacción
     * @param type El tipo de transacción
     * @return Puntos por cada 100 unidades o 0 si no está configurado
     */
    public int getPointsForTransactionType(TransactionType type) {
        return pointsConfig.getOrDefault(type, 0);
    }
}