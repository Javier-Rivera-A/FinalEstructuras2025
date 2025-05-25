package co.edu.uniquindio.monederoVirtual.services.implement;

import co.edu.uniquindio.monederoVirtual.model.Customer;
import co.edu.uniquindio.monederoVirtual.model.Transaction;
import co.edu.uniquindio.monederoVirtual.model.Wallet;
import co.edu.uniquindio.monederoVirtual.services.CustomerService;
import co.edu.uniquindio.monederoVirtual.services.PointService;
import co.edu.uniquindio.monederoVirtual.services.TransactionService;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class WalletServiceImp {
    private Map<String, Wallet> wallets;
    private TransactionService transactionService;
    private CustomerService customerService;
    private PointService pointsService;
    private Queue<Transaction> scheduledTransactions;
}