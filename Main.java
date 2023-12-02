import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Plant {
    private String name;
    private double price;
    private int stock;

    public Plant(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}

class Customer {
    private String name;
    private String email;

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}

class Order {
    private Customer customer;
    private Map<Plant, Integer> plantsOrdered;

    public Order(Customer customer) {
        this.customer = customer;
        plantsOrdered = new HashMap<>();
    }

    public Customer getCustomer() {
        return customer;
    }

    public Map<Plant, Integer> getPlantsOrdered() {
        return plantsOrdered;
    }

    public void addPlantToOrder(Plant plant, int quantity) {
        plantsOrdered.put(plant, quantity);
    }
}
class Nursery {
    private List<Plant> plants;
    private List<Customer> customers;
    private List<Order> orders;

    public Nursery() {
        plants = new ArrayList<>();
        customers = new ArrayList<>();
        orders = new ArrayList<>();
    }

    public void addPlant(Plant plant) {
        plants.add(plant);
    }

    public void registerCustomer(Customer customer) {
        customers.add(customer);
    }

    public void placeOrder(Customer customer, Map<Plant, Integer> plantsToOrder) {
        Order order = new Order(customer);
        for (Map.Entry<Plant, Integer> entry : plantsToOrder.entrySet()) {
            Plant plant = entry.getKey();
            int quantity = entry.getValue();
            order.addPlantToOrder(plant, quantity);

            int updatedStock = plant.getStock() - quantity;
            plant.setStock(updatedStock);
        }
        orders.add(order);
    }

    public void displayAvailablePlants() {
        System.out.println("Available Plants:");
        for (Plant plant : plants) {
            System.out.println("Plant: " + plant.getName() + ", Price: $" + plant.getPrice() + ", Stock: " + plant.getStock());
        }
    }

    public void listRegisteredCustomers() {
        System.out.println("Registered Customers:");
        for (Customer customer : customers) {
            System.out.println("Name: " + customer.getName() + ", Email: " + customer.getEmail());
        }
    }

    public void showCustomerOrders(Customer customer) {
        System.out.println("Orders for Customer: " + customer.getName());
        for (Order order : orders) {
            if (order.getCustomer().equals(customer)) {
                System.out.println("Order Details:");
                for (Map.Entry<Plant, Integer> entry : order.getPlantsOrdered().entrySet()) {
                    Plant plant = entry.getKey();
                    int quantity = entry.getValue();
                    System.out.println("Plant: " + plant.getName() + ", Quantity: " + quantity);
                }
            }
        }
    }
}


public class Main {
    public static void main(String[] args) {
        Nursery nursery = new Nursery();
        Plant plant1 = new Plant("Rose", 10.5, 50);
        Plant plant2 = new Plant("Lily", 8.0, 30);
        Plant plant3 = new Plant("Peony", 13.5, 50);
        Plant plant4 = new Plant("Orchid", 15.0, 30);
        nursery.addPlant(plant1);
        nursery.addPlant(plant2);
        nursery.addPlant(plant3);
        nursery.addPlant(plant4);
        Customer customer1 = new Customer("Sumaiya", "sumu@gmail.com");
        Customer customer2 = new Customer("Aporna", "mickeymouse@gmail.com");
        nursery.registerCustomer(customer1);
        nursery.registerCustomer(customer2);
        Map<Plant, Integer> plantsToOrder1 = new HashMap<>();
        plantsToOrder1.put(plant1, 2);
        plantsToOrder1.put(plant2, 1);
        plantsToOrder1.put(plant3, 5);
        plantsToOrder1.put(plant4, 3);
        nursery.placeOrder(customer1, plantsToOrder1);
        nursery.displayAvailablePlants();
        nursery.listRegisteredCustomers();
        nursery.showCustomerOrders(customer1);
    }
}