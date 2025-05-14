package co.edu.uniquindio.monederoVirtual.dto.transaction;

import co.edu.uniquindio.monederoVirtual.model.Customer;

public record CreateDepositDTO(String id, String source, String recipientWallet, double amount, Customer customer) {
}
