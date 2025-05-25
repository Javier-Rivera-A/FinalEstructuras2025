package co.edu.uniquindio.monederoVirtual.controller;


import co.edu.uniquindio.monederoVirtual.dto.MessageDto;
import co.edu.uniquindio.monederoVirtual.dto.NotificationDTO;
import co.edu.uniquindio.monederoVirtual.services.NotificationService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationController {
    private final NotificationService service;
    @PostMapping
    public ResponseEntity<MessageDto<String>> sendNotification(@PathVariable String id, @RequestBody NotificationDTO account){
        service.sendNotification(id, account);
        return ResponseEntity.ok(new MessageDto<>(false, "Notificación enviada con éxito"));
    }
}
