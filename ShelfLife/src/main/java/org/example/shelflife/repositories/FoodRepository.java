package org.example.shelflife.repositories;

import org.example.shelflife.entities.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FoodRepository extends JpaRepository<FoodItem, Long> {
    List<FoodItem> findAllByOrderByExpiryDateAsc();
    List<FoodItem> findByNameContainingIgnoreCaseOrderByExpiryDateAsc(String name);
}