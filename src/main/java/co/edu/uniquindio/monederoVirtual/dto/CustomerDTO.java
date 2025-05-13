package co.edu.uniquindio.monederoVirtual.dto;

import co.edu.uniquindio.monederoVirtual.model.Rank;

public record CustomerDTO(String name, String id, String email, String phone, Rank rank, int totalPoints) {
}
