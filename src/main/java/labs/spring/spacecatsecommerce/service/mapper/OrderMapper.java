package labs.spring.spacecatsecommerce.service.mapper;

import labs.spring.spacecatsecommerce.domain.order.Order;
import labs.spring.spacecatsecommerce.domain.order.OrderEntry;
import labs.spring.spacecatsecommerce.dto.order.OrderDto;
import labs.spring.spacecatsecommerce.dto.order.OrderEntryDto;
import labs.spring.spacecatsecommerce.dto.order.PlaceOrderRequestDto;
import labs.spring.spacecatsecommerce.repository.entity.CustomerEntity;
import labs.spring.spacecatsecommerce.repository.entity.OrderEntity;
import labs.spring.spacecatsecommerce.repository.entity.OrderEntryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    // Map OrderEntity to Order domain
    @Mapping(target = "id", source = "id")
    @Mapping(target = "customer", source = "customer.id")
    @Mapping(target = "entries", source = "entries")
    Order toOrder(OrderEntity orderEntity);

    // Map OrderEntryEntity to OrderEntry domain
    @Mapping(target = "id", source = "id")
    @Mapping(target = "productCode", source = "productCode")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "price", source = "price")
    OrderEntry toOrderEntry(OrderEntryEntity orderEntryEntity);

    // Map OrderEntry domain to OrderEntryDto
    @Mapping(target = "productType", source = "productCode")
    @Mapping(target = "quantity", source = "quantity")
    OrderEntryDto toOrderEntryDto(OrderEntry orderEntry);

    // Map a list of OrderEntry to a list of OrderEntryDto
    List<OrderEntryDto> toOrderEntryDtos(List<OrderEntry> orderEntries);

    // Map Order domain to OrderEntity
    @Mapping(target = "id", source = "id")
    @Mapping(target = "customer", source = "customer", qualifiedByName = "mapCustomerIdToEntity") // Custom mapping here
    @Mapping(target = "entries", source = "entries")
    OrderEntity toOrderEntity(Order order);

    // Map OrderEntry domain to OrderEntryEntity
    @Mapping(target = "id", source = "id")
    @Mapping(target = "productCode", source = "productCode")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "price", source = "price")
    OrderEntryEntity toOrderEntryEntity(OrderEntry orderEntry);

    // Map PlaceOrderRequestDto to Order domain
    @Mapping(target = "totalPrice", source = "totalPrice")
    @Mapping(target = "entries", source = "entries")
    Order toOrder(PlaceOrderRequestDto placeOrderRequestDto);

    // Map Order domain to PlaceOrderRequestDto
    PlaceOrderRequestDto toPlaceOrderRequestDto(Order order);

    // Map Customer ID to CustomerEntity
    @Named("mapCustomerIdToEntity")
    default CustomerEntity mapCustomerIdToEntity(Long customerId) {
        if (customerId == null) {
            return null;
        }
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(customerId);
        return customerEntity;
    }

    // Map OrderEntity to OrderDto
    @Mapping(target = "id", source = "id")
    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "entries", source = "entries")
    @Mapping(target = "totalPrice", source = "totalPrice")
    OrderDto toOrderDto(OrderEntity savedOrderEntity);

    // Map a list of OrderEntity to a list of Order domain objects
    List<Order> toOrders(List<OrderEntity> orderEntities);
}

