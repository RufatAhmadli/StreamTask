package EcommercePlatform;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class CheckDemo {
    public static void main(String[] args) {
        Customer customer1 = new Customer(1, "Will Smith");
        Customer customer2 = new Customer(2, "Joe Smith");
        Customer customer3 = new Customer(3, "Paul Smith");

        Product product1 = new Product(1, "Laptop", 800);
        Product product2 = new Product(2, "Smartphone", 500);

        Order order1 = new Order(1, customer1, List.of(product2), 1, LocalDate.now());
        Order order2 = new Order(2, customer2, List.of(product1), 2, LocalDate.of(2019, 1, 1));
        Order order3 = new Order(3, customer3, List.of(product1, product2), 3, LocalDate.now());
        Order order4 = new Order(4, customer2, List.of(product1), 4, LocalDate.of(2020, 1, 1));

        List<Order> orders = List.of(order1, order2, order3, order4);

        System.out.println("1: ");
        System.out.println(getTopSpendingCustomers(orders));

        System.out.println("2: ");
        System.out.println(getMostPurchasedProduct(orders));

        System.out.println("3: ");
        System.out.println(getAverageOrdersPerCustomer(orders));

        System.out.println("4: ");
        System.out.println(getTotalRevenue(orders));

        System.out.println("5: ");
        System.out.println(getOrdersInLast30Days(orders));

        System.out.println("6: ");
        System.out.println(getCustomerOrderSummary(orders));

        System.out.println("7: ");
        System.out.println(getExpensivePurchases(orders));
    }

    //Top Spending Customers
    public static List<Customer> getTopSpendingCustomers(List<Order> orders) {
        return orders.stream()
                .collect(Collectors.groupingBy(Order::getCustomer, Collectors.summingDouble(Order::getTotalPrice)))
                .entrySet().stream()
                .sorted(Map.Entry.<Customer, Double>comparingByValue(Comparator.reverseOrder()))
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    //Most Purchased Product
    public static Product getMostPurchasedProduct(List<Order> orders) {
        return orders.stream()
                .flatMap(order -> order.getProducts().stream())
                .collect(Collectors.groupingBy(product -> product, Collectors.counting()))
                .entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElseThrow(() -> new NoSuchElementException("No product found"));
    }

    //Orders Per Customer
    public static double getAverageOrdersPerCustomer(List<Order> orders) {
        return orders.stream()
                .collect(Collectors.groupingBy(Order::getCustomer, Collectors.counting()))
                .values()
                .stream()
                .mapToInt(Long::intValue)
                .average()
                .orElseThrow(() -> new NoSuchElementException("No orders found"));
    }

    //Total Revenue
    public static double getTotalRevenue(List<Order> orders) {
        return orders.stream()
                .mapToDouble(Order::getTotalPrice)
                .sum();
    }

    //Orders in the Last 30 Days
    public static List<Order> getOrdersInLast30Days(List<Order> orders) {
        return orders.stream()
                .filter(order -> ChronoUnit.DAYS.between(order.getOrderDate(), LocalDate.now()) <= 30)
                .toList();
    }

    //Customer Order Summary
    public static Map<Customer, Double> getCustomerOrderSummary(List<Order> orders) {
        return orders.stream()
                .collect(Collectors.groupingBy(Order::getCustomer, Collectors.summingDouble(Order::getTotalPrice))); //AI help
    }

    //Expensive Purchases
    public static List<Order> getExpensivePurchases(List<Order> orders) {
        return orders.stream()
                .filter(order -> order.getTotalPrice() > 1000)
                .toList();
    }
}
