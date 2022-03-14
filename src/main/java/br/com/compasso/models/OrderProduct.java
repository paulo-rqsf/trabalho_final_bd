package br.com.compasso.models;

import jakarta.json.bind.annotation.JsonbTransient;

import javax.persistence.*;

@Entity
@Table(name = "order_product")
public class OrderProduct
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int qt;
    private double unitPrice;

    @ManyToOne
    @JsonbTransient
    private Order order;

    @ManyToOne
    private Product product;

    public OrderProduct(int qt, Order order, Product product) {
        this.qt = qt;
        this.order = order;
        this.unitPrice = product.getProductValue();
        this.product = product;
    }

    public OrderProduct() { }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getQt() { return qt; }
    public void setQt(int qt) { this.qt = qt; }

    public double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }

    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    @Override
    public String toString() {
        return "OrderProduct{" +
                "Id=" + id +
                ", qt=" + qt +
                ", unitPrice=" + unitPrice +
                ", product=" + product +
                '}';
    }
}
