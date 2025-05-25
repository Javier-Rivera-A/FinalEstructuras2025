package co.edu.uniquindio.monederoVirtual.mapper;

import co.edu.uniquindio.monederoVirtual.dto.NotificationDTO;
import co.edu.uniquindio.monederoVirtual.model.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    NotificationMapper INSTANCE = Mappers.getMapper(NotificationMapper.class);
    Notification dtoToNotification(NotificationDTO notificationDTO);
    NotificationDTO notificationToDto(Notification notification);
}
