//package org.example.shelflife.services;
//
//import org.example.shelflife.entities.FoodItem;
//import org.example.shelflife.repositories.FoodRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class FoodService {
//
//    private final FoodRepository repo;
//
//    @Autowired
//    public FoodService(FoodRepository repo) {
//        this.repo = repo;
//    }
//
//    public List<FoodItem> findAll() {
//        return repo.findAllByOrderByExpiryDateAsc();
//    }
//
//    public Optional<FoodItem> findById(Long id) {
//        return repo.findById(id);
//    }
//
//    public List<FoodItem> findByName(String name) {
//        return repo.findByNameContainingIgnoreCaseOrderByExpiryDateAsc(name);
//    }
//
//    public FoodItem save(FoodItem item) {
//        return repo.save(item);
//    }
//
//    public void delete(Long id) {
//        repo.deleteById(id);
//    }
//}


package org.example.shelflife.services;

import org.example.shelflife.entities.FoodItem;
import org.example.shelflife.repositories.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FoodService {
    private final FoodRepository repo;

    @Autowired
    public FoodService(FoodRepository repo) {
        this.repo = repo;
    }

    public List<FoodItem> findAll() {
        return repo.findAllByOrderByExpiryDateAsc();
    }

    public Optional<FoodItem> findById(Long id) {
        return repo.findById(id);
    }

    public List<FoodItem> findByName(String name) {
        return repo.findByNameContainingIgnoreCaseOrderByExpiryDateAsc(name);
    }

    public FoodItem save(FoodItem item) {
        // Validate that the expiry date is not in the past
        if (item.getExpiryDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Cannot add or update food with an expired date");
        }

        // Validate that quantity is positive
        if (item.getQuantity() <= 0) {
            throw new IllegalArgumentException("Food quantity must be positive");
        }

        return repo.save(item);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}