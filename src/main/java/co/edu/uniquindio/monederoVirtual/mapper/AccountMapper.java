package co.edu.uniquindio.monederoVirtual.mapper;

import co.edu.uniquindio.monederoVirtual.dto.AccountDTO;
import co.edu.uniquindio.monederoVirtual.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);
    Account dtoToAccount(AccountDTO dto);
    AccountDTO accountToDto(Account account);
}
