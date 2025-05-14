package co.edu.uniquindio.monederoVirtual.model;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Enum que define los tipos de notificaciones en el sistema
 */
@Schema(description = "Tipos de notificaciones disponibles en el sistema")
public enum NotificationType {
    @Schema(description = "Notificación informativa")
    INFO,

    @Schema(description = "Notificación de advertencia")
    WARNING,

    @Schema(description = "Notificación de error")
    ERROR
}