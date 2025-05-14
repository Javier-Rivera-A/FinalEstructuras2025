package co.edu.uniquindio.monederoVirtual.dto.wallets;

import co.edu.uniquindio.monederoVirtual.model.Account;
import co.edu.uniquindio.monederoVirtual.model.Customer;

public record CreateWalletDTO(String name, String id, double balance, Account parentAccount, Customer owner) {
}
