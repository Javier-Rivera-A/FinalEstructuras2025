package co.edu.uniquindio.monederoVirtual.services;

import co.edu.uniquindio.monederoVirtual.dto.CustomerDTO;
import co.edu.uniquindio.monederoVirtual.dto.customers.CreateCustomerDTO;
import co.edu.uniquindio.monederoVirtual.dto.customers.DeleteCustomerDTO;
import co.edu.uniquindio.monederoVirtual.dto.customers.UpdateCustomerDTO;
import co.edu.uniquindio.monederoVirtual.model.Rank;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getAllCustomers();

    List<CustomerDTO> searchCustomers(String searchTerm);

    List<CustomerDTO> getCustomersByRank(Rank rank);

    void createCustomer(CreateCustomerDTO createCustomerDTO);

    void updateCustomer(UpdateCustomerDTO updateCustomerDTO);

    CustomerDTO findById(DeleteCustomerDTO dto);

    boolean deleteCustomer(DeleteCustomerDTO deleteCustomerDTO);

    List<CustomerDTO> listAllCustomers(CustomerDTO customerDTO);


}
