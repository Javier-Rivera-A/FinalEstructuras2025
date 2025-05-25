package co.edu.uniquindio.monederoVirtual.controller;

import co.edu.uniquindio.monederoVirtual.dto.CustomerDTO;
import co.edu.uniquindio.monederoVirtual.dto.MessageDto;
import co.edu.uniquindio.monederoVirtual.dto.customers.CreateCustomerDTO;
import co.edu.uniquindio.monederoVirtual.dto.customers.DeleteCustomerDTO;
import co.edu.uniquindio.monederoVirtual.dto.customers.UpdateCustomerDTO;
import co.edu.uniquindio.monederoVirtual.model.Customer;
import co.edu.uniquindio.monederoVirtual.services.CustomerService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping
    public ResponseEntity<MessageDto<String>> listAllCustomers(@Valid @RequestBody CustomerDTO customerDTO){
        customerService.listAllCustomers(customerDTO);
        return ResponseEntity.ok(new MessageDto<>(false,"El cliente ha sido creado satisfactoriamente"));
    }
    @PutMapping
    public ResponseEntity<MessageDto<String>> updateCustomer(@Valid @RequestBody UpdateCustomerDTO updateCustomerDTO){
        customerService.updateCustomer(updateCustomerDTO);
        return ResponseEntity.ok(new MessageDto<>(false,"El cliente ha sido actualizado"));
    }
    @GetMapping
    public ResponseEntity<MessageDto<String>> getCustomer(@Valid @RequestBody DeleteCustomerDTO dto){
        customerService.findById(dto);
        return ResponseEntity.ok(new MessageDto<>(false,"Cliente encontrado"));
    }
    @DeleteMapping
    public ResponseEntity<MessageDto<String>> deleteCustomer(@Valid @RequestBody DeleteCustomerDTO deleteCustomerDTO){
        customerService.deleteCustomer(deleteCustomerDTO);
        return ResponseEntity.ok(new MessageDto<>(false,"Cliente eliminado"));
    }
    @GetMapping
    public ResponseEntity<MessageDto<String>> searchCustomers(@Valid @RequestBody UpdateCustomerDTO updateCustomerDTO){
        customerService.searchCustomers(String.valueOf(updateCustomerDTO));
        return ResponseEntity.ok(new MessageDto<>(false,"Cliente encontrado"));
    }
}
