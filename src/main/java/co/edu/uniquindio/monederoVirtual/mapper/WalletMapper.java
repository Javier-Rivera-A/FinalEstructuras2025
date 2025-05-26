package co.edu.uniquindio.monederoVirtual.mapper;

import co.edu.uniquindio.monederoVirtual.dto.WalletDTO;
import co.edu.uniquindio.monederoVirtual.model.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WalletMapper {
    WalletMapper INSTANCE = Mappers.getMapper(WalletMapper.class);
    Wallet dtoToWallet(WalletDTO walletDTO);
    WalletDTO WalletToDto(Wallet wallet);
}
