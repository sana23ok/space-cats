package labs.spring.spacecatsecommerce.web.mapper;

import labs.spring.spacecatsecommerce.common.ProductType;
import labs.spring.spacecatsecommerce.domain.order.Order;
import labs.spring.spacecatsecommerce.domain.order.OrderContext;
import labs.spring.spacecatsecommerce.domain.order.OrderEntry;
import labs.spring.spacecatsecommerce.dto.order.OrderEntryDto;
import labs.spring.spacecatsecommerce.dto.order.PlaceOrderRequestDto;
import labs.spring.spacecatsecommerce.dto.order.PlaceOrderResponseDto;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderDtoMapper {


    @Mapping(target = "cartId", source = "cartId")
    @Mapping(target = "totalPrice", source = "orderDto.totalPrice")
    @Mapping(target = "customerReference", source = "customerReference")
    @Mapping(target = "entries", source = "orderDto.entries")
    OrderContext toOrderContext(String cartId, Long customerReference, PlaceOrderRequestDto orderDto);

    @Mapping(target = "productType", source = "productType")
    @Mapping(target = "amount", source = "quantity")
    OrderEntry toOrderEntry(OrderEntryDto orderEntryDto);

    default ProductType toProductType(String productType) {
        return ProductType.fromDisplayName(productType);
    }

    @Mapping(target = "orderId", source = "id")
    @Mapping(target = "transactionId", source = "transactionId")
    PlaceOrderResponseDto toPlaceOrderResponseDto(Order order);
}
