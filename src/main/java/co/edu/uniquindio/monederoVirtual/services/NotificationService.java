package co.edu.uniquindio.monederoVirtual.services;

import co.edu.uniquindio.monederoVirtual.dto.NotificationDTO;

public interface NotificationService {
    void sendNotification(String id, NotificationDTO account);
}
