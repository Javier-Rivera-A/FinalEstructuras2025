package co.edu.uniquindio.monederoVirtual.dto;

import co.edu.uniquindio.monederoVirtual.model.AccountType;
import co.edu.uniquindio.monederoVirtual.model.Customer;

public record AccountDTO(String accountID, double balance, AccountType accountType, Customer owner) {
}
