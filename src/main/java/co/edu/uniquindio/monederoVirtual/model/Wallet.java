package co.edu.uniquindio.monederoVirtual.model;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "Representa una billetera digital dentro de una cuenta")
public class Wallet {

    @Schema(description = "Nombre de la billetera", example = "Ahorros")
    private String name;

    @Schema(description = "ID único de la billetera", example = "WLT123")
    private String id;

    @Schema(description = "Saldo actual de la billetera", example = "500.0")
    private double balance;

    @Schema(description = "Cuenta asociada a esta billetera")
    private Account parentAccount;

    @Schema(description = "Propietario de esta billetera")
    private Customer owner;
}