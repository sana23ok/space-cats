package labs.spring.spacecatsecommerce.service.impl;

import labs.spring.spacecatsecommerce.common.CommunicationChannel;
import labs.spring.spacecatsecommerce.common.ProductType;
import labs.spring.spacecatsecommerce.domain.Category;
import labs.spring.spacecatsecommerce.domain.Product;
import labs.spring.spacecatsecommerce.domain.order.Order;
import labs.spring.spacecatsecommerce.dto.customer.CustomerDetailsDto;
import labs.spring.spacecatsecommerce.dto.order.OrderDto;
import labs.spring.spacecatsecommerce.repository.OrderEntryRepository;
import labs.spring.spacecatsecommerce.repository.OrderRepository;
import labs.spring.spacecatsecommerce.repository.entity.OrderEntity;
import labs.spring.spacecatsecommerce.repository.entity.OrderEntryEntity;
import labs.spring.spacecatsecommerce.service.OrderService;
import labs.spring.spacecatsecommerce.service.mapper.OrderMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderEntryRepository orderEntryRepository;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderRepository orderRepository, OrderEntryRepository orderEntryRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderEntryRepository = orderEntryRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    @Transactional
    public OrderDto createOrder(Order order) {
        OrderEntity orderEntity = orderMapper.toOrderEntity(order);
        OrderEntity savedOrderEntity = orderRepository.save(orderEntity);
        return orderMapper.toOrderDto(savedOrderEntity);
    }

    @Override
    public OrderDto findOrderById(Long id) {
        OrderEntity orderEntity = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
        return orderMapper.toOrderDto(orderEntity);
    }

    @Override
    public List<OrderDto> findAllOrders() {
        List<OrderEntity> orderEntities = (List<OrderEntity>) orderRepository.findAll();
        return orderEntities.stream()
                .map(orderMapper::toOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        } else {
            throw new RuntimeException("Order not found with ID: " + id);
        }
    }

    @Override
    @Transactional
    public OrderDto addEntryToOrder(Long orderId, Long productCode, int quantity) {
        OrderEntity orderEntity = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));

        Product product = fetchProductByCode(productCode);
        if (product == null) {
            throw new RuntimeException("Product not found with code: " + productCode);
        }

        OrderEntryEntity orderEntry = new OrderEntryEntity();
        orderEntry.setOrder(orderEntity);
        orderEntry.setProductCode(productCode);
        orderEntry.setQuantity(quantity);

        orderEntryRepository.save(orderEntry);

        return findOrderById(orderId);
    }

    private Product fetchProductByCode(Long productCode) {
        Category mockCategory = Category.builder()
                .name("Electronics")
                .description("Devices and gadgets")
                .build();

        Product mockProduct = Product.builder()
                .id(productCode)
                .name("Laser Pointer Asteroid")
                .description("A high-quality laser pointer.")
                .price(15.99)
                .type(ProductType.COSMIC_CATNIP)
                .category(mockCategory)
                .build();

        return mockProduct;
    }


    private CustomerDetailsDto fetchCustomerById(Long customerId) {
        CustomerDetailsDto mockCustomer = CustomerDetailsDto.builder()
                .id(customerId)
                .name("Alice")
                .address("Sector 5, Planet Zeta, Quadrant 12")
                .phoneNumber("555-1234")
                .email("alice@example.com")
                .preferredChannel(List.of(CommunicationChannel.MOBILE.name(),
                        CommunicationChannel.EMAIL.name()))
//          .customerReference("REF12345")
                .build();

        return mockCustomer;
    }
}
