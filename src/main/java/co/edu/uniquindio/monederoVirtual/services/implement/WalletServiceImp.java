package co.edu.uniquindio.monederoVirtual.services.implement;

import co.edu.uniquindio.monederoVirtual.dto.WalletDTO;
import co.edu.uniquindio.monederoVirtual.mapper.WalletMapper;
import co.edu.uniquindio.monederoVirtual.model.Wallet;
import co.edu.uniquindio.monederoVirtual.repository.WalletRepository;
import co.edu.uniquindio.monederoVirtual.services.WalletService;
import jakarta.validation.Valid;

import java.util.*;

public class WalletServiceImp implements WalletService {

    private final WalletRepository walletRepository;
    private final WalletMapper walletMapper;

    public WalletServiceImp(WalletRepository walletRepository, WalletMapper walletMapper) {
        this.walletRepository = walletRepository;
        this.walletMapper = walletMapper;
    }

    @Override
    public Wallet createWallet(WalletDTO walletDTO) {
        Wallet wallet = walletMapper.dtoToWallet(walletDTO);
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
    public Wallet updateWallet(WalletDTO walletDTO) {
        Wallet wallet = walletMapper.dtoToWallet(walletDTO);
        return walletRepository.save(wallet);
    }

    @Override
    public void deleteWallet(String walletId) {
        walletRepository.deleteById(walletId);
    }
}