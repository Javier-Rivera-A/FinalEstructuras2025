package co.edu.uniquindio.monederoVirtual.mapper;

import co.edu.uniquindio.monederoVirtual.dto.CustomerDTO;
import co.edu.uniquindio.monederoVirtual.model.Customer;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customer dtoToCustomer(CustomerDTO dto);
    CustomerDTO customerToDto(Customer customer);
}
