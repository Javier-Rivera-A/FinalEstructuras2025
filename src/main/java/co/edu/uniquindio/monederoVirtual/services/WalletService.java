package co.edu.uniquindio.monederoVirtual.services;

import co.edu.uniquindio.monederoVirtual.dto.WalletDTO;
import co.edu.uniquindio.monederoVirtual.model.Wallet;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

public interface WalletService {
    Wallet createWallet(@Valid WalletDTO wallet);

    Optional<Wallet> getWalletById(String walletId);

    List<Wallet> getAllWallets();

    Wallet updateWallet(@Valid WalletDTO wallet);

    void deleteWallet(String walletId);
}
