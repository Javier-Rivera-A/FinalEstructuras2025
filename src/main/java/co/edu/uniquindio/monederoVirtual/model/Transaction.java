package co.edu.uniquindio.monederoVirtual.model;

import java.time.LocalDateTime;

public abstract class Transaction {
    private String id;
    private LocalDateTime date;
    private double amount;
    private Account fromAccount,toAccount;
    private Wallet fromWallet,toWallet;
    private Customer customer;

}