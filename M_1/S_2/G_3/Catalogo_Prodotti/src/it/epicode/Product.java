package it.epicode;

import it.epicode.enums.Category;

public class Product {
    long id;
    String name;
    Category category;
    Double price;

    public Product(long id, String name, Category category, Double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Prodotto [id=%s, name=%s, category=%s, price=%f ]", id, name, category, price);
    }
}
