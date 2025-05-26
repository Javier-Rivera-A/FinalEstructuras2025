package co.edu.uniquindio.monederoVirtual.bussinesLogic;

import co.edu.uniquindio.monederoVirtual.dto.WalletDTO;
import co.edu.uniquindio.monederoVirtual.mapper.WalletMapper;
import co.edu.uniquindio.monederoVirtual.model.Account;
import co.edu.uniquindio.monederoVirtual.model.Customer;
import co.edu.uniquindio.monederoVirtual.model.Wallet;
import co.edu.uniquindio.monederoVirtual.repository.WalletRepository;
import co.edu.uniquindio.monederoVirtual.services.implement.WalletServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WalletServiceTest {

    @Mock
    private WalletRepository walletRepository;

    private WalletMapper walletMapper;

    private WalletServiceImp walletService;

    private WalletDTO walletDTO;
    private Wallet wallet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        walletMapper = Mappers.getMapper(WalletMapper.class);

        // Construir el servicio manualmente con dependencias
        walletService = new WalletServiceImp(walletRepository, walletMapper);

        Account mockAccount = new Account();
        Customer mockCustomer = new Customer();

        walletDTO = new WalletDTO("Mi Billetera", "wallet123", 100.0, mockAccount, mockCustomer);

        wallet = new Wallet();
        wallet.setId("wallet123");
        wallet.setName("Mi Billetera");
        wallet.setBalance(100.0);
        wallet.setParentAccount(mockAccount);
        wallet.setOwner(mockCustomer);
    }

    @Test
    void testCreateWallet() {
        when(walletRepository.save(any(Wallet.class))).thenReturn(wallet);

        Wallet createdWallet = walletService.createWallet(walletDTO);

        assertNotNull(createdWallet);
        assertEquals("wallet123", createdWallet.getId());
        assertEquals("Mi Billetera", createdWallet.getName());
        assertEquals(100.0, createdWallet.getBalance());
        verify(walletRepository, times(1)).save(any(Wallet.class));
    }

    @Test
    void testGetWalletById() {
        when(walletRepository.findById("wallet123")).thenReturn(Optional.of(wallet));

        Optional<Wallet> foundWallet = walletService.getWalletById("wallet123");

        assertTrue(foundWallet.isPresent());
        assertEquals("wallet123", foundWallet.get().getId());
        verify(walletRepository, times(1)).findById("wallet123");
    }

    @Test
    void testGetAllWallets() {
        when(walletRepository.findAll()).thenReturn(List.of(wallet));

        List<Wallet> wallets = walletService.getAllWallets();

        assertEquals(1, wallets.size());
        verify(walletRepository, times(1)).findAll();
    }

    @Test
    void testUpdateWallet() {
        when(walletRepository.save(any(Wallet.class))).thenReturn(wallet);

        Wallet updatedWallet = walletService.updateWallet(walletDTO);

        assertNotNull(updatedWallet);
        assertEquals("wallet123", updatedWallet.getId());
        verify(walletRepository, times(1)).save(any(Wallet.class));
    }

    @Test
    void testDeleteWallet() {
        doNothing().when(walletRepository).deleteById("wallet123");

        walletService.deleteWallet("wallet123");

        verify(walletRepository, times(1)).deleteById("wallet123");
    }
}