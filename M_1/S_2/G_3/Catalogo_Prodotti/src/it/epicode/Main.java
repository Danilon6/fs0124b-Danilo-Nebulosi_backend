package it.epicode;

import it.epicode.enums.Category;
import it.epicode.enums.Status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {



    public static void main(String[] args) {

        //CREAZIONE LIBRI
        Product libro1 = new Product(1, "Libro " + 1, Category.BOOK, 100.99);
        Product libro2 = new Product(2, "Libro " + 2, Category.BOOK, 10.99);
        Product libro3 = new Product(3, "Libro " + 3, Category.BOOK, 120.00);
        Product libro4 = new Product(4, "Libro " + 4, Category.BOOK, 15.00);
        Product libro5 = new Product(5, "Libro " + 5, Category.BOOK, 56.99);

        //CREAZIONE BABY
        Product baby1 = new Product(1, "Baby " + 1, Category.BABY, 100.99);
        Product baby2 = new Product(2, "Baby " + 2, Category.BABY, 10.99);
        Product baby3 = new Product(3, "Baby " + 3, Category.BABY, 120.00);
        Product baby4 = new Product(4, "Baby " + 4, Category.BABY, 15.00);
        Product baby5 = new Product(5, "Baby " + 5, Category.BABY, 56.99);

        //CREAZIONE BOYS
        Product boys1 = new Product(1, "Boys " + 1, Category.BOYS, 100.99);
        Product boys2 = new Product(2, "Boys " + 2, Category.BOYS, 10.99);
        Product boys3 = new Product(3, "Boys " + 3, Category.BOYS, 120.00);
        Product boys4 = new Product(4, "Boys " + 4, Category.BOYS, 15.00);
        Product boys5 = new Product(5, "Boys " + 5, Category.BOYS, 56.99);

        List<Product> products = new ArrayList<>();
        products.add(libro1);
        products.add(libro2);
        products.add(libro3);
        products.add(libro4);
        products.add(libro5);
        products.add(baby1);
        products.add(baby2);
        products.add(baby3);
        products.add(baby4);
        products.add(baby5);
        products.add(boys1);
        products.add(boys2);
        products.add(boys3);
        products.add(boys4);
        products.add(boys5);

        //LETTURA DI TUTTI I PRODOTTI
        System.out.println("Tutti i prodotti");
        products.stream()
                .forEach(System.out::println);

        //FILTRO SUI PRODOTTI
        System.out.println("Libri filtrati");
        products.stream()
                .filter(p -> p.category == Category.BOOK && p.price > 100)
                .forEach(System.out::println);


        //CREAZIONE E GENERAZIONE CUSTOMERS
        Customer customer1 = new Customer(1, "Mario Rossi", 1);
        Customer customer2 = new Customer(1, "Luca Bianchi", 2);
        Customer customer3 = new Customer(1, "Giulia Verdi", 2);
        Customer customer4 = new Customer(1, "Alessia Neri", 1);

        //CREAZIONE E GENERAZIONE ORDINI

        //ORDINE1
        Order order1 = new Order(1, Status.ACCEPTED, LocalDate.of(2021, 2, 1), LocalDate.of(2021, 1, 2), Arrays.asList(libro1, libro2, baby3), customer1);

        //ORDINE2
        Order order2 = new Order(2, Status.ACCEPTED, LocalDate.of(2021, 3, 5), LocalDate.of(2021, 3, 10), Arrays.asList(libro1, boys3, baby3), customer2);//PRODOTTI ORDINE2

        //ORDINE3
        Order order3 = new Order(3, Status.ACCEPTED, LocalDate.of(2021, 2, 20), LocalDate.of(2021, 3, 1), Arrays.asList(libro1, libro2, libro4), customer3);

        List<Order> allOrderList = new ArrayList<>();
        allOrderList.add(order1);
        allOrderList.add(order2);
        allOrderList.add(order3);

        //ESERCIZIO 2
        List<Order> ordiniBaby = allOrderList.stream()
                .filter(order -> order.getProducts()
                        .stream()
                        .anyMatch(product -> product.getCategory() == Category.BABY))
                .toList();


        System.out.println("Ordini filtrati");
        ordiniBaby.stream().forEach(System.out::println);

        //ESERCIZIO 3
        List<Product> productBoysWithDiscount = products.stream()
                .filter(p-> p.category == Category.BOYS)
                .map(p-> new Product(p.getId(), p.getName(), p.getCategory(), p.getPrice()*0.9))
                .toList();

        productBoysWithDiscount.stream().forEach(System.out::println);

        //ESERCIZIO 4
        List<Product> ordiniTier = allOrderList.stream()
                .filter(order -> order.getCustomer().getTier() == 2)
                .filter(order -> order.getOrderDate().compareTo(LocalDate.of(2021, 2, 1)) >= 0 && order.getOrderDate().compareTo(LocalDate.of(2021, 4, 1)) <= 0)
                .flatMap(order -> order.getProducts().stream())
                .toList();

        System.out.println("Prodotti filtrati in base al tier e alla data");
        ordiniTier.stream().forEach(System.out::println);
    }


}