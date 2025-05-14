package co.edu.uniquindio.monederoVirtual.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Modelo que representa una consulta de saldo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Representa una consulta de saldo de una billetera")
public class BalanceInquiry {

    @Schema(description = "ID único de la consulta", example = "INQ123")
    private String id;

    @Schema(description = "ID de la billetera consultada", example = "WALLET123")
    private String originWallet;

    @Schema(description = "Cliente que realiza la consulta")
    private Customer customer;

    @Schema(description = "Resultado de la consulta de saldo", example = "1500.75")
    private double result;

    @Schema(description = "Indica si la consulta fue exitosa", example = "true")
    private boolean successful;
}