package co.edu.uniquindio.monederoVirtual.repository;

import co.edu.uniquindio.monederoVirtual.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, String> {
}
