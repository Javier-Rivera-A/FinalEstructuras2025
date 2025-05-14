package co.edu.uniquindio.monederoVirtual.controller;

import co.edu.uniquindio.monederoVirtual.dto.MessageDto;
import co.edu.uniquindio.monederoVirtual.dto.customers.CreateCustomerDTO;
import co.edu.uniquindio.monederoVirtual.model.Customer;
import co.edu.uniquindio.monederoVirtual.services.CustomerService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<MessageDto<String>> createCustomer(@Valid @RequestBody CreateCustomerDTO createCustomerDTO){
            customerService.createCustomer(createCustomerDTO);
            return ResponseEntity.ok(new MessageDto<>(false,"El cliente ha sido creado satisfactoriamente"));
    }
}
