package co.edu.uniquindio.monederoVirtual.controller;

import co.edu.uniquindio.monederoVirtual.dto.MessageDto;
import co.edu.uniquindio.monederoVirtual.dto.PointsTdo;
import co.edu.uniquindio.monederoVirtual.services.PointService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class PointsController {
    private final PointService pointService;

    @PostMapping
    public ResponseEntity<MessageDto<String>> createPoints(@Valid @RequestBody PointsTdo tdo){
        pointService.createPointsTransaction(tdo.customer(),tdo.transaction());
        return ResponseEntity.ok(new MessageDto<>(false, "Puntos asignados por transaccion"));
    }
    @PutMapping
    public ResponseEntity<MessageDto<String>> redeemPoints(@Valid @RequestBody PointsTdo tdo){
        pointService.redeemPoints(tdo.customer().getId(),tdo.pointsToRedeem());
        return ResponseEntity.ok(new MessageDto<>(false, "Puntos redimidos"));
    }
    @PutMapping
    public ResponseEntity<MessageDto<String>> updatePoints(@Valid @RequestBody PointsTdo tdo){
        pointService.recalculatePoints(tdo.customer().getId(), tdo.pointsToRedeem());
        return ResponseEntity.ok(new MessageDto<>(false, "Puntos totales re calculados"));
    }
    @GetMapping
    public ResponseEntity<MessageDto<String>> getCustomerRank(@Valid @RequestBody PointsTdo tdo){
        pointService.getCustomerRank(tdo.customer().getId());
        return ResponseEntity.ok(new MessageDto<>(false, "Rango encontrado"));
    }
}
