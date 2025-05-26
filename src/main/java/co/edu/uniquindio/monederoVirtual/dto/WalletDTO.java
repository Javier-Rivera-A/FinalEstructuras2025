package co.edu.uniquindio.monederoVirtual.dto;
import co.edu.uniquindio.monederoVirtual.model.Account;
import co.edu.uniquindio.monederoVirtual.model.Customer;
import lombok.Getter;
import lombok.Setter;


public record WalletDTO(String name, String id, double balance, Account parentAccount, Customer owner) {

}
