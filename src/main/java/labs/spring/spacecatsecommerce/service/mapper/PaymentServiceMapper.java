package labs.spring.spacecatsecommerce.service.mapper;

import labs.spring.spacecatsecommerce.domain.payment.Payment;
import labs.spring.spacecatsecommerce.domain.payment.PaymentTransaction;
import labs.spring.spacecatsecommerce.dto.payment.PaymentClientRequestDto;
import labs.spring.spacecatsecommerce.dto.payment.PaymentClientResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentServiceMapper {

    @Mapping(target = "consumerReference", source = "consumerReference")
    @Mapping(target = "paymentAssetId", source = "paymentAssetId")
    @Mapping(target = "amount", source = "amount")
    PaymentClientRequestDto toPaymentClientRequestDto(Payment payment);


    @Mapping(target = "id", source = "paymentClientResponseDto.uuid")
    @Mapping(target = "status", source = "paymentClientResponseDto.status")
    @Mapping(target = "cartId", source = "cartId")
    PaymentTransaction toPaymentTransaction(String cartId, PaymentClientResponseDto paymentClientResponseDto);
}
