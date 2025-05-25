package co.edu.uniquindio.monederoVirtual.services.implement;

import co.edu.uniquindio.monederoVirtual.dto.AccountDTO;
import co.edu.uniquindio.monederoVirtual.mapper.AccountMapper;
import co.edu.uniquindio.monederoVirtual.model.Account;
import co.edu.uniquindio.monederoVirtual.model.Customer;

import co.edu.uniquindio.monederoVirtual.repository.AccountRepository;
import co.edu.uniquindio.monederoVirtual.services.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class AccountServiceImp implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper = AccountMapper.INSTANCE;

    public AccountServiceImp(AccountRepository repository) {
        this.accountRepository = new AccountRepository("src/main/resources/Persistence/accounts.txt");
    }
    @Override
    public AccountDTO createAccount(AccountDTO accountDTO){
        if(accountDTO == null){
            throw new IllegalArgumentException("La cuenta no puede ser nula");
        }
        if (accountDTO.accountID() == null || accountDTO.accountID().trim().isEmpty()) {
            throw new IllegalArgumentException("El ID de la cuenta no puede estar vacío");
        }

        if (accountRepository.findByID(accountDTO.accountID()).isPresent()) {
            throw new IllegalArgumentException("Ya existe la cuenta con ID: " + accountDTO.accountID());
        }

        Account account = accountMapper.dtoToAccount(accountDTO);
        Account savedAccount = accountRepository.save(account);

        return accountMapper.accountToDto(savedAccount);
    }
    @Override
    public AccountDTO findAccountById(String id){
        if(id == null){
            throw new IllegalArgumentException("El ID de la cuenta no puede ser nulo");
        }
        Optional<Account> accountOptional = accountRepository.findByID(id);
        return accountOptional.map(accountMapper::accountToDto).orElse(null);
    }
    @Override
    public AccountDTO findAccountByOwner(Customer owner){
        if(owner == null){
            throw new IllegalArgumentException("El dueño de la cuenta no puede ser nulo");
        }
        Optional<Account> accountOptional = accountRepository.findByOwner(owner);
        return accountOptional.map(accountMapper::accountToDto).orElse(null);
    }
    @Override
    public List<AccountDTO> getAllAccounts(){
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(accountMapper::accountToDto).collect(Collectors.toList());
    }

    @Override
    public AccountDTO updateAccount(AccountDTO accountDTO){
        if (accountDTO == null || accountDTO.accountID() == null) {
            throw new IllegalArgumentException("La cuenta o su ID no pueden ser nulos");
        }
        Optional<Account> existingAccountOpt = accountRepository.findByID(accountDTO.accountID());
        if(existingAccountOpt.isEmpty()){
            return null;
        }
        Account existingAccount = existingAccountOpt.get();
        Account updatedAccount = accountMapper.dtoToAccount(accountDTO);

        updatedAccount.setAccountID(existingAccount.getAccountID());
        updatedAccount.setAccountType(existingAccount.getAccountType());
        updatedAccount.setOwner(existingAccount.getOwner());
        updatedAccount.setBalance(existingAccount.getBalance());
        updatedAccount.setTransactions(existingAccount.getTransactions());
        updatedAccount.setSubWallets(existingAccount.getSubWallets());

        Account savedAccount = accountRepository.save(updatedAccount);
        return accountMapper.accountToDto(savedAccount);
    }

    @Override
    public boolean deleteAccount(String id){
        if(id == null){
            throw new IllegalArgumentException("El ID de la cuenta no puede ser nulo");
        }
        if(accountRepository.findByID(id).isEmpty()){
            return false;
        }
        accountRepository.deleteById(id);
        return true;
    }
}
