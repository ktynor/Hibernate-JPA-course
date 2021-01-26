package pl.nullpointerexception.hibernate.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@NamedEntityGraph(
        name = "order-rows",
        attributeNodes = @NamedAttributeNode("orderRows")
)
@Entity
@Table(name = "\"order\"") // order is a key word in SQL and needs: "order"
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime created;
    private BigDecimal total;

    @OneToMany
    @JoinColumn(name = "order_id")
    private Set<OrderRow> orderRows;

//    Let's make a relation customer-order bidirectional
    @OneToOne
    private Customer customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Set<OrderRow> getOrderRows() {
        return orderRows;
    }

    public void setOrderRows(Set<OrderRow> orderRows) {
        this.orderRows = orderRows;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", created=" + created +
                ", total=" + total +
                '}';
    }
}
