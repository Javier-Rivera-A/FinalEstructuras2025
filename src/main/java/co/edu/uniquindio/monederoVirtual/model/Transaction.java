package co.edu.uniquindio.monederoVirtual.model;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Clase abstracta base para representar una transacción financiera")
public abstract class Transaction {

    @Schema(description = "ID único de la transacción", example = "TX12345")
    private String id;

    @Schema(description = "Fecha y hora de la transacción")
    private LocalDateTime date;

    @Schema(description = "Monto de dinero involucrado en la transacción", example = "250.0")
    private double amount;

    @Schema(description = "Cuenta de origen involucrada en la transacción")
    private Account fromAccount;

    @Schema(description = "Cuenta de destino involucrada en la transacción")
    private Account toAccount;

    @Schema(description = "Billetera de origen involucrada en la transacción")
    private Wallet fromWallet;

    @Schema(description = "Billetera de destino involucrada en la transacción")
    private Wallet toWallet;

    @Schema(description = "Cliente que realizó la transacción")
    private Customer customer;

    // Método para obtener el tipo de transacción
    // Método para establecer el tipo de transacción
    @Getter
    @Setter
    @Schema(description = "Tipo de transacción")
    private TransactionType type;

    // Método abstracto que debe ser implementado por las subclases
    public abstract void execute();

}