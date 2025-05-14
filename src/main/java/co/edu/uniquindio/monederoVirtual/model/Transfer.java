package co.edu.uniquindio.monederoVirtual.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos necesarios para realizar una transferencia entre billeteras")
public class Transfer {

    @Schema(description = "ID de la billetera que envía el dinero", example = "WALLET123")
    private String senderWallet;

    @Schema(description = "ID de la billetera que recibe el dinero", example = "WALLET456")
    private String recipientWallet;
}
