package co.edu.uniquindio.monederoVirtual.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Tipos de transacciones disponibles en el sistema")
public enum TransactionType {
    DEPOSIT,
    WITHDRAWAL,
    TRANSFER,
    AUTOMATIC,
    BALANCE_INQUIRY
}