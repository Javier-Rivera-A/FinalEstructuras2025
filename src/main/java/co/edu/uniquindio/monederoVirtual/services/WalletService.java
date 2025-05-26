package co.edu.uniquindio.monederoVirtual.services;

import co.edu.uniquindio.monederoVirtual.model.Wallet;

import java.util.List;
import java.util.Optional;

public interface WalletService {
    Wallet createWallet(Wallet wallet);

    Optional<Wallet> getWalletById(String walletId);

    List<Wallet> getAllWallets();

    Wallet updateWallet(Wallet wallet);

    void deleteWallet(String walletId);
}
