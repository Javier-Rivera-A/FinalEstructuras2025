package co.edu.uniquindio.monederoVirtual.services;

import co.edu.uniquindio.monederoVirtual.dto.CustomerDTO;
import co.edu.uniquindio.monederoVirtual.dto.customers.CreateCustomerDTO;
import co.edu.uniquindio.monederoVirtual.dto.customers.DeleteCustomerDTO;
import co.edu.uniquindio.monederoVirtual.dto.customers.UpdateCustomerDTO;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> searchCustomers(String searchTerm);

    void createCustomer(CreateCustomerDTO createCustomerDTO);

    void updateCustomer(UpdateCustomerDTO updateCustomerDTO);

    CustomerDTO findById(DeleteCustomerDTO dto);

    boolean deleteCustomer(DeleteCustomerDTO deleteCustomerDTO);

    List<CustomerDTO> listAllCustomers(CustomerDTO customerDTO);
}
