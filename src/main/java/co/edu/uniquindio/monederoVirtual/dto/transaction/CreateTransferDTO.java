package co.edu.uniquindio.monederoVirtual.dto.transaction;

public record CreateTransferDTO(String senderWallet,String recipientWallet,double amount) {
}
