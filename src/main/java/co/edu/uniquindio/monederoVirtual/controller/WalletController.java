package co.edu.uniquindio.monederoVirtual.controller;

import co.edu.uniquindio.monederoVirtual.dto.MessageDto;
import co.edu.uniquindio.monederoVirtual.dto.WalletDTO;
import co.edu.uniquindio.monederoVirtual.services.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;
    @PostMapping
    public ResponseEntity<MessageDto<String>> createWallet(@Valid @RequestBody WalletDTO walletDTO){
        walletService.createWallet(walletDTO);
        return  ResponseEntity.ok(new MessageDto<>(false,"Wallet creada"));
    }
    @DeleteMapping
    public ResponseEntity<MessageDto<String>> deleteWallet(@Valid @RequestBody WalletDTO walletDTO){
        walletService.deleteWallet(walletDTO.toString());
        return  ResponseEntity.ok(new MessageDto<>(false,"Wallet creada"));
    }
    @PutMapping
    public ResponseEntity<MessageDto<String>> updateWallet(@Valid @RequestBody WalletDTO walletDTO){
        walletService.updateWallet(walletDTO);
        return  ResponseEntity.ok(new MessageDto<>(false,"Wallet creada"));
    }
}
