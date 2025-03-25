package EcommercePlatform;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private int orderId;
    private Customer customer;
    private List<Product> products;
    private int quantity;
    private LocalDate orderDate;

    public Order(int orderId, Customer customer, List<Product> products, int quantity, LocalDate orderDate) {
        this.orderId = orderId;
        this.customer = customer;
        this.products = products;
        this.quantity = quantity;
        this.orderDate = orderDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalPrice() {
        return products.stream()
                .mapToDouble(s -> s.getPrice() * quantity)
                .sum();
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", customer=" + customer +
                ", products=" + products +
                ", quantity=" + quantity +
                ", orderDate=" + orderDate +
                '}';
    }
}
