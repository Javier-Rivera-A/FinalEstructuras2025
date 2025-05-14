package co.edu.uniquindio.monederoVirtual.dto.transaction;

import java.time.LocalDateTime;

public record CreateAutoTransactionsDTO(String originWallet, String recipientWallet, double amount, LocalDateTime scheduledDate,int recurrence) {
}
