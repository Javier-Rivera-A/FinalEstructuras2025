package co.edu.uniquindio.monederoVirtual.services.implement;

import co.edu.uniquindio.monederoVirtual.dto.NotificationDTO;
import co.edu.uniquindio.monederoVirtual.mapper.NotificationMapper;
import co.edu.uniquindio.monederoVirtual.model.Notification;
import co.edu.uniquindio.monederoVirtual.repository.NotificationRepository;
import co.edu.uniquindio.monederoVirtual.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImp implements NotificationService {
    private final NotificationMapper notificationMapper = NotificationMapper.INSTANCE;
    private final NotificationRepository notificationRepository ;


    @Override
    public void sendNotification(String id, NotificationDTO  account) {

        Notification notification = notificationMapper.dtoToNotification(account);
        notification.setRead(false);
        notificationRepository.save(notification);
    }


}
