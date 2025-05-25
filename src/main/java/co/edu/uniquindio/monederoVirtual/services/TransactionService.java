package co.edu.uniquindio.monederoVirtual.services;

public interface TransactionService {
    boolean deposit(String accountId, double amount);

    boolean withdraw(String accountId, double amount);

    boolean transfer(String fromAccountId, String toAccountId, double amount);
}
