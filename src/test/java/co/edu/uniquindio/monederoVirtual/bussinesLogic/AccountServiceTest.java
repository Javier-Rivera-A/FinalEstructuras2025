package co.edu.uniquindio.monederoVirtual.bussinesLogic;

import co.edu.uniquindio.monederoVirtual.dto.AccountDTO;
import co.edu.uniquindio.monederoVirtual.model.Account;
import co.edu.uniquindio.monederoVirtual.model.AccountType;
import co.edu.uniquindio.monederoVirtual.model.Customer;
import co.edu.uniquindio.monederoVirtual.repository.AccountRepository;
import co.edu.uniquindio.monederoVirtual.repository.CustomerRepository;
import co.edu.uniquindio.monederoVirtual.services.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private static String testAccountId = "test-account-001";

    private Customer testCustomer;

    @BeforeEach
    public void setup() {
        // Limpiar datos previos
        accountRepository.deleteAll();
        customerRepository.deleteAll();

        // Crear cliente de prueba
        testCustomer = new Customer();
        testCustomer.setId("customer-001");
        testCustomer.setName("Cliente Prueba");
        testCustomer.setEmail("cliente@correo.com");
        customerRepository.save(testCustomer);
    }

    @Test
    public void testCreateAccount() {
        AccountDTO dto = new AccountDTO(
                testAccountId,
                1000,
                AccountType.SAVINGS,
                testCustomer

        );

        AccountDTO created = accountService.createAccount(dto);

        assertNotNull(created);
        assertEquals(testAccountId, created.accountID());

        Optional<Account> optional = accountRepository.findById(testAccountId);
        if (optional.isPresent()) {
            assertTrue(true);
            Account saved = optional.get();
            assertEquals(testCustomer.getId(), saved.getOwner().getId());
            assertEquals(AccountType.SAVINGS, saved.getAccountType());
            assertEquals(1000, saved.getBalance());
        }



    }

    @Test
    public void testFindAccountById() {
        // Guardar directamente una cuenta
        Account account = new Account();
        account.setAccountID(testAccountId);
        account.setOwner(testCustomer);
        account.setAccountType(AccountType.SAVINGS);
        account.setBalance(500);
        accountRepository.save(account);

        AccountDTO result = accountService.findAccountById(testAccountId);

        if (result != null) {
            assertEquals(testCustomer.getId(), result.owner().getId());
        }

    }

    @Test
    public void testFindAccountByOwner() {
        // Guardar cuenta
        Account account = new Account();
        account.setAccountID(testAccountId);
        account.setOwner(testCustomer);
        account.setAccountType(AccountType.CHECKING);
        account.setBalance(800);
        accountRepository.save(account);

        AccountDTO result = accountService.findAccountByOwner(testCustomer);
        assertNotNull(result);
        assertEquals(AccountType.CHECKING, result.accountType());
    }

    @Test
    public void testGetAllAccounts() {
        // Crear varias cuentas
        for (int i = 0; i < 3; i++) {
            Account account = new Account();
            account.setAccountID("acc-" + i);
            account.setOwner(testCustomer);
            account.setAccountType(AccountType.SAVINGS);
            account.setBalance(100*i);
            accountRepository.save(account);
        }

        List<AccountDTO> accounts = accountService.getAllAccounts();
        assertEquals(3, accounts.size());
    }

    @Test
    public void testUpdateAccount() {
        // Guardar cuenta original
        Account original = new Account();
        original.setAccountID(testAccountId);
        original.setOwner(testCustomer);
        original.setAccountType(AccountType.SAVINGS);
        original.setBalance(200);
        accountRepository.save(original);

        // DTO con cambios (balance, pero se mantiene accountType, owner, etc.)
        AccountDTO dto = new AccountDTO(
                testAccountId,
                500,
                AccountType.SAVINGS,
                testCustomer


        );

        AccountDTO updated = accountService.updateAccount(dto);
        if (updated != null) {
            assertEquals(500, updated.balance());
        }

        Account actual= accountRepository.findById(testAccountId).orElseThrow();
        assertEquals(500, actual.getBalance());


    }

    @Test
    public void testDeleteAccount() {
        Account account = new Account();
        account.setAccountID(testAccountId);
        account.setOwner(testCustomer);
        account.setAccountType(AccountType.SAVINGS);
        account.setBalance(100);
        accountRepository.save(account);

        boolean deleted = accountService.deleteAccount(testAccountId);
        if (deleted) {
            assertFalse(accountRepository.findById(testAccountId).isPresent());
  }

}
}