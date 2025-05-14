package co.edu.uniquindio.monederoVirtual.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos necesarios para realizar un retiro desde una billetera")
public class Withdrawal {

    @Schema(description = "ID de la billetera desde la cual se realiza el retiro", example = "WALLET123")
    private String destination;
}
