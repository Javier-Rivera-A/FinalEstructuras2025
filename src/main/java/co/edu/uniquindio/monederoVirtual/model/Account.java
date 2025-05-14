package co.edu.uniquindio.monederoVirtual.model;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Información de una cuenta financiera del cliente")
public class Account {

    @Schema(description = "ID único de la cuenta", example = "ACC123456")
    private String accountID;

    @Schema(description = "Saldo actual de la cuenta", example = "2500.75")
    private double balance;

    @Schema(description = "Tipo de cuenta: SAVINGS o CHECKING", example = "SAVINGS")
    private AccountType accountType;

    @Schema(description = "Cliente dueño de esta cuenta")
    private Customer owner;

    @Schema(description = "Lista de transacciones asociadas a la cuenta")
    private List<Transaction> transactions;

    @Schema(description = "Sub-billeteras asociadas a esta cuenta")
    private List<Wallet> subWallets;
}
