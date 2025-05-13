package co.edu.uniquindio.monederoVirtual.dto;

import java.time.LocalDateTime;

public record TransactionDTO(String id, LocalDateTime date, double amount) {
}
