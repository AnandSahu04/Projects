import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Car {
    private String carId;
    private String brand;
    private String model;
    private double basePricePerDay;
    private boolean isAvailable;

    public Car(String carId, String brand, String model, double basePricePerDay) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.basePricePerDay = basePricePerDay;
        this.isAvailable = true;
    }
    public String getCarId() {
        return carId;
    }

     public String getBrand(){
        return brand;
     }

     public String getModel(){
        return model;
     }

     public double calculatePrice(int rentalDays){
        return basePricePerDay *rentalDays;
     }

     public boolean isAvailable(){
        return isAvailable;
     }

     public void rent(){
        isAvailable = false;
     }

     public void returnCar(){
        isAvailable = true;
     }
}

class Customer {
    private String customerId;
    private String name;

    public Customer(String customerId, String name){
        this.customerId = customerId;
        this.name = name;
    }

    public String getCustomerId(){
        return customerId;
    }

    public String getName(){
        return name;
    }
}

class Rental{
    private Car1 car;
    private Customer customer;
    private int days;

    public Rental (Car1 car, Customer customer, int days){
        this.car = car;
        this.customer = customer;
        this.days = days;
    }

    public Car1 getCar(){
        return car;
    }

    public Customer getCustomer(){
        return customer;
    }

    public int getDays(){
        return days;
    }
}

class CarRentalSystem{
    private List<Car1> cars;
    private List<Customer> customers;
    private List<Rental> rentals;

    public CarRentalSystem(){
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    public void addCar(Car1 car) {
        cars.add(car);
    }

    public void addCustomer(Customer customer){
        customers.add(customer);
    }

    public void rentCar(Car1 car, Customer customer, int days){
        if(car.isAvailable()){
            car.rent();
            rentals.add(new Rental(car, customer,days));

        } else{
            System.out.println("Car is not available for rent.");
        }
    }

     public void returnCar(Car1 car){
        car.returnCar();
        Rental rentalToRemove = null;
        for (Rental rental : rentals) {
            if (rental.getCar() == car) {
                rentalToRemove = rental;
                break;
            }
        }
        if (rentalToRemove != null) {
            rentals.remove(rentalToRemove);

        } else{
            System.out.println("Car was not rented");
        }
     }

     public void menu(){
        Scanner kb = new Scanner(System.in);

        while(true) {
            System.out.println("|~~~~~~| Car Rental System |~~~~~~|");
            System.out.println("1. Rent a Car");
            System.out.println("2. Return a Car");
            System.out.println("3. Exit");
            System.out.println("Enter your choice: ");

            int choice = kb.nextInt();
            kb.nextLine();

            if (choice == 1){
                System.out.println("\n == Rent a Car ==\n");
                System.out.println("Enter your name: ");
                String customerName = kb.nextLine();

                System.out.println("\nAvailable Cars: ");
                for (Car1 car : cars) {
                    if(car.isAvailable()) {
                        System.out.println(car.getCarId() + " - " + car.getBrand() + " " + car.getModel());
                    }
                }

                System.out.println("\nEnter the car ID you want to rent: ");
                String carId = kb.nextLine();

                System.out.println("Enter the number of days for rental: ");
                int rentalDays = kb.nextInt();
                kb.nextLine();

                Customer newCustomer = new Customer("CUS" + (customers.size() + 1), customerName);
                addCustomer(newCustomer);

                Car1 selectedCar = null;
                for (Car1 car : cars){
                    if (car.getCarId().equals(carId) && car.isAvailable()){
                        selectedCar = car;
                        break;
                    }
                }

                if(selectedCar != null) {
                    double totalPrice = selectedCar.calculatePrice(rentalDays);
                    System.out.println("\n== Rental Information ==\n");
                    System.out.println("Customer ID: " + newCustomer.getName());
                    System.out.println("Customer Name: " + newCustomer.getName());
                    System.out.println("Car: " + selectedCar.getBrand() + " " + selectedCar.getModel());
                    System.out.println("Rental Days: " + rentalDays);
                    System.out.printf("Total Price: $%.2f%n", totalPrice);

                    System.out.println("\nConfirm rental (Y/N): ");
                    String confirm = kb.nextLine();

                    if (confirm.equalsIgnoreCase("Y")) {
                        rentCar(selectedCar, newCustomer, rentalDays);
                        System.out.println("\nCar rented successfully.");
                    } else {
                        System.out.println("\nRental canceled.");
                    }
                } else{
                    System.out.println("\nInvalid car selection or car not available for rent.");
                }
            } else if (choice == 2) {
                System.out.println("\n== Return a Car ==\n");
                System.out.println("Enter the car ID you want to return: ");
                String carId = kb.nextLine();

                Car1 carToReturn = null;
                for (Car1 car : cars) {
                    if (car.getCarId().equals(carId) && !car.isAvailable()) {
                        carToReturn = car;
                        break;
                    }
                }

                if (carToReturn != null) {
                    Customer customer = null;
                    for (Rental rental : rentals) {
                        if (rental.getCar() == carToReturn) {
                            customer = rental.getCustomer();
                            break;
                        }
                    }

                    if (customer != null) {
                        returnCar(carToReturn);
                        System.out.println("Car returned successfully by " + customer.getName());
                    } else {
                        System.out.println("Car was not rented or rental details are missing.");
                    }
                } else {
                    System.out.println("Invaild car ID or car is not rented.");
                }
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid choice. Please enter a vaild option.");
            }
        }

         System.out.println("\nThank you for using the Car Rental System! Have a nice day.");
     }

}

public class Main {
    public static void main(String[] args) {
        CarRentalSystem rentalSystem = new CarRentalSystem();

        Car1 car1 = new Car1("C001", "Tata", "Harrier", 40.0);
        Car1 car2 = new Car1("C002", "Hyundai", "Creta", 30.0);
        Car1 car3 = new Car1("C003", "Mahindra", "Thar", 25.0);
        Car1 car4 = new Car1("C004", "Toyota", "Fortuner", 45.0);
        Car1 car5 = new Car1("C005", "MG", "Hector", 35.0);

        rentalSystem.addCar(car1);
        rentalSystem.addCar(car2);
        rentalSystem.addCar(car3);
        rentalSystem.addCar(car4);
        rentalSystem.addCar(car5);

        rentalSystem.menu();

    }
}