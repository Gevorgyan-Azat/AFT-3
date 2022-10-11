package framework;

import java.util.ArrayList;

public class Product {

    private int id = 0;
    private String name;
    private int price;
    private String warranty;
    private String description;

    public Product(int id, String name, int price, String warranty, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.warranty = warranty;
        this.description = description;
    }

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
