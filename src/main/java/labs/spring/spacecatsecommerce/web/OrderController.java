//package labs.spring.spacecatsecommerce.web;
//
//import labs.spring.spacecatsecommerce.dto.OrderDTO;
//import labs.spring.spacecatsecommerce.service.OrderService;
//import labs.spring.spacecatsecommerce.service.mapper.OrderMapper;
//import jakarta.validation.Valid;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@Validated
//@RequestMapping("/api/v1/orders")
//public class OrderController {
//
//    private final OrderService orderService;
//    private final OrderMapper orderMapper;
//
//    public OrderController(OrderService orderService, OrderMapper orderMapper) {
//        this.orderService = orderService;
//        this.orderMapper = orderMapper;
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
//        return ResponseEntity.ok(orderMapper.toOrderDTO(orderService.getOrderById(id)));
//    }
//
//    @PostMapping
//    public ResponseEntity<OrderDTO> createOrder(@RequestBody @Valid OrderDTO orderDTO) {
//        return ResponseEntity.ok(orderMapper.toOrderDTO(orderService.createOrder(orderDTO)));
//    }
//}

