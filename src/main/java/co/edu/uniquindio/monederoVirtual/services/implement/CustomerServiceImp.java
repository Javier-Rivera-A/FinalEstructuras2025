package co.edu.uniquindio.monederoVirtual.services.implement;

import co.edu.uniquindio.monederoVirtual.dto.CustomerDTO;
import co.edu.uniquindio.monederoVirtual.dto.customers.CreateCustomerDTO;
import co.edu.uniquindio.monederoVirtual.dto.customers.DeleteCustomerDTO;
import co.edu.uniquindio.monederoVirtual.dto.customers.UpdateCustomerDTO;
import co.edu.uniquindio.monederoVirtual.mapper.CustomerMapper;
import co.edu.uniquindio.monederoVirtual.model.Customer;
import co.edu.uniquindio.monederoVirtual.model.Rank;
import co.edu.uniquindio.monederoVirtual.repository.CustomerRepository;
import co.edu.uniquindio.monederoVirtual.services.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class CustomerServiceImp implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    public CustomerServiceImp() {
        this.customerRepository = new CustomerRepository("src/main/resources/Persistence/customers.txt");
    }



    /**
     * Obtiene todos los clientes del sistema
     * @return Lista de DTOs de clientes
     */
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customerMapper::customerToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerDTO> searchCustomers(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllCustomers();
        }

        String term = searchTerm.toLowerCase();
        List<Customer> customers = customerRepository.findAll();

        return customers.stream()
                .filter(customer ->
                        customer.getName().toLowerCase().contains(term) ||
                                customer.getEmail().toLowerCase().contains(term))
                .map(customerMapper::customerToDto)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene todos los clientes de un determinado rango
     * @param rank Rango de los clientes a buscar
     * @return Lista de DTOs de clientes con el rango especificado
     */
    public List<CustomerDTO> getCustomersByRank(Rank rank) {
        if (rank == null) {
            throw new IllegalArgumentException("El rango no puede ser nulo");
        }

        List<Customer> customers = customerRepository.findByRank(rank);
        return customers.stream()
                .map(customerMapper::customerToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void createCustomer(CreateCustomerDTO customerDTO) {
        // Validaciones básicas
        if (customerDTO == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo");
        }

        if (customerDTO.id() == null || customerDTO.id().trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del cliente no puede estar vacío");
        }

        if (customerRepository.findById(customerDTO.id()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un cliente con el ID: " + customerDTO.id());
        }

        // Convertir DTO a entidad y establecer valores iniciales
        Customer customer = customerMapper.createDtoToCustomer(customerDTO);
        customer.setRank(Rank.BRONZE); // Por defecto, un cliente nuevo es Bronce
        customer.setTotalPoints(0);     // Inicia con 0 puntos

        // Guardar en el repositorio
        Customer savedCustomer = customerRepository.save(customer);

    }

    @Override
    public void updateCustomer(UpdateCustomerDTO updateCustomerDTO) {
        if (updateCustomerDTO == null || updateCustomerDTO.id() == null) {
            throw new IllegalArgumentException("El cliente o su ID no pueden ser nulos");
        }

        // Verificar si el cliente existe
        Optional<Customer> existingCustomerOpt = customerRepository.findById(updateCustomerDTO.id());
        if (existingCustomerOpt.isEmpty()) {
            return; // Cliente no encontrado
        }

        Customer existingCustomer = existingCustomerOpt.get();
        Customer updatedCustomer = customerMapper.updateDtoToCustomer(updateCustomerDTO);

        // Conservar los valores que no deben cambiarse al actualizar
        updatedCustomer.setTotalPoints(existingCustomer.getTotalPoints());
        updatedCustomer.setRank(existingCustomer.getRank());
        updatedCustomer.setWallets(existingCustomer.getWallets());
        updatedCustomer.setAccounts(existingCustomer.getAccounts());
        updatedCustomer.setTransactions(existingCustomer.getTransactions());

        // Guardar cambios
         customerRepository.save(updatedCustomer);
    }

    @Override
    public CustomerDTO findById(DeleteCustomerDTO dto) {
        if (dto.id() == null) {
            throw new IllegalArgumentException("El ID del cliente no puede ser nulo");
        }

        Optional<Customer> customerOptional = customerRepository.findById(dto.id());
        return customerOptional.map(customerMapper::customerToDto).orElse(null);
    }

    @Override
    public boolean deleteCustomer(DeleteCustomerDTO deleteCustomerDTO) {
        if (deleteCustomerDTO.id() == null) {
            throw new IllegalArgumentException("El ID del cliente no puede ser nulo");
        }

        if (customerRepository.findById(deleteCustomerDTO.id()).isEmpty()) {
            return false; // Cliente no encontrado
        }

        customerRepository.deleteById(deleteCustomerDTO.id());
        return true;
    }

    @Override
    public List<CustomerDTO> listAllCustomers(CustomerDTO customerDTO) {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customerMapper::customerToDto)
                .collect(Collectors.toList());
    }
}
