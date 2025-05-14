package co.edu.uniquindio.monederoVirtual.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Modelo que representa un depósito de dinero a una billetera
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Información de un depósito a una billetera")
public class Deposit {

    @Schema(description = "ID único del depósito", example = "DEP123")
    private String id;

    @Schema(description = "Fuente del depósito (ej: transferencia bancaria, efectivo)", example = "BANK_TRANSFER")
    private String source;

    @Schema(description = "ID de la billetera que recibe el depósito", example = "WALLET123")
    private String recipientWallet;

    @Schema(description = "Monto del depósito", example = "500.00")
    private double amount;

    @Schema(description = "Cliente que realiza el depósito")
    private Customer customer;

    @Schema(description = "Descripción del depósito", example = "Recarga mensual")
    private String description;
}