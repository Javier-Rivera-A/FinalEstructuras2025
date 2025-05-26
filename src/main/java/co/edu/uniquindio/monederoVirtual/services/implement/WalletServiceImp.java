package co.edu.uniquindio.monederoVirtual.services.implement;

import co.edu.uniquindio.monederoVirtual.model.Wallet;
import co.edu.uniquindio.monederoVirtual.repository.WalletRepository;
import co.edu.uniquindio.monederoVirtual.services.WalletService;

import java.util.*;

public class WalletServiceImp implements WalletService {

    private final WalletRepository walletRepository;

    public WalletServiceImp(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public Wallet createWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    @Override
    public Optional<Wallet> getWalletById(String walletId) {
        return walletRepository.findById(walletId);
    }

    @Override
    public List<Wallet> getAllWallets() {
        return walletRepository.findAll();
    }

    @Override
    public Wallet updateWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    @Override
    public void deleteWallet(String walletId) {
        walletRepository.deleteById(walletId);
    }
}