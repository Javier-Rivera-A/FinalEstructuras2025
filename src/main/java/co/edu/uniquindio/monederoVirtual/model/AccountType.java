package co.edu.uniquindio.monederoVirtual.model;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Enum que define los tipos de cuentas disponibles en el sistema
 */
@Schema(description = "Tipos de cuenta disponibles en el sistema")
public enum AccountType {
    @Schema(description = "Cuenta de ahorros")
    SAVINGS,
    @Schema(description = "Cuenta corriente")
    CHECKING
}