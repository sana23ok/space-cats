package labs.spring.spacecatsecommerce.repository.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import static jakarta.persistence.CascadeType.PERSIST;

@Entity
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "\"order\"")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_seq")
    @SequenceGenerator(name = "order_id_seq", sequenceName = "order_id_seq")
    Long id;

    @NaturalId
    String cartId;
    Double totalPrice;
    String paymentReference;

    @ManyToOne(cascade = PERSIST)
    @JoinColumn(name = "customer_id", nullable = false)
    CustomerEntity customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST, orphanRemoval = true)
    List<OrderEntryEntity> entries;

}
