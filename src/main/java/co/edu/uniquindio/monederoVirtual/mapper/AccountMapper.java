package co.edu.uniquindio.monederoVirtual.mapper;

import co.edu.uniquindio.monederoVirtual.dto.AccountDTO;
import co.edu.uniquindio.monederoVirtual.model.Account;
@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);
    Account dtoToAccount(AccountDTO dto);
    AccountDTO accountToDto(Account account);
}
