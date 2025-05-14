package co.edu.uniquindio.monederoVirtual.dto;

import co.edu.uniquindio.monederoVirtual.model.Customer;
import co.edu.uniquindio.monederoVirtual.model.NotificationType;

public record NotificationDTO(String tittle, String text, Customer recipient, NotificationType type) {
}
