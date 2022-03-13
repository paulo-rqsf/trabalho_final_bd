package br.com.compasso.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProductList = new ArrayList<>();

    private double totalValue = 0;
    private double feeValue;
    private int deliveryTime;

    public Order(double feeValue, int deliveryTime) {
        this.feeValue = feeValue;
        this.deliveryTime = deliveryTime;
    }

    public Order() { }

    public void addProductOrder(OrderProduct orderProduct){
        orderProduct.setOrder(this);
        orderProductList.add(orderProduct);
    }

    public Long getId() { return Id; }
    public void setId(Long id) { Id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public double getTotalValue() { return totalValue; }
    public void setTotalValue(double totalValue) { this.totalValue = totalValue; }

    public double getFeeValue() { return feeValue; }
    public void setFeeValue(double feeValue) { this.feeValue = feeValue; }

    public int getDeliveryTime() { return deliveryTime; }
    public void setDeliveryTime(int deliveryTime) { this.deliveryTime = deliveryTime; }

    public List<OrderProduct> getOrderProductList() {
        return orderProductList;
    }
    public void setOrderProductList(List<OrderProduct> orderProductList) {
        this.orderProductList = orderProductList;
    }

    @Override
    public String toString() {
        return "Order{" +
                "Id=" + Id +
                ", productList=" + orderProductList +
                ", users=" + user +
                '}';
    }
}
