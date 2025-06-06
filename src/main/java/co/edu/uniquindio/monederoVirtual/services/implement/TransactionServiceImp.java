package co.edu.uniquindio.monederoVirtual.services.implement;

import co.edu.uniquindio.monederoVirtual.model.Account;
import co.edu.uniquindio.monederoVirtual.model.Transaction;
import co.edu.uniquindio.monederoVirtual.model.TransactionType;
import co.edu.uniquindio.monederoVirtual.repository.AccountRepository;
import co.edu.uniquindio.monederoVirtual.repository.TransactionRepository;
import co.edu.uniquindio.monederoVirtual.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImp implements TransactionService {
    private final AccountRepository accountRepository;


    private final TransactionRepository transactionRepository;

    @Override
    public boolean deposit(String accountId, double amount) {
        Optional<Account> accountOpt = accountRepository.findById(accountId);
        if (accountOpt.isPresent() && amount > 0) {
            Account account = accountOpt.get();
            account.setBalance(account.getBalance() + amount);
            accountRepository.save(account);

            Transaction transaction = new Transaction();
            transaction.setAccountId(accountId);
            transaction.setType(TransactionType.valueOf("DEPOSIT"));
            transaction.setAmount(amount);
            transaction.setDate(LocalDateTime.now());
            transactionRepository.save(transaction);

            return true;
        }
        return false;
    }

    @Override
    public boolean withdraw(String accountId, double amount) {
        Optional<Account> accountOpt = accountRepository.findById(accountId);
        if (accountOpt.isPresent() && amount > 0) {
            Account account = accountOpt.get();
            if (account.getBalance() >= amount) {
                account.setBalance(account.getBalance() - amount);
                accountRepository.save(account);

                Transaction transaction = new Transaction();
                transaction.setAccountId(accountId);
                transaction.setType(TransactionType.valueOf("WITHDRAW"));
                transaction.setAmount(amount);
                transaction.setDate(LocalDateTime.now());
                transactionRepository.save(transaction);

                return true;
            }
        }
        return false;
    }

    @Override
    public boolean transfer(String fromAccountId, Account toAccountId, double amount) {
        Optional<Account> fromAccountOpt = accountRepository.findById(fromAccountId);
        Optional<Account> toAccountOpt = accountRepository.findById(toAccountId.getAccountID());
        if (fromAccountOpt.isPresent() && toAccountOpt.isPresent() && amount > 0) {
            Account fromAccount = fromAccountOpt.get();
            Account toAccount = toAccountOpt.get();
            if (fromAccount.getBalance() >= amount) {
                fromAccount.setBalance(fromAccount.getBalance() - amount);
                toAccount.setBalance(toAccount.getBalance() + amount);
                accountRepository.save(fromAccount);
                accountRepository.save(toAccount);

                Transaction transaction = new Transaction();
                transaction.setAccountId(fromAccountId);
                transaction.setType(TransactionType.valueOf("TRANSFER"));
                transaction.setAmount(amount);
                transaction.setDate(LocalDateTime.now());
                transaction.setToAccount(toAccountId);
                transactionRepository.save(transaction);

                return true;
            }
        }
        return false;
    }

}

