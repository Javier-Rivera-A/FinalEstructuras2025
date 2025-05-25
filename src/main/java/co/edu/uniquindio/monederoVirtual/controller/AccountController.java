package co.edu.uniquindio.monederoVirtual.controller;

import co.edu.uniquindio.monederoVirtual.dto.AccountDTO;
import co.edu.uniquindio.monederoVirtual.dto.MessageDto;
import co.edu.uniquindio.monederoVirtual.dto.account.DeleteAccountDTO;
import co.edu.uniquindio.monederoVirtual.dto.account.FindAccountByOwnerDTO;
import co.edu.uniquindio.monederoVirtual.dto.customers.CreateCustomerDTO;
import co.edu.uniquindio.monederoVirtual.services.AccountService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    public ResponseEntity<MessageDto<String>> createAccount(@Valid @RequestBody AccountDTO accountDTO){
        accountService.createAccount(accountDTO);
        return ResponseEntity.ok(new MessageDto<>(false,"La cuenta se ha creado satisfactoriamente"));
    }

    public ResponseEntity<MessageDto<String>> findById(@Valid @RequestBody DeleteAccountDTO accountDTO){
        accountService.findAccountById(String.valueOf(accountDTO));
        return ResponseEntity.ok(new MessageDto<>(false,"La cuenta se ha encontrado"));
    }

    public ResponseEntity<MessageDto<String>> findByOwner(@Valid @RequestBody FindAccountByOwnerDTO ownerDTO){
        accountService.findAccountByOwner(ownerDTO.owner());
        return ResponseEntity.ok(new MessageDto<>(false,"La cuenta se ha encontrado"));
    }
    public ResponseEntity<MessageDto<String>> updateAccount(@Valid @RequestBody AccountDTO accountDTO){
        accountService.updateAccount(accountDTO);
        return ResponseEntity.ok(new MessageDto<>(false,"La cuenta se ha actualizado satisfactoriamente"));
    }

    public ResponseEntity<MessageDto<String>> deleteAccount(@Valid @RequestBody DeleteAccountDTO accountDTO){
        accountService.deleteAccount(accountDTO.id());
        return ResponseEntity.ok(new MessageDto<>(false,"La cuenta se ha creado satisfactoriamente"));
    }

}
