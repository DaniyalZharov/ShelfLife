package org.example.shelflife.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate expiryDate;
    private int quantity;
    private String mainIngredient;

    public FoodItem() { }

    public FoodItem(String name, LocalDate expiryDate, int quantity, String mainIngredient) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.quantity = quantity;
        this.mainIngredient = mainIngredient;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public String getMainIngredient() { return mainIngredient; }
    public void setMainIngredient(String mainIngredient) { this.mainIngredient = mainIngredient; }
}