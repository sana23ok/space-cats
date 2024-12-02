package labs.spring.spacecatsecommerce.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_entry")
public class OrderEntryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "order_entry_id_seq")
    @SequenceGenerator(name = "order_entry_id_seq", sequenceName = "order_entry_id_seq")
    Long id;

    Long productCode;
    Integer quantity;
    Double price;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    OrderEntity order;
}
