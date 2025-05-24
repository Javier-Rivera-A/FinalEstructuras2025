package co.edu.uniquindio.monederoVirtual.mapper;

import co.edu.uniquindio.monederoVirtual.dto.CustomerDTO;
import co.edu.uniquindio.monederoVirtual.dto.customers.CreateCustomerDTO;
import co.edu.uniquindio.monederoVirtual.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customer createDtoToCustomer(CreateCustomerDTO dto);
    CreateCustomerDTO createCustomerToDto(Customer customer);

    Customer dtoToCustomer(CustomerDTO dto);
    CustomerDTO customerToDto(Customer customer);
}
