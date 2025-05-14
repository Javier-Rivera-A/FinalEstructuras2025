package co.edu.uniquindio.monederoVirtual.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

/**
 * Modelo que representa una notificación enviada a un cliente
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Información de una notificación del sistema")
public class Notification {

    @Schema(description = "ID único de la notificación", example = "NOTIF123")
    private String id;

    @Schema(description = "Título de la notificación", example = "Alerta de saldo bajo")
    private String title;

    @Schema(description = "Contenido de la notificación", example = "Tu billetera 'Principal' tiene un saldo inferior a $100")
    private String text;

    @Schema(description = "Momento en que se generó la notificación")
    private LocalDateTime timeStamp;

    @Schema(description = "Cliente destinatario de la notificación")
    private Customer recipient;

    @Schema(description = "Tipo de notificación")
    private NotificationType type;

    @Schema(description = "Indica si la notificación ha sido leída", example = "false")
    private boolean read;

    /**
     * Marca la notificación como leída
     */
    public void markAsRead() {
        this.read = true;
    }
}