package co.edu.uniquindio.monederoVirtual.bussinesLogic;

import co.edu.uniquindio.monederoVirtual.dto.CustomerDTO;
import co.edu.uniquindio.monederoVirtual.dto.customers.CreateCustomerDTO;
import co.edu.uniquindio.monederoVirtual.dto.customers.DeleteCustomerDTO;
import co.edu.uniquindio.monederoVirtual.dto.customers.UpdateCustomerDTO;
import co.edu.uniquindio.monederoVirtual.model.Customer;
import co.edu.uniquindio.monederoVirtual.model.Rank;
import co.edu.uniquindio.monederoVirtual.repository.CustomerRepository;
import co.edu.uniquindio.monederoVirtual.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    private final String customerId = "test-customer-001";

    @BeforeEach
    public void setup() {
        customerRepository.deleteAll();
    }

    @Test
    public void testCreateCustomer() {
        CreateCustomerDTO dto = new CreateCustomerDTO(
                "Juan Pérez",
                customerId,
                "juan@example.com",
                "1542"
        );

        customerService.createCustomer(dto);

        Optional<Customer> result = customerRepository.findById(customerId);
        assertTrue(result.isPresent());
        assertEquals("Juan Pérez", result.get().getName());
        assertEquals(Rank.BRONZE, result.get().getRank());
    }

    @Test
    public void testUpdateCustomer() {
        // Primero crear uno
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setName("Nombre Inicial");
        customer.setEmail("inicial@correo.com");
        customer.setRank(Rank.SILVER);
        customerRepository.save(customer);

        UpdateCustomerDTO dto = new UpdateCustomerDTO(
               "Chupapija", "Chupapijita3",
                "1524450",
                customerId
        );

        customerService.updateCustomer(dto);

        Customer updated = customerRepository.findById(customerId).orElseThrow();
        assertEquals("Chupapija", updated.getName());
        assertEquals("Chupapijita3", updated.getEmail());
        assertEquals(Rank.SILVER, updated.getRank());
    }

    @Test
    public void testFindById() {
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setName("Carlos López");
        customer.setEmail("carlos@correo.com");
        customer.setRank(Rank.GOLD);
        customerRepository.save(customer);

        DeleteCustomerDTO dto = new DeleteCustomerDTO(customerId);
        CustomerDTO found = customerService.findById(dto);

        assertNotNull(found);
        assertEquals("Carlos López", found.name());
    }

    @Test
    public void testDeleteCustomer() {
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setName("Eliminar Yo");
        customer.setEmail("delete@correo.com");
        customerRepository.save(customer);

        DeleteCustomerDTO dto = new DeleteCustomerDTO(customerId);
        boolean deleted = customerService.deleteCustomer(dto);

        assertTrue(deleted);
        assertFalse(customerRepository.findById(customerId).isPresent());
    }

    @Test
    public void testGetAllCustomers() {
        for (int i = 1; i <= 2; i++) {
            Customer customer = new Customer();
            customer.setId("id" + i);
            customer.setName("Cliente " + i);
            customer.setEmail("cliente" + i + "@correo.com");
            customerRepository.save(customer);
        }

        List<CustomerDTO> customers = customerService.getAllCustomers();
        assertEquals(2, customers.size());
    }

    @Test
    public void testSearchCustomers() {
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setName("Lucas Ramirez");
        customer.setEmail("lucas@correo.com");
        customerRepository.save(customer);

        List<CustomerDTO> result = customerService.searchCustomers("lucas");
        assertFalse(result.isEmpty());
        assertEquals("Lucas Ramirez", result.get(0).name());
    }

    @Test
    public void testGetCustomersByRank() {
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setName("Rankeado");
        customer.setEmail("rank@correo.com");
        customer.setRank(Rank.GOLD);
        customerRepository.save(customer);

        List<CustomerDTO> goldCustomers = customerService.getCustomersByRank(Rank.GOLD);
        assertEquals(1, goldCustomers.size());
        assertEquals("Rankeado", goldCustomers.get(0).name());
    }
}
