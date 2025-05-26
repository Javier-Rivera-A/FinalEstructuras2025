package co.edu.uniquindio.monederoVirtual.services;

import co.edu.uniquindio.monederoVirtual.model.Account;

public interface TransactionService {
    boolean deposit(String accountId, double amount);

    boolean withdraw(String accountId, double amount);


    boolean transfer(String fromAccountId, Account toAccountId, double amount);
}
