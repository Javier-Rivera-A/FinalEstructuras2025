package co.edu.uniquindio.monederoVirtual.services;

import co.edu.uniquindio.monederoVirtual.dto.AccountDTO;
import co.edu.uniquindio.monederoVirtual.model.Customer;

import java.util.List;

public interface AccountService {
    AccountDTO createAccount(AccountDTO accountDTO);

    AccountDTO findAccountById(String id);

    AccountDTO findAccountByOwner(Customer owner);

    List<AccountDTO> getAllAccounts();

    AccountDTO updateAccount(AccountDTO accountDTO);

    boolean deleteAccount(String id);
}
